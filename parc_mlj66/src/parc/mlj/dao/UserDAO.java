package parc.mlj.dao;

import java.util.List;

import parc.mlj.beans.User;
import parc.mlj.dao.config.DAOException;

public interface UserDAO {
	
	User connecter( String login, String password ) throws DAOException;

	User trouver( long id ) throws DAOException;

	List<User> lister() throws DAOException;

	void creer(User user);
	
}
