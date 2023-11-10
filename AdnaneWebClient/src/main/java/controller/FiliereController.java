package controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.IDaoLocal;
import entities.Filiere;
import entities.Role;

/**
 * Servlet implementation class FiliereController
 */
@WebServlet("/FiliereController")
public class FiliereController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiliereController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    @EJB(beanName = "FILIERE_EJB")
	   private IDaoLocal<Filiere> daofilieres;
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if(action==null) {
			request.setAttribute("listFilieres", daofilieres.findAll());
		
			request.getRequestDispatcher("indexFiliere.jsp").forward(request, response);
		}
		
		else {
			if(action.equalsIgnoreCase("add")) {
				
				request.getRequestDispatcher("addFiliere.jsp").forward(request, response);
			}
			
			else if(action.equalsIgnoreCase("update")) {
			
				
				int filiereId = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("filiere", daofilieres.findById(filiereId));
				
			    request.getRequestDispatcher("editFiliere.jsp").forward(request, response);
			    
			}
			
			else if(action.equalsIgnoreCase("delete")) {
				String idString = request.getParameter("id");
			    int id = Integer.parseInt(idString);
			    daofilieres.delete(daofilieres.findById(id));
			    response.sendRedirect("FiliereController");
			   
				
		
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("add")) {
			try {
				
			Filiere filiere = new Filiere();
			filiere.setName(request.getParameter(("name")));
			filiere.setCode(request.getParameter(("code")));
			
			
			daofilieres.create(filiere);
			response.sendRedirect("FiliereController");
			}
			catch(Exception e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("addFiliere.jsp").forward(request, response);
			}
		}

			else if(action.equalsIgnoreCase("update")) {
				try {
					
				int id=Integer.parseInt(request.getParameter("id"));
				
				Filiere filiere = daofilieres.findById(id);
				filiere.setName(request.getParameter(("name")));
				filiere.setCode(request.getParameter(("code")));
				daofilieres.update(filiere);
				response.sendRedirect(request.getContextPath() + "/FiliereController");
				}
				catch(Exception e) {
					request.setAttribute("error", e.getMessage());
					request.getRequestDispatcher("editFiliere.jsp").forward(request, response);
				}
		}


	}

}
