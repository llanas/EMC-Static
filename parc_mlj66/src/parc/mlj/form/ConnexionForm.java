package parc.mlj.form;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import parc.mlj.beans.User;
import parc.mlj.dao.UserDAO;
import parc.mlj.dao.config.DAOException;

/**
 * Servlet implementation class ConnexionForm
 */
@WebServlet("/ConnexionForm")
public class ConnexionForm extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String CHAMP_LOGIN  = "login";
    private static final String CHAMP_PASSWORD   = "password";
    private String resultat;
    private static Map<String, String> erreurs = new HashMap<String, String>();
    private UserDAO userDao;
    
    public ConnexionForm(){
    	
    }
    
    public ConnexionForm( UserDAO userDao){
    	this.userDao = userDao;
    }
    
    public String getResultat() {
    	return resultat;
    }
    
    public Map<String, String> getErreurs() {
    	return erreurs;
    }
    
    public User ConnexionUser( HttpServletRequest request) {
    	
    	erreurs.clear();
    	
    	String login = getValeurChamp(request, CHAMP_LOGIN);
    	String password = getValeurChamp(request, CHAMP_PASSWORD);
    	
    	User user = new User();
    	
    	traiterLogin(login, user);
    	traiterPassword(password, user);
    
    	try{
    		if(erreurs.isEmpty()){
    			user = userDao.connecter(login, password);
    			resultat = "Succès de la connexion!";
    		} else {
    			resultat = "Erreur lors de la connexion.";
    		}
    	} catch (DAOException e){
    		setErreur("Imprevu", "Erreur imprevu lors de la connexion de l'utilisateur");
    		resultat = "Identifiant ou mot de passe invalide.";
    		e.printStackTrace();
    	}
    	
    	return user;
    }
    
    /* Traitement des données */
    
    
    private void traiterLogin( String login, User user){
    	
    	try {
    		validationLogin( login );
    	} catch ( Exception e ) {
    		setErreur( CHAMP_LOGIN, e.getMessage() );
    	}
    }
    
    private void traiterPassword( String password, User user){
    	
    	try {
    		validationPassword( password );
    	} catch (Exception e) {
    		setErreur( CHAMP_PASSWORD, e.getMessage() );
    	}
    }
    
    /* Validation des données traiter */
    
    private void validationPassword(String password) {
		
	}

	private void validationLogin(String login) {
		
	}

	private static String getValeurChamp( HttpServletRequest request, String champ){
		if (champ == null) {
			champ = "";
		}
		String valeur = request.getParameter( champ );
		if( valeur == null || valeur.trim().length() == 0){
			return null;
		} else {
			return valeur.trim();
		}
	}
    
    private static void setErreur( String champ, String message ) {
    	erreurs.put( champ, message );
    }

}
