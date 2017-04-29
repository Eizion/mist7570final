package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbHelpers.ReadCartQuery;
import dbHelpers.ReadProductQuery;
import dbHelpers.RegisterQuery;
import dbHelpers.UpdateCartQuery;
import model.User;
import utilities.Encryption;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/validate")
public class ValidateRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateRequestServlet() {
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
	
		String url;
		String msg;
		String submitValue;
		int productId;
		int quantityRequested;
		int currentInventoryQuantity;
		
		// pull request entries
		productId = Integer.parseInt(request.getParameter("productId"));
		quantityRequested = Integer.parseInt(request.getParameter("quantity"));
		submitValue = request.getParameter("submit");
		
		// query on productId to determine current inventory amount
		ReadProductQuery rpq = new ReadProductQuery("online_store", "root", "root");
		currentInventoryQuantity = rpq.getInventoryQty(productId);
		
		if (currentInventoryQuantity >= quantityRequested && quantityRequested > 0) {

			url = "updateCart";

			request.setAttribute("productId", productId);
			request.setAttribute("updateQuantity", quantityRequested);
			request.setAttribute("submitValue", submitValue);
			
			System.out.println("validate: " + productId + " " + quantityRequested + " " + submitValue);
			
		} else {

			url = "shop";
			msg = "<div id='error'>Please enter a valid quantity</div><br />";
			request.setAttribute("msg", msg);
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
