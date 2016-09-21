package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import dao.DAOFactory;
import dao.UserDAO;
import form.AuthentificationForm;

public class Authentification extends HttpServlet {
	private static final long serialVersionUID 		= 1L;
	private static final String CONF_DAO_FACTORY	= "DAOFactory";
	
	private static final String VUE_ECHEC			= "/WEB-INF/accueil.jsp";
    private static final String VUE_SUCCES			= "/espace";
    
    private static final String ATT_FORM 			= "form";
    private static final String ATT_USER 			= "user";
    private static final String ATT_USER_SESSION	= "sessionUser";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authentification() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private UserDAO userDao;
    
    public void init() throws ServletException {
    	this.userDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDAO();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher(VUE_ECHEC).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AuthentificationForm form = new AuthentificationForm( userDao );
		
		User user = form.Connexion(request);
		
		HttpSession session = request.getSession();
		
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, user);
		
		if( form.getErreurs().isEmpty() ) {
			session.setAttribute(ATT_USER_SESSION, user);
			this.getServletContext().getRequestDispatcher( VUE_SUCCES ).forward(request, response);
		} else {
			session.setAttribute(ATT_USER_SESSION, null);
			this.getServletContext().getRequestDispatcher( VUE_ECHEC ).forward(request, response);
		}
		
		
	}

}
