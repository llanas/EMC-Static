package parc.mlj.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parc.mlj.beans.User;
import parc.mlj.dao.DAOFactory;
import parc.mlj.dao.UserDAO;
import parc.mlj.form.ConnexionForm;


public class Connexion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "DAOFactory";
	public static final String ATT_USER = "user";
	public static final String ATT_USER_SESSION = "sessionUser";
	public static final String ATT_FORM = "form";
	public static final String VUE_SUCCES = "/WEB-INF/profil.jsp";
	public static final String VUE_ECHEC = "/WEB-INF/accueil.jsp";
       
    private UserDAO userDao;
    
    public void init() throws ServletException {
    	this.userDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		this.getServletContext().getRequestDispatcher(VUE_ECHEC).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ConnexionForm form = new ConnexionForm( userDao );
		
		User user = form.ConnexionUser(request);
		
		HttpSession session = request.getSession();
		
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, user);
		
		if(form.getErreurs().isEmpty()){
			session.setAttribute( ATT_USER_SESSION, user);
			this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response);
		} else {
			session.setAttribute( ATT_USER_SESSION, null);
			this.getServletContext().getRequestDispatcher(VUE_ECHEC).forward(request, response);
		}
	}

}
