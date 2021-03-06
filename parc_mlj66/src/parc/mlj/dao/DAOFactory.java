package parc.mlj.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import parc.mlj.dao.config.DAOConfigurationException;

public class DAOFactory {

	
	private static final String FICHIER_PROPERTIES       = "/parc/mlj/dao/dao.properties";
	private static final String PROPERTY_URL             = "url";
	private static final String PROPERTY_DRIVER          = "driver";
	private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
	private static final String PROPERTY_MOT_DE_PASSE    = "motdepasse";
	
	private String url;
	private String username;
	private String password;
	
	DAOFactory( String url, String username, String password ) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public static DAOFactory getInstance() throws DAOConfigurationException {
		Properties properties = new Properties();
		String url;
		String driver;
		String nomUtilisateur;
		String motDePasse;
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
		
		if ( fichierProperties == null ) {
			throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.");
		}
		
		try {
			properties.load( fichierProperties );
			url = properties.getProperty( PROPERTY_URL );
			driver = properties.getProperty( PROPERTY_DRIVER );
			nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
			motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE);
		} catch ( IOException e ) {
			throw new DAOConfigurationException( "Impossible de charger le fichier " + FICHIER_PROPERTIES + ".", e);
		}
		
		try {
			Class.forName( driver );
		} catch (ClassNotFoundException e ) {
			throw new DAOConfigurationException( "Le driver de connexion à la base est introuvble dans le classpath.", e);
		}
		
		DAOFactory instance = new DAOFactory( url, nomUtilisateur, motDePasse );
		return instance;
	}
	
	/* package */ Connection getConnection() throws SQLException {
		return DriverManager.getConnection( url, username, password );
	}
	
	public TypeProfilDAO getTypeProfilDAO() {
		return new TypeProfilDaoImpl( this );
	}

	public PartnerDAO getPartnerDAO() {
		return new PartnerDaoImpl( this );
	}
	
	public AddressDAO getAddressDAO() {
		return new AddressDaoImpl( this );
	}

	public LocationDAO getLocationDAO() {
		return new LocationDaoImpl( this );
	}
	
	public UserDAO getUserDAO() {
		return new UserDaoImpl( this );
	}
	
	public ColonnesDAO getColonnesDAO() {
		return new ColonnesDaoImpl( this );
	}
}
