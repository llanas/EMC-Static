package form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import beans.User;
import dao.UserDAO;
import dao.config.DAOException;

@WebServlet("/AuthentificationForm")
public class AuthentificationForm extends HttpServlet {

	private static final long serialVersionUID 				= 1L;
	private static final String CHAMP_LOGIN_ADMIN			= "login_admin";
	private static final String CHAMP_PASSWORD_ADMIN		= "password_admin";
	private static final String CHAMP_PASSWORD				= "password_user";
	private String resultat;
	private static Map<String, String> erreurs = new HashMap<String, String>();
	private UserDAO userDao;
	
	public AuthentificationForm() {
		
	}
	
	public AuthentificationForm( UserDAO userDao) {
		this.userDao = userDao;
	}
	
	public String getResultat() {
		return resultat;
	}
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public User ConnexionAdmin( HttpServletRequest request ) {
		
		erreurs.clear();
		
		String loginAdmin = getValeurChamp(request, CHAMP_LOGIN_ADMIN);
		String passwordAdmin = getValeurChamp(request, CHAMP_PASSWORD_ADMIN);
		
		User user= new User();
		
		traiterLoginAdmin(loginAdmin, user);
		traiterPasswordAdmin(passwordAdmin, user);
		
		try{
			if(erreurs.isEmpty()) {
				user = userDao.authentificationAdmin(loginAdmin, passwordAdmin);
				resultat = "Authentification validé";
			} else {
				resultat = "Echec de l'authentifaction";
			}
		} catch (DAOException e) {
			setErreur("Imprévu", "Erreur imprévu lors de l'authentification administrateur");
			resultat = "Identifiant ou mot de passe incorrect";
			e.printStackTrace();
		}
		
		return user;
	}
	
public User Connexion( HttpServletRequest request ) {
		
		erreurs.clear();
		
		String password = getValeurChamp(request, CHAMP_PASSWORD);
		
		User user= new User();
		
		traiterPassword(password, user);
		
		try{
			if(erreurs.isEmpty()) {
				user = userDao.authentification(password);
				resultat = "Authentification validé";
			} else {
				resultat = "Echec de l'authentifaction";
			}
		} catch (DAOException e) {
			setErreur("Imprévu", "Erreur imprévu lors de l'authentification administrateur");
			resultat = "Mot de passe incorrect";
			e.printStackTrace();
		}
		
		return user;
	}
	
	/* Traiter les données */

	private void traiterLoginAdmin( String mdp, User user ) {
		
		try{
			validationLoginAdmin( mdp );
		} catch (Exception e) {
			setErreur( CHAMP_PASSWORD_ADMIN, e.getMessage() );
		}
	}
	
	private void validationLoginAdmin( String mdp ) {
		
	}
	
	private void traiterPasswordAdmin( String mdp, User user ) {
		
		try{
			validationPasswordAdmin( mdp );
		} catch (Exception e) {
			setErreur( CHAMP_PASSWORD_ADMIN, e.getMessage() );
		}
	}
	
	private void validationPasswordAdmin( String mdp ) {
		
	}
	
	private void traiterPassword( String mdp, User user ) {
		
		try{
			validationPassword( mdp );
		} catch (Exception e) {
			setErreur( CHAMP_PASSWORD_ADMIN, e.getMessage() );
		}
	}
	
	private void validationPassword( String mdp ) {
		
	}
	
	private static String getValeurChamp( HttpServletRequest request, String champ ) {
		if( champ == null ) {
			champ = "";
		}
		String valeur = request.getParameter( champ );
		if( valeur == null || valeur.trim().length() == 0 ) {
			return null;
		} else {
			return valeur.trim();
		}
	}
	
	private static void setErreur( String champ, String message ) {
		erreurs.put( champ, message );
	} 
	
	
}
