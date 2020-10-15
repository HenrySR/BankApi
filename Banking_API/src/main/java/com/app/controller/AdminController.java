package com.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dao.SearchDAO;
import com.app.dao.impl.SearchDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.User;
import com.app.service.Search;
import com.app.service.impl.SearchImpl;

/**
 * Servlet implementation class AdminController
 */
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);
		if(session==null) {
			response.sendError(401,"Invalid Credentials");
		}
		else {	
		User user =(User) session.getAttribute("user");
		if(user.getRole().getRoleId()!=1) {
		RequestDispatcher requestDispatcher=null;
		PrintWriter out=response.getWriter();
		
			try {
				SearchDAO search = new SearchDAOImpl();
				String type= request.getParameter("type");
				requestDispatcher=request.getRequestDispatcher("admin.html");
				requestDispatcher.include(request, response);
				switch(type) {
				case "id":
					List<User> userList = search.searchId(Integer.parseInt((request.getParameter("search"))));
					for(User u:userList) {
						out.print("<h4>User List "+u.toString()+"</h4>");
					}
					break;
				case "fname":
					List<User> userList1 = search.searchFname((request.getParameter("search")));
					for(User u:userList1) {
						out.print("<h4>User List "+u.toString()+"</h4>");
					}
					break;
				case "lname":
					List<User> userList2 = search.searchLname((request.getParameter("search")));
					for(User u:userList2) {
						out.print("<h4>User List "+u.toString()+"</h4>");
					}
					break;
				case "username":
					
					List<User> userList3 = search.searchUsername((request.getParameter("search")));
					for(User u:userList3) {
						out.print("<h4>User List "+u.toString()+"</h4>");
					}
					break;
				case "all":
					List<User> userList4 = search.allUsers();
					for(User u:userList4) {
						out.print("<h4>User List "+u.toString()+"</h4>");
					}
					break;
				}
				
				
				
			} catch (BusinessException e) {
//				requestDispatcher=request.getRequestDispatcher("admin");
//				requestDispatcher.include(request, response);
				out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
			}
			catch(NumberFormatException e) {
//				requestDispatcher=request.getRequestDispatcher("admin.html");
//				requestDispatcher.include(request, response);
				out.print("<center><span style='color:red;'> Please Enter a number for Id </span></center>");
			}
			}
			
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
