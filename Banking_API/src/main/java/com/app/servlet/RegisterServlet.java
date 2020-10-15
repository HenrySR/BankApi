package com.app.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dao.RegisterDAO;
import com.app.dao.impl.RegisterDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Role;
import com.app.model.User;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		
		User user =(User) session.getAttribute("user");
		
		RegisterDAO register = new RegisterDAOImpl();
		User newUser = new User();
		RequestDispatcher requestDispatcher=null;
		PrintWriter out = response.getWriter();
		
		try {
			newUser.setUsername(request.getParameter("username"));
			newUser.setPassword(request.getParameter("password"));
			newUser.setFirstName(request.getParameter("fname"));
			newUser.setLastName(request.getParameter("lname"));
			newUser.setEmail(request.getParameter("email"));
			Role role = new Role(Integer.parseInt(request.getParameter("role")));
			newUser.setRole(role);
			register.registerUser(newUser);
		} catch (BusinessException | NumberFormatException e) {
			requestDispatcher=request.getRequestDispatcher("Register.html");
			requestDispatcher.include(request, response);
			out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
		}
		

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher=null;
		requestDispatcher=request.getRequestDispatcher("Register.html");
		requestDispatcher.include(request, response);
	}

}
