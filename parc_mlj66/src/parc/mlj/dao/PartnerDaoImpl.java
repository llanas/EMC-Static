package parc.mlj.dao;

import static parc.mlj.dao.config.DAOUtilitaire.fermeturesSilencieuses;
import static parc.mlj.dao.config.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import parc.mlj.beans.Partner;
import parc.mlj.beans.User;
import parc.mlj.dao.config.DAOException;


public class PartnerDaoImpl implements PartnerDAO {
	
	private static final String SQL_SELECT_PAR_ID	=  "SELECT * FROM partners WHERE id_partner = ?";
	
	private DAOFactory daoFactory;
	
	PartnerDaoImpl( DAOFactory daoFactory ){
		this.daoFactory = daoFactory;
	}
	
	public Partner trouver( long id ) throws DAOException {
		return trouver( SQL_SELECT_PAR_ID, id);
	}
	
	private Partner trouver(String sql, Object... objects) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Partner partner = null;
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objects);
			resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ){
				partner = map(resultSet);
			}
		} catch (SQLException e){
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion);
		}
		return partner;
	}
	
	private static Partner map( ResultSet resultSet) throws SQLException {
		Partner partner = new Partner();
		partner.setId( resultSet.getLong( "id_partner" ));
		partner.setName( resultSet.getString( "name_partner" ));
		partner.setPhone( resultSet.getString( "phone_partner" ));
		return partner;
	}
	
}