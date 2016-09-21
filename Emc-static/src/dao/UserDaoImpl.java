package dao;

import static dao.config.DAOUtilitaire.fermeturesSilencieuses;
import static dao.config.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import dao.config.DAOException;

public class UserDaoImpl implements UserDAO {
	
	/* Déclaration des champs de la base de donnée */
	private static final String SQL_ID				= "id_user";
	private static final String SQL_GROUPE			= "group_user";
	private static final String SQL_PROFIL			= "profil_user";
	private static final String SQL_PRENOM			= "prenom_user";
	private static final String SQL_NOM				= "nom_user";
	private static final String SQL_MDP				= "mdp_user";
	private static final String SQL_PHONE			= "phone_user";
	private static final String SQL_MOBILE			= "mobile_user";
	private static final String SQL_MAIL			= "mail_user";
	private static final String SQL_FONCTION		= "fonction_user";
	private static final String SQL_CHAMP			= "champ_user";
	private static final String SQL_CHAMP1			= "champ1_user";
	private static final String SQL_CHAMP2			= "champ2_user";

	/* Déclaration des requêtes SQL */
	private static final String SQL_SELECT_BY_ID 	= "SELECT * FROM users WHERE id_user = ?";
	private static final String SQL_SELECT			= "";
	private static final String SQL_INSERT 			= "";
	private static final String SQL_DELETE_BY_ID	= "";
	private static final String SQL_CONNECT			= "SELECT id_user FROM users WHERE mdp_user = ?";
	private static final String SQL_CONNECT_ADMIN	= "SELECT id_user FROM users WHERE nom_user = ? AND mdp_user = ?";
	
	/* Constructeur */
	private DAOFactory daoFactory;
	
	UserDaoImpl( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}

	@Override
	public User trouver( Long id ) throws DAOException {
		return trouver( SQL_SELECT_BY_ID, id);
	}
	
	private User trouver( String sql, Object... objects) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, sql, false, objects );
			resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ) {
				user = map( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return user;
	}
	
	public User authentificationAdmin( String log, String mdp ) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		long userId = 0;
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_CONNECT_ADMIN, true, log, mdp );
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				userId = (long) resultSet.getInt(1);	
				return trouver( userId );
			} else {
				throw new DAOException("Erreur lors de la requête SQL.");
			}
		} catch ( SQLException e ) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses( preparedStatement, connexion );
		}
	}
	
	@Override
	public User authentification(String mdp) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		long userId = 0;
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_CONNECT, true, mdp );
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				userId = (long) resultSet.getInt(1);	
				return trouver( userId );
			} else {
				throw new DAOException("Erreur lors de la requête SQL.");
			}
		} catch ( SQLException e ) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses( preparedStatement, connexion );
		}
	}

	@Override
	public List<User> lister() {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<User> users = new ArrayList<User>();
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement( SQL_SELECT );
			resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ) {
				users.add( map( resultSet ) );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return users;
	}

	@Override
	public void creer( User user ) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeurAutoGeneree = null;
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
					user.getId(),
					user.getGroupe(),
					user.getProfil(),
					user.getPrenom(),
					user.getNom(),
					user.getMdp(),
					user.getPhone(),
					user.getMobile(),
					user.getMail(),
					user.getFonction(),
					user.getChamp(),
					user.getChamp1(),
					user.getChamp2()
					);
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

	private User map( ResultSet resultSet ) throws SQLException {
		
		User user = new User();
		
		Long groupeId = resultSet.getLong( SQL_GROUPE );
		if( groupeId != 0 ){
			GroupeDAO groupeDao = daoFactory.getGroupeDAO();
			user.setGroupe( groupeDao.trouver( groupeId ) );
		} else {
			user.setGroupe(null);
		}
		
		Long profilId = resultSet.getLong( SQL_PROFIL );
		if( profilId != 0 ){
			ProfilDAO profilDao = daoFactory.getProfilDAO();
			user.setProfil( profilDao.trouver( profilId ) );
		} else {
			user.setProfil(null);
		}
		
		user.setId( resultSet.getLong( SQL_ID ) );
		user.setPrenom( resultSet.getString( SQL_PRENOM ) );
		user.setNom( resultSet.getString( SQL_NOM ) );
		user.setMdp( resultSet.getString( SQL_MDP ) );
		user.setPhone( resultSet.getString( SQL_PHONE ) );
		user.setMobile( resultSet.getString( SQL_MOBILE ) );
		user.setMail( resultSet.getString( SQL_MAIL ) );
		user.setFonction( resultSet.getString( SQL_FONCTION ) );
		user.setChamp( resultSet.getString( SQL_CHAMP ) );
		user.setChamp1( resultSet.getString( SQL_CHAMP1 ) );
		user.setChamp2( resultSet.getString( SQL_CHAMP2 ) );
		
		return user;
	}

	

}
