package parc.mlj.dao;

import static parc.mlj.dao.config.DAOUtilitaire.fermeturesSilencieuses;
import static parc.mlj.dao.config.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import parc.mlj.beans.TypeTicket;
import parc.mlj.dao.config.DAOException;

public class TypeTicketDaoImpl implements TypeTicketDAO {

	private static final String SQL_SELECT_BY_ID 	= "SELECT * FROM type_ticket WHERE id_type_ticket = ?";
	
	private DAOFactory daoFactory;
	
	TypeTicketDaoImpl( DAOFactory daoFactory ){
		this.daoFactory = daoFactory;
	}
	
	public TypeTicket trouver( long id ) throws DAOException {
		return trouver( SQL_SELECT_BY_ID, id );
	}
	
	private TypeTicket trouver( String sql, Object... objects ) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		TypeTicket typeTicket = null;
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objects );
			resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ){
				typeTicket = map( resultSet );
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return typeTicket;
	}
	
	private TypeTicket map( ResultSet resultSet ) throws SQLException {
		
		TypeTicket typeTicket = new TypeTicket();
		typeTicket.setId( resultSet.getLong( "id_type_ticket" ));
		typeTicket.setName( resultSet.getString( "name_type_ticket" ));
		typeTicket.setStat( resultSet.getString( "stat_type_ticket" ));
		
		return typeTicket;
	}
}
