package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbHelpers.ReadCartQuery;
import dbHelpers.UpdateCartQuery;
import model.User;

/**
 * Servlet implementation class GenerateCartServlet
 */
@WebServlet(
		description = "Controller for reading the cart table", 
		urlPatterns = { 
				"/GenerateCart", 
				"/cart"
		})
public class GenerateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateCartServlet() {
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
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		
		ReadCartQuery rcq = new ReadCartQuery("online_store", "root", "root");
		
		// Query on db
		
		rcq.doRead(user);
		
		// Iterate through DB and assign to HTML string
		String table = "";
		try {
			table = rcq.getHTMLTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Send string to JSP via request/response object
		request.setAttribute("table", table);

		String url = "cart.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
