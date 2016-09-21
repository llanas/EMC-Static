package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import dao.config.DAOConfigurationException;

public class DAOFactory {
	
	private static final String FICHIER_PROPERTIES 			= "/dao/dao.properties";
	private static final String PROPERTY_URL				= "url";
	private static final String PROPERTY_DRIVER				= "driver";
	private static final String PROPERTY_NOM_UTILISATEUR	= "nomutilisateur";
	private static final String PROPERTY_MDP				= "motdepasse";
	
	private String url;
	private String userName;
	private String passWord;
	
	DAOFactory( String url, String userName, String passWord ) {
		this.url = url;
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public static DAOFactory getInstance() throws DAOConfigurationException {
		
		Properties properties = new Properties();
		String url;
		String driver;
		String nomUtilisateur;
		String motDePasse;
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);
		
		if( fichierProperties == null ) {
			throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.");
		}
		
		try {
			properties.load( fichierProperties );
			url = properties.getProperty( PROPERTY_URL );
			driver = properties.getProperty( PROPERTY_DRIVER );
			nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
			motDePasse = properties.getProperty( PROPERTY_MDP );
		} catch (IOException e ) {
			throw new DAOConfigurationException( "Impossible de changer le fichier " + FICHIER_PROPERTIES + "." + e );
		}
		
		try {
			Class.forName( driver );
		} catch ( ClassNotFoundException e ) {
			throw new DAOConfigurationException( "Le driver de connexion à la base est introuvable dans le classpath." + e );
		}
		
		DAOFactory instance = new DAOFactory(url, nomUtilisateur, motDePasse);
		return instance;
	}
	
	/* package */ Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, userName, passWord);
	}

	public EspaceDAO getEspaceDAO() {
		return new EspaceDaoImpl( this );
	}

	public UserDAO getUserDAO() {
		return new UserDaoImpl( this );
	}
	
	public FichierDAO getFichierDAO() {
		return new FichierDaoImpl( this );
	}

	public GroupeDAO getGroupeDAO() {
		return new GroupeDaoImpl( this );
	}

	public ProfilDAO getProfilDAO() {
		return new ProfilDaoImpl( this );
	}
	
	public LienFichierDAO getLienFichierDAO() {
		return new LienFichierDaoImpl( this );
	}

}
