package parc.mlj.dao;

import static parc.mlj.dao.config.DAOUtilitaire.fermeturesSilencieuses;
import static parc.mlj.dao.config.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import parc.mlj.beans.Location;
import parc.mlj.beans.User;
import parc.mlj.dao.config.DAOException;


public class LocationDaoImpl implements LocationDAO {

	private static final String SQL_SELECT_BY_ID	= "SELECT * FROM locations WHERE id_location = ?";
	private static final String SQL_SELECT 			= "SELECT * FROM locations ORDER BY id_location";
	
	private DAOFactory daoFactory;
	
	LocationDaoImpl( DAOFactory daoFactory ){
		this.daoFactory = daoFactory;
	}
	
	public Location trouver( long id ) throws DAOException{
		return trouver( SQL_SELECT_BY_ID, id );
	}
	
	private Location trouver(String sql, Object... objects) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Location location = null;
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objects);
			resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ){
				location = map(resultSet);
			}
		} catch (SQLException e){
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion);
		}
		return location;
	}
	
	public List<Location> lister() throws DAOException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Location> locations = new ArrayList<Location>();
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while( resultSet.next() ){
				locations.add(map(resultSet));
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return locations;
	}
	
	private Location map( ResultSet resultSet ) throws SQLException{
		
		Location location = new Location();
		location.setId( resultSet.getLong( "id_location" ));
		
		AddressDAO addressDAO = daoFactory.getAddressDAO();
		location.setAddress( addressDAO.trouver( resultSet.getLong( "address_location" )));
		
		location.setName( resultSet.getString( "name_location" ));
		location.setDesk( resultSet.getLong( "desk_location" ));
		location.setFloor( resultSet.getLong( "floor_location" ));
				
		return location;
	}
	
	
}
