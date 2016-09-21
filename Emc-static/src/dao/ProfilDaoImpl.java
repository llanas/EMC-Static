package dao;

import static dao.config.DAOUtilitaire.fermeturesSilencieuses;
import static dao.config.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Profil;
import dao.config.DAOException;

public class ProfilDaoImpl implements ProfilDAO {

	/* Déclaration des noms des champs de la base de données */
	private static final String SQL_ID 				= "id_profil";
	private static final String SQL_NOM				= "nom_profil";
	private static final String SQL_DROIT			= "droit_profil";
	
	/* Déclaration des requêtes SQL */
	private static final String SQL_SELECT_BY_ID 	= "SELECT * FROM type_profil WHERE id_profil = ?";
	private static final String SQL_SELECT			= "";
	private static final String SQL_DELETE_BY_ID	= "";
	
	
	/* Constructeur */
	private DAOFactory daoFactory;
	
	ProfilDaoImpl( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Profil trouver( Long id ) throws DAOException {
		return trouver( SQL_SELECT_BY_ID, id);
	}
	
	private Profil trouver( String sql, Object... objects) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Profil profil = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, sql, false, objects );
			resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ) {
				profil = map( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return profil;
	}

	@Override
	public List<Profil> lister() {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Profil> profils = new ArrayList<Profil>();
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement( SQL_SELECT );
			resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ) {
				profils.add( map( resultSet ) );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return profils;
	}

	@Override
	public void creer( Profil profil ) throws DAOException {
	
		// NO NEED
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

	private Profil map( ResultSet resultSet ) throws SQLException {
		
		Profil profil = new Profil();
		
		profil.setId( resultSet.getLong( SQL_ID ) );
		profil.setNom( resultSet.getString( SQL_NOM ) );
		profil.setDroit( resultSet.getInt( SQL_DROIT ) );
		
		return profil;
		
	}
}
