package com.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.app.dao.RegisterDAO;
import com.app.dao.impl.AccountDAOImpl;
import com.app.dao.impl.RegisterDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Role;
import com.app.model.User;

/**
 * Servlet implementation class Account
 */
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
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
		AccountDAO account = new AccountDAOImpl();
		User newUser = new User();
		RequestDispatcher requestDispatcher=null;
		PrintWriter out = response.getWriter();
		
		try {
			List<Account> accountList = null;
			requestDispatcher=request.getRequestDispatcher("account.html");
			requestDispatcher.include(request, response);
			String type = request.getParameter("type");
			switch(type) {
			case "all":
				accountList = account.findAccounts(user);
				break;
			case "id":
				accountList = account.findAccountById(user,Integer.parseInt(request.getParameter("search")));
				break;
			case "status":
				AccountStatus status = new AccountStatus(request.getParameter("search"));
				accountList = account.findAccountByStatus(user, status);
				break;
			case "userId":
				newUser.setUserId(user.getUserId());
				if(user.getRole().getRoleId()!=1) {
					newUser.setUserId(Integer.parseInt(request.getParameter("search")));
				}
				accountList = account.findAccountByUserId(newUser);
				break;
			}
			System.out.println(type);
			System.out.println(request.getParameter("search"));
			for(Account a:accountList) {
				out.print("<h4>User Account "+a.toString()+"</h4>");
			}
			
			
		} catch (BusinessException e) {
//			requestDispatcher=request.getRequestDispatcher("account.html");
//			requestDispatcher.include(request, response);
			System.out.println(e);
			out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
		}
		catch(NumberFormatException | NullPointerException e) {
//			requestDispatcher=request.getRequestDispatcher("account.html");
//			requestDispatcher.include(request, response);
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
		else{
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
			System.out.println(request.getParameter("atype"));
			AccountType atype = new AccountType(request.getParameter("atype"));
			System.out.println(atype.getType());
			account.submitAccount(user, atype);
			
			
		} catch (BusinessException | NumberFormatException e) {
			requestDispatcher=request.getRequestDispatcher("account.html");
			requestDispatcher.include(request, response);
			out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
		}
		
	}
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
		AccountDAO account = new AccountDAOImpl();
		User newUser = new User();
		RequestDispatcher requestDispatcher=null;
		PrintWriter out = response.getWriter();
		requestDispatcher=request.getRequestDispatcher("account.html");
		requestDispatcher.include(request, response);
		try {
			newUser.setUserId(user.getUserId());
			if(user.getRole().getRoleId()!=1) {
				newUser.setUserId(Integer.parseInt(request.getParameter("userId")));
			}
			Account newAccount = new Account();System.out.println("2");
			newAccount.setBalance(Double.parseDouble(request.getParameter("balance")));
			AccountType type = new AccountType(request.getParameter("btype"));
			newAccount.setType(type);
			AccountStatus status = new AccountStatus(request.getParameter("status"));
			newAccount.setStatus(status);
			newAccount.setAccountId(Integer.parseInt(request.getParameter("id")));
			account.updateAccount(Integer.parseInt(request.getParameter("userId")), newAccount);
			out.print(newAccount.toString());
			
		} catch (BusinessException | NumberFormatException e) {
			
			out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
		}
		
	}
	}

}
