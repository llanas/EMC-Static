package parc.mlj.dao;

import static parc.mlj.dao.config.DAOUtilitaire.fermeturesSilencieuses;
import static parc.mlj.dao.config.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import parc.mlj.beans.TypeHardware;
import parc.mlj.dao.config.DAOException;

public class TypeHardwareDaoImpl implements TypeHardwareDAO {

	private static final String SQL_SELECT_BY_ID = "SELECT * FROM type_hardware WHERE id_type_hardware = ?";

	private DAOFactory daoFactory;
	
	TypeHardwareDaoImpl( DAOFactory daoFactory ){
		this.daoFactory = daoFactory;
	}
	
	public TypeHardware trouver( long id ) throws DAOException {
		return trouver( SQL_SELECT_BY_ID, id );
	}
	
	private TypeHardware trouver( String sql, Object... objects){
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		TypeHardware typeHardware = null;
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, sql, false, objects);
			resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ){
				typeHardware = map( resultSet );
			}
		} catch (SQLException e){
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}

		return typeHardware;
	}
	
	private TypeHardware map( ResultSet resultSet ) throws SQLException {
		
		TypeHardware typeHardware = new TypeHardware();
		typeHardware.setId( resultSet.getLong( "id_type_hardware" ));
		typeHardware.setName( resultSet.getString( "name_type_hardware" ));
		
		return typeHardware;
}

}
