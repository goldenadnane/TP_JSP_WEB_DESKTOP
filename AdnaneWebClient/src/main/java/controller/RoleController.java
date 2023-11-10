package controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.IDaoLocal;
import entities.Filiere;
import entities.Role;
import entities.Student;

/**
 * Servlet implementation class RoleController
 */

       

   
@WebServlet("/RoleController")
public class RoleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	   @EJB(beanName = "ROLE_EJB")
	   private IDaoLocal<Role> daoroles;
	   

	   
    public RoleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action==null) {
			request.setAttribute("listRoles", daoroles.findAll());
		
			request.getRequestDispatcher("indexRole.jsp").forward(request, response);
		}
		
		else {
			if(action.equalsIgnoreCase("add")) {
				
				request.getRequestDispatcher("addRole.jsp").forward(request, response);
			}
			
			else if(action.equalsIgnoreCase("update")) {
			
				
				int roleId = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("role", daoroles.findById(roleId));
				
			    request.getRequestDispatcher("editRole.jsp").forward(request, response);
			    
			}
			
			else if(action.equalsIgnoreCase("delete")) {
				String idString = request.getParameter("id");
			    int id = Integer.parseInt(idString);
			    daoroles.delete(daoroles.findById(id));
			    response.sendRedirect("RoleController");
			   
				
		
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
				
			Role role = new Role();
			role.setName(request.getParameter(("name")));
			
			daoroles.create(role);
			response.sendRedirect("RoleController");
			}
			catch(Exception e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("addRole.jsp").forward(request, response);
			}
		}

			else if(action.equalsIgnoreCase("update")) {
				try {
					
				int id=Integer.parseInt(request.getParameter("id"));
				
				Role role = daoroles.findById(id);
				role.setName(request.getParameter(("name")));
				daoroles.update(role);
				response.sendRedirect(request.getContextPath() + "/RoleController");
				}
				catch(Exception e) {
					request.setAttribute("error", e.getMessage());
					request.getRequestDispatcher("editRole.jsp").forward(request, response);
				}
		}


	
	}

}
