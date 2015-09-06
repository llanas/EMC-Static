package parc.mlj.dao;

import static parc.mlj.dao.config.DAOUtilitaire.fermeturesSilencieuses;
import static parc.mlj.dao.config.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import parc.mlj.beans.Address;
import parc.mlj.beans.User;
import parc.mlj.dao.config.DAOException;

public class AddressDaoImpl implements AddressDAO {

	private static final String SQL_SELECT_BY_ID = "SELECT * FROM address WHERE id_address = ?";
	private static final String SQL_SELECT 		 = "SELECT * FROM address ORDER BY id_address";
	
	private DAOFactory daoFactory;
	
	AddressDaoImpl( DAOFactory daoFactory ){
		this.daoFactory = daoFactory;
	}
	
	public Address trouver( long id ) throws DAOException{
		return trouver( SQL_SELECT_BY_ID, id );
	}
	
	private Address trouver( String sql, Object... objects ){
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Address address = null;
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objects);
			resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ){
				address = map(resultSet);
			}
		} catch (SQLException e){
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		
		return address;
	}
	
	public List<Address> lister() throws DAOException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Address> addresses = new ArrayList<Address>();
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while( resultSet.next() ){
				addresses.add(map(resultSet));
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return addresses;
	}
	
	private Address map( ResultSet resultSet ) throws SQLException {
		
		Address address = new Address();
		address.setId( resultSet.getLong( "id_address" ));
		
		PartnerDAO partnerDAO = daoFactory.getPartnerDAO();
		address.setPartner( partnerDAO.trouver( resultSet.getLong( "partner_address" )));
		
		address.setName( resultSet.getString( "name_address" ));
		address.setNum( resultSet.getLong( "n_address" ));
		address.setRoad( resultSet.getString( "road_address" ));
		address.setTypeRoad( resultSet.getString( "type_road_address" ));
		address.setCity( resultSet.getString( "city_address" ));
		address.setCode( resultSet.getLong( "code_address" ));
		address.setCountry( resultSet.getString( "country_address" ));
		
		return address;
	}
}
