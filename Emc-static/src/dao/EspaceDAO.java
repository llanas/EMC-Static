package dao;

import java.util.List;

import beans.Espace;
import dao.config.DAOException;

public interface EspaceDAO {

	Espace trouver( Long id ) throws DAOException;
	
	List<Espace> lister();
	
	void creer( Espace espace ) throws DAOException;
	
	void supprimer( Long id ) throws DAOException;
	
}
