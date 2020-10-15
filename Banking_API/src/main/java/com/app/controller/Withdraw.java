package com.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dao.AccountDAO;
import com.app.dao.impl.AccountDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.User;

/**
 * Servlet implementation class Withdraw
 */
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Withdraw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(session==null) {
			response.sendError(401,"Invalid Credentials");
		}
		else {
		User user =(User) session.getAttribute("user");
		AccountDAO account = new AccountDAOImpl();
		RequestDispatcher requestDispatcher=null;
		
		PrintWriter out = response.getWriter();
		try {
			
			account.withdraw(Double.parseDouble(request.getParameter("amount")), Integer.parseInt(request.getParameter("id")),user);
		}
		catch(BusinessException | NumberFormatException e) {
			out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
		}
		
		requestDispatcher=request.getRequestDispatcher("withdraw.html");
		requestDispatcher.include(request, response);
	}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/withdraw.html");
	}
}
