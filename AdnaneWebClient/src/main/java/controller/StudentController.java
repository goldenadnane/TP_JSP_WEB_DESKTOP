package controller;

import jakarta.ejb.EJB;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.IDao;
import dao.IDaoLocal;
import entities.Filiere;
import entities.Student;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   @EJB(beanName = "STUDENT_EJB")
   private IDaoLocal<Student> dao;
   
   @EJB(beanName = "FILIERE_EJB")
   private IDaoLocal<Filiere> daoFilieres;
   
  
    public StudentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action==null) {
			request.setAttribute("listStudents", dao.findAll());
		
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		else {
			if(action.equalsIgnoreCase("add")) {
				List<Integer> filiereIds = new ArrayList<>();
		        for(Filiere f : daoFilieres.findAll()) {
		        	
		            filiereIds.add(f.getId());
		        }
		        request.setAttribute("listFilieres", filiereIds);
				request.getRequestDispatcher("addStudent.jsp").forward(request, response);
			}
			
			else if(action.equalsIgnoreCase("update")) {
			
				
				int studentId = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("student", dao.findById(studentId));
				List<Integer> filiereIds = new ArrayList<>();
		        for(Filiere f : daoFilieres.findAll()) {
		        	
		            filiereIds.add(f.getId());
		        }
		        request.setAttribute("listFilieres", filiereIds);
			    request.getRequestDispatcher("edit.jsp").forward(request, response);
			    
			}
			
			else if(action.equalsIgnoreCase("delete")) {
				String idString = request.getParameter("id");
			    int id = Integer.parseInt(idString);
			    dao.delete(dao.findById(id));
			    response.sendRedirect("StudentController");
			   
				
		
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
				
			Student student = new Student();
			student.setLogin(request.getParameter(("login")));
			student.setPassword(request.getParameter(("password")));
			student.setFirstname(request.getParameter(("firstname")));
			student.setLastname(request.getParameter(("lastname")));
			student.setTelephone(request.getParameter(("telephone")));
			int filiereId = Integer.parseInt(request.getParameter("filiereId"));
			Filiere filiere = daoFilieres.findById(filiereId);
	        student.setFiliere(filiere);
			dao.create(student);
			response.sendRedirect("StudentController");
			}
			catch(Exception e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("addStudent.jsp").forward(request, response);
			}
		}

			else if(action.equalsIgnoreCase("update")) {
				try {
					
				int id=Integer.parseInt(request.getParameter("id"));
				
				Student student = dao.findById(id);
				student.setLogin(request.getParameter(("login")));
				student.setPassword(request.getParameter(("password")));
				student.setFirstname(request.getParameter(("firstname")));
				student.setLastname(request.getParameter(("lastname")));
				student.setTelephone(request.getParameter(("telephone")));
				Filiere filiere = daoFilieres.findById(id);
				dao.update(student);
				response.sendRedirect(request.getContextPath() + "/StudentController");
				}
				catch(Exception e) {
					request.setAttribute("error", e.getMessage());
					request.getRequestDispatcher("edit.jsp").forward(request, response);
				}
		}


	}
}
