package dao;

import static dao.config.DAOUtilitaire.fermeturesSilencieuses;
import static dao.config.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Groupe;
import dao.config.DAOException;

public class GroupeDaoImpl implements GroupeDAO {
	
	/* Déclaration des noms des champs de la base de donnée */
	private static final String SQL_ID				= "id_groupe";
	private static final String SQL_NOM				= "nom_groupe";

	/* Déclaration des requêtes SQL */
	private static final String SQL_SELECT_BY_ID 	= "SELECT * FROM groupes WHERE id_groupe = ?";
	private static final String SQL_SELECT			= "";
	
	/* Constructeur */
	private DAOFactory daoFactory;
	
	GroupeDaoImpl( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Groupe trouver( Long id ) throws DAOException {
		return trouver( SQL_SELECT_BY_ID, id);
	}
	
	private Groupe trouver( String sql, Object... objects) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Groupe groupe = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, sql, false, objects );
			resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ) {
				groupe = map( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return groupe;
	}

	@Override
	public List<Groupe> lister() {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Groupe> groupes = new ArrayList<Groupe>();
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement( SQL_SELECT );
			resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ) {
				groupes.add( map( resultSet ) );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return groupes;
	}

	@Override
	public void creer(Groupe groupe) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimer(Long id) throws DAOException {
		// TODO Auto-generated method stub

	}

	private Groupe map(ResultSet resultSet) throws SQLException {

		Groupe groupe = new Groupe();
		
		groupe.setId( resultSet.getLong( SQL_ID ));
		groupe.setNom( resultSet.getString( SQL_NOM ));
		
		return groupe;
	}

}
