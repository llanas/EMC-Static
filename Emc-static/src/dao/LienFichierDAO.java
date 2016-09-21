package dao;

import java.util.List;

import beans.LienFichier;
import dao.config.DAOException;

public interface LienFichierDAO {

	LienFichier trouver( Long id ) throws DAOException;
	
	List<LienFichier> lister();
	
	void creer( LienFichier lienFichier ) throws DAOException;
	
	void supprimer( Long id ) throws DAOException;

	List<LienFichier> trouverFichier(Long id);
}
