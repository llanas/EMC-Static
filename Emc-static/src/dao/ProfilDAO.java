package dao;

import java.util.List;

import beans.Profil;
import dao.config.DAOException;

public interface ProfilDAO {

	Profil trouver( Long id ) throws DAOException;
	
	List<Profil> lister();
	
	void creer( Profil profil ) throws DAOException;
	
	void supprimer( Long id ) throws DAOException;

}
