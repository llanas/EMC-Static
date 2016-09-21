package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.LienFichierDAO;
import beans.LienFichier;
import beans.User;

public class Espace extends HttpServlet {
	private static final long serialVersionUID 	= 1L;
	
	private static final String ATT_USER 		= "user";
	private static final String VUE_SUCCES 		= "/WEB-INF/espace.jsp";
	private static final String VUE_ECHEC		= "/WEB-INF/accueil.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Espace() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			if( request.getAttribute( ATT_USER ) != null ){
				User user = ( User ) request.getAttribute( ATT_USER );
				LienFichierDAO lienFichierDao = DAOFactory.getInstance().getLienFichierDAO();
				List<LienFichier> fichiers = lienFichierDao.trouverFichier( user.getId() );
				request.setAttribute("Fichiers", fichiers);
				this.getServletContext().getRequestDispatcher( VUE_SUCCES ).forward(request, response);
			}
			else if( request.getSession().getAttribute( ATT_USER ) != null ){
				User user = ( User ) request.getAttribute(ATT_USER);
				LienFichierDAO lienFichierDao = DAOFactory.getInstance().getLienFichierDAO();
				List<LienFichier> fichiers = lienFichierDao.trouverFichier( user.getId() );
				request.setAttribute("Fichiers", fichiers);
				this.getServletContext().getRequestDispatcher( VUE_SUCCES ).forward(request, response);
			} else {
				this.getServletContext().getRequestDispatcher( VUE_ECHEC ).forward(request, response);
			}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
