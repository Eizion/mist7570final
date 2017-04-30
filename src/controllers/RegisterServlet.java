package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbHelpers.CheckUserQuery;
import dbHelpers.LoginQuery;
import dbHelpers.RegisterQuery;
import model.User;
import utilities.Encryption;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String url;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("user");
		String password = request.getParameter("password");
		String fName = request.getParameter("fname");
		String lName = request.getParameter("lname");
		
		Encryption pwd = new Encryption();
		String encryptedPass = pwd.encrypt(password);
		
		CheckUserQuery cu = new CheckUserQuery("online_store", "root", "root");
		User user = cu.checkUser(userName);
		
		if (user != null){
			String errorMessage = "Error: Username already exists. Try another one.";
			request.setAttribute("errorMessage", errorMessage);
			url = "registerForm.jsp";
		} else {
			User newUser = new User();
			
			newUser.setUsername(userName);
			newUser.setPassword(encryptedPass);
			newUser.setfName(fName);
			newUser.setlName(lName);
			
			RegisterQuery rq = new RegisterQuery("online_store", "root", "root");
			
			rq.doRegister(newUser);
			
			url = "success.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
