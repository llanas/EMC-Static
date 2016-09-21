package dao;

import java.util.List;

import beans.Groupe;
import dao.config.DAOException;

public interface GroupeDAO {

	Groupe trouver( Long id ) throws DAOException;
	
	List<Groupe> lister();
	
	void creer( Groupe groupe ) throws DAOException;
	
	void supprimer( Long id ) throws DAOException;
	
}
