package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import beans.User;
import dao.UserDAO;
import form.AuthentificationForm;


public class AuthentificationAdmin extends HttpServlet {
	
	private static final long serialVersionUID		= 1L;
	private static final String CONF_DAO_FACTORY	= "DAOFactory";
	private static final String VUE_ECHEC 			= "/WEB-INF/authentificationAdmin.jsp";
	private static final String VUE					= "/WEB-INF/gestion.jsp";
	private static final String ATT_FORM 			= "form";
	private static final String ATT_USER			= "user";
	private static final String ATT_USER_SESSION	= "sessionUser";
	
	private UserDAO userDao;
    
	public void init() throws ServletException {
    	this.userDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDAO();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher(VUE_ECHEC).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AuthentificationForm form = new AuthentificationForm( userDao );
		
		User user = form.ConnexionAdmin(request);
		
		HttpSession session = request.getSession();
		
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, user);
		
		if(form.getErreurs().isEmpty()) {
			session.setAttribute(ATT_USER_SESSION, user);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		} else {
			session.setAttribute(ATT_USER_SESSION, null);
			this.getServletContext().getRequestDispatcher(VUE_ECHEC).forward(request, response);
		}
	}

}
