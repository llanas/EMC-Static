package dao;

import java.util.List;

import beans.Fichier;
import dao.config.DAOException;

public interface FichierDAO {

	Fichier trouver( Long id ) throws DAOException;
	
	List<Fichier> lister();
	
	void creer( Fichier fichier ) throws DAOException;
	
	void supprimer( Long id ) throws DAOException;
}
