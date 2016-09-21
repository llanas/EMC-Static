package dao;

import static dao.config.DAOUtilitaire.fermeturesSilencieuses;
import static dao.config.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Espace;
import dao.config.DAOException;

public class EspaceDaoImpl implements EspaceDAO {
	
	/* Déclaration des noms des champs en base de donnée */
	private static final String SQL_ID				= "id_espace";
	private static final String SQL_NOM 			= "nom_espace";
	
	/* Déclaration des requêtes SQL */
	private static final String SQL_SELECT_BY_ID 	= "SELECT * FROM espaces WHERE id = ?";
	private static final String SQL_SELECT			= "SELECT * FROM espaces ORDER BY id_espace";
	private static final String SQL_INSERT 			= "";
	private static final String SQL_DELETE_BY_ID	= "";
	
	/* Constructeur */
	private DAOFactory daoFactory;
	EspaceDaoImpl( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Espace trouver( Long id ) throws DAOException {
		return trouver( SQL_SELECT_BY_ID, id);
	}
	
	private Espace trouver( String sql, Object... objects) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Espace espace = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, sql, false, objects );
			resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ) {
				espace = map( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return espace;
	}

	@Override
	public List<Espace> lister() {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Espace> espaces = new ArrayList<Espace>();
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement( SQL_SELECT );
			resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ) {
				espaces.add( map( resultSet ) );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return espaces;
	}

	@Override
	public void creer(Espace espace) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeurAutoGeneree = null;
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
					espace.getNom() );
			int statut = preparedStatement.executeUpdate();
			if( statut == 0 ) {
				throw new DAOException( "Echec de la création de l'espace de stockage." );
			}
			valeurAutoGeneree = preparedStatement.getGeneratedKeys();
			if( valeurAutoGeneree.next() ) {
				espace.setId( valeurAutoGeneree.getLong(1) );
			} else {
				throw new DAOException( "Echec de la création de l'utilisateur, aucun ID auto-généré." );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses(valeurAutoGeneree, preparedStatement, connexion);
		}

	}

	@Override
	public void supprimer(Long id) throws DAOException {
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

	private Espace map( ResultSet resultSet ) throws SQLException {
		
		Espace espace = new Espace();
		
		espace.setId( resultSet.getLong(SQL_ID));
		espace.setNom( resultSet.getString(SQL_NOM));
		
		return espace;
	}
	
}
