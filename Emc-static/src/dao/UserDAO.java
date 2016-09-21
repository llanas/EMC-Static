package dao;

import java.util.List;

import beans.User;
import dao.config.DAOException;

public interface UserDAO {

	User trouver( Long id ) throws DAOException;
	
	List<User> lister() throws DAOException;
	
	void creer( User user );
	
	void supprimer( Long id );

	User authentificationAdmin( String login, String mdp ) throws DAOException;
	
	User authentification( String mdp ) throws DAOException;
 
}
