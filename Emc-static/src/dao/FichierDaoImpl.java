package dao;

import static dao.config.DAOUtilitaire.fermeturesSilencieuses;
import static dao.config.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Fichier;
import dao.config.DAOException;

public class FichierDaoImpl implements FichierDAO {
	
	/* Déclaration des noms des champs de la base de donnée */
	private static final String SQL_ID 				= "id_fichier";
	private static final String SQL_ESPACE			= "espace_fichier";
	private static final String SQL_LIEN			= "lien_fichier";
	private static final String SQL_NOM				= "nom_fichier";
	private static final String SQL_DATE			= "date_fichier";

	/* Déclaration des requêtes SQL */
	private static final String SQL_SELECT_BY_ID 	= "";
	private static final String SQL_SELECT			= "";
	
	/* Constructeur */
	private DAOFactory daoFactory;
	FichierDaoImpl( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Fichier trouver( Long id ) throws DAOException {
		return trouver( SQL_SELECT_BY_ID, id);
	}
	
	private Fichier trouver( String sql, Object... objects) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Fichier fichier = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, sql, false, objects );
			resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ) {
				fichier = map( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return fichier;
	}

	@Override
	public List<Fichier> lister() {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Fichier> fichiers = new ArrayList<Fichier>();
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement( SQL_SELECT );
			resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ) {
				fichiers.add( map( resultSet ) );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return fichiers;
	}

	@Override
	public void creer(Fichier fichier) throws DAOException {
		
	}

	@Override
	public void supprimer(Long id) throws DAOException {
		// TODO Auto-generated method stub

	}

	private Fichier map(ResultSet resultSet) throws SQLException {
		
		Fichier fichier = new Fichier();
		
		fichier.setId( resultSet.getLong( SQL_ID ) );
		fichier.setNom( resultSet.getString( SQL_NOM ) );
		fichier.setLien( resultSet.getString( SQL_LIEN ));
		fichier.setDate( resultSet.getDate( SQL_DATE ));
		
		EspaceDAO espaceDao = daoFactory.getEspaceDAO();
		fichier.setEspace( espaceDao.trouver( resultSet.getLong( SQL_ESPACE ) ) );
		
		return fichier;
		
	}

}
