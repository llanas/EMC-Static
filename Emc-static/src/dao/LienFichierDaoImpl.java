package dao;

import static dao.config.DAOUtilitaire.fermeturesSilencieuses;
import static dao.config.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.LienFichier;
import dao.config.DAOException;

public class LienFichierDaoImpl implements LienFichierDAO {
	
	/* Déclaration des champs de la base de donnée */
	private static final String SQL_USER			= "user_lien";
	private static final String SQL_FICHIER			= "fichier_lien";
	
	/* Déclaration des requêtes SQL */
	private static final String SQL_SELECT_BY_ID 	= "";
	private static final String SQL_SELECT			= "";
	private static final String SQL_INSERT 			= "";
	private static final String SQL_DELETE_BY_ID	= "";
	private static final String SQL_SELECT_BY_USER	= "SELECT * FROM lien_user_fichier WHERE user_lien = ?";
	
	/* Constructeur */
	private DAOFactory daoFactory;
	
	LienFichierDaoImpl( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}

	@Override
	public LienFichier trouver( Long id ) throws DAOException {
		return trouver( SQL_SELECT_BY_ID, id);
	}
	
	private LienFichier trouver( String sql, Object... objects) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		LienFichier lienFichier= null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, sql, false, objects );
			resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ) {
				lienFichier = map( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return lienFichier;
	}

	@Override
	public List<LienFichier> lister() {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<LienFichier> lienFichiers = new ArrayList<LienFichier>();
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement( SQL_SELECT );
			resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ) {
				lienFichiers.add( map( resultSet ) );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return lienFichiers;
	}

	@Override
	public void creer( LienFichier lienFichier ) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeurAutoGeneree = null;
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
					lienFichier.getUser(),
					lienFichier.getFichier() );
			int statut = preparedStatement.executeUpdate();
			if( statut == 0 ) {
				throw new DAOException( "Echec de la création du lien entre le fichier et l'utilisateur." );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses(valeurAutoGeneree, preparedStatement, connexion);
		}

	}

	@Override
	public void supprimer( Long id ) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_DELETE_BY_ID, false, id);
			int statut = preparedStatement.executeUpdate();
			if( statut == 0 ) {
				throw new DAOException( "Echec lors de la suppression.");
			}
		} catch ( SQLException e ) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}

	}
	
	@Override
	public List<LienFichier> trouverFichier(Long id) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<LienFichier> liensFichier = new ArrayList<LienFichier>();
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_BY_USER, false, id);
			resultSet = preparedStatement.executeQuery();
			while( resultSet.next() ){
				liensFichier.add( map(resultSet) );
			}
		} catch ( SQLException e ){
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return liensFichier;
	}

	private LienFichier map( ResultSet resultSet ) throws SQLException {
		
		LienFichier lienFichier = new LienFichier();
		
		UserDAO userDao = daoFactory.getUserDAO();
		lienFichier.setUser( userDao.trouver( resultSet.getLong( SQL_USER ) ) );
		
		FichierDAO fichierDao = daoFactory.getFichierDAO();
		lienFichier.setFichier( fichierDao.trouver( resultSet.getLong( SQL_FICHIER ) ) );
		
		return lienFichier;
	}

	
	

}
