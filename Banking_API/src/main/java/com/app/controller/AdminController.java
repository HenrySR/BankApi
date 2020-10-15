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

import com.app.account.Account;
import com.app.account.AccountStatus;
import com.app.account.AccountType;
import com.app.dao.AccountDAO;
import com.app.dao.SearchDAO;
import com.app.dao.UpdateUserDAO;
import com.app.dao.impl.AccountDAOImpl;
import com.app.dao.impl.SearchDAOImpl;
import com.app.dao.impl.UpdateUserDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Role;
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
		RequestDispatcher requestDispatcher=null;
		PrintWriter out=response.getWriter();
		if(user.getRole().getRoleId()!=1) {
		
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
		else {
			try {
				SearchDAO search = new SearchDAOImpl();
				String type= request.getParameter("type");
				requestDispatcher=request.getRequestDispatcher("standard.html");
				requestDispatcher.include(request, response);
				switch(type) {
				case "all":
					List<User> userList4 = search.searchId(user.getUserId());
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
		if(request.getParameter("_method").equalsIgnoreCase("PUT")){
			doPut(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(session==null) {
			response.sendError(401,"Invalid Credentials");
		}
		else {
		User user =(User) session.getAttribute("user");
		UpdateUserDAO update = new UpdateUserDAOImpl();
		User newUser = new User();
		RequestDispatcher requestDispatcher=null;
		PrintWriter out = response.getWriter();
		if(user.getRole().getRoleId() ==1) {
		requestDispatcher=request.getRequestDispatcher("standard.html");
		requestDispatcher.include(request, response);}
		if(user.getRole().getRoleId() ==2) {
			requestDispatcher=request.getRequestDispatcher("admin.html");
			requestDispatcher.include(request, response);}
		if(user.getRole().getRoleId() ==3) {
			requestDispatcher=request.getRequestDispatcher("admin.html");
			requestDispatcher.include(request, response);}
		try {
			newUser.setUserId(user.getUserId());
			if(user.getRole().getRoleId()==3) {
				newUser.setUserId(Integer.parseInt(request.getParameter("userId")));
			}
			
			newUser.setUsername(request.getParameter("username"));
			newUser.setPassword(request.getParameter("password"));
			newUser.setFirstName(request.getParameter("fname"));
			newUser.setLastName(request.getParameter("lname"));
			newUser.setEmail(request.getParameter("email"));
			
			Role role = new Role(Integer.parseInt(request.getParameter("role")));
			newUser.setRole(role);
			if(user.getRole().getRoleId()==1) {
				Role role1 = new Role(1);
				newUser.setRole(role1);
			}
			if(user.getRole().getRoleId()==2) {
				Role role2 = new Role(2);
				newUser.setRole(role2);
			}
			update.updateUser(newUser);
			out.print(newUser.toString());
			
		} catch (BusinessException | NumberFormatException e) {
			
			out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
		}
		
	}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
