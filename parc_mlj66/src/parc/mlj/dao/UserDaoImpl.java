package parc.mlj.dao;

import static parc.mlj.dao.config.DAOUtilitaire.fermeturesSilencieuses;
import static parc.mlj.dao.config.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.ResultSetMetaData;

import parc.mlj.beans.TypeProfil;
import parc.mlj.beans.User;
import parc.mlj.dao.config.DAOException;


public class UserDaoImpl implements UserDAO {

	private static final String SQL_SELECT 			= "SELECT * FROM users ORDER BY id_user";
	private static final String SQL_CONNECT			= "SELECT id_user FROM users WHERE log_user = ? AND pwd_user = ?";
	private static final String SQL_SELECT_BY_ID	= "SELECT * FROM users WHERE id_user = ?";
	
	private DAOFactory daoFactory;
	
	UserDaoImpl( DAOFactory daoFactory ){
		this.daoFactory = daoFactory;
	}
	
	public User connecter( String log, String pwd ) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		long userId = 0;
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_CONNECT, true, log, pwd );
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				userId = (long) resultSet.getInt(1);	
				return trouver( userId );
			} else {
				throw new DAOException("Erreur lors de la requête SQL.");
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
	
	@Override
	public List<User> lister() throws DAOException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<User> users = new ArrayList<User>();
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while( resultSet.next() ){
				users.add(map(resultSet));
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return users;
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
