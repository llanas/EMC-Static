package parc.mlj.dao;

import static parc.mlj.dao.config.DAOUtilitaire.fermeturesSilencieuses;
import static parc.mlj.dao.config.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import parc.mlj.beans.TypeProfil;
import parc.mlj.beans.User;
import parc.mlj.dao.config.DAOException;


public class UserDaoImpl implements UserDAO {

	private static final String SQL_CONNECT			= "SELECT id_user FROM users WHERE log_user = ? AND pwd_user = ?";
	private static final String SQL_SELECT_BY_ID	= "SELECT * FROM Client WHERE id_user = ?";
	
	private DAOFactory daoFactory;
	
	UserDaoImpl( DAOFactory daoFactory ){
		this.daoFactory = daoFactory;
	}
	
	public User connecter( String log, String pwd ) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_CONNECT, true, log, pwd );
			resultSet = preparedStatement.executeQuery();
			long userId = (long) resultSet.getInt(1);
			int statut = preparedStatement.executeUpdate();
			if ( statut == 0 ) {
				throw new DAOException("Echec de la connexion Utilisateur, ce login n'existe pas.");
			} else {
				return trouver( userId );
			}
		} catch ( SQLException e ) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses( preparedStatement, connexion );
		}
		
	}
	
	public User trouver( long id ) throws DAOException {
		return trouver( SQL_SELECT_BY_ID, id);
	}
	
	private User trouver(String sql, Object... objects) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objects);
			resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ){
				user = map(resultSet);
			}
		} catch (SQLException e){
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion);
		}
		return user;
	}

	private User map( ResultSet resultSet ) throws SQLException {
		
		User user = new User();
		user.setId( resultSet.getLong( "id_user" ));
		
		TypeProfilDAO typeProfilDAO = daoFactory.getTypeProfilDAO();
		user.setProfil( typeProfilDAO.trouver( resultSet.getLong( "profil_user" ) ));
		
		LocationDAO locationDAO = daoFactory.getLocationDAO();
		user.setLocation( locationDAO.trouver( resultSet.getLong( "location_user" ) ));
		
		user.setFunction( resultSet.getString( "function_user" ));
		user.setLogin( resultSet.getString( "log_user" ));
		user.setPassword( resultSet.getString( "pwd_user"));
		user.setGender( resultSet.getBoolean( "gender_user" ));
		user.setFirstName( resultSet.getString( "first_name_user" ));
		user.setLastName( resultSet.getString( "last_name_user" ));
		user.setPhone( resultSet.getString( "phone_user" ));
		
		return user;
	}
	
}
