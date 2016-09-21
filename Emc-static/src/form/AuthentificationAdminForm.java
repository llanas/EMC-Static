package form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import beans.User;
import dao.UserDAO;
import dao.config.DAOException;

@WebServlet("/AuthentificationAdminForm")
public class AuthentificationAdminForm extends HttpServlet {

	private static final long serialVersionUID 	= 1L;
	private static final String CHAMP_ID_ADMIN		= "log_admin";
	private static final String CHAMP_MDP_ADMIN		= "mdp_admin";
	private String resultat;
	private static Map<String, String> erreurs = new HashMap<String, String>();
	private UserDAO userDao;
	
	public AuthentificationAdminForm() {
		
	}
	
	public AuthentificationAdminForm( UserDAO userDao) {
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
		
		String login = getValeurChamp(request, CHAMP_ID_ADMIN);
		String mdp = getValeurChamp(request, CHAMP_MDP_ADMIN);
		
		User user= new User();
		
		traiterLoginAdmin(login, user);
		traiterMdpAdmin(mdp, user);
		
		try{
			if(erreurs.isEmpty()) {
				user = userDao.authentificationAdmin(login, mdp);
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
	
	
	/* Traiter les données */
	
	private void traiterLoginAdmin( String login, User user ) {
		
		try{
			validationLogin( login );
		} catch (Exception e) {
			setErreur( CHAMP_ID_ADMIN, e.getMessage() );
		}
	}
	
	private void traiterMdpAdmin( String mdp, User user ) {
		
		try{
			validationMdp( mdp );
		} catch (Exception e) {
			setErreur( CHAMP_MDP_ADMIN, e.getMessage() );
		}
	}
	
	private void validationLogin( String login ) {
		
	}
	
	private void validationMdp( String mdp ) {
		
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
