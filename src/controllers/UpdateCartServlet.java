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
import dbHelpers.RegisterQuery;
import dbHelpers.UpdateCartQuery;
import model.User;
import utilities.Encryption;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/updateCart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCartServlet() {
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
		
		User user;
		int productId;
		int cartQuantity;
		int	updateQuantity;
		
		
		user = (User) session.getAttribute("user");
		updateQuantity = Integer.parseInt(request.getParameter("quantity"));
		productId = Integer.parseInt(request.getParameter("productId"));
		
		
		ReadCartQuery rcq = new ReadCartQuery("online_store", "root", "root");
		UpdateCartQuery ucq = new UpdateCartQuery("online_store", "root", "root");
		
		// Check to see if product is already in cart
		
		if(rcq.isInCart(user, productId) == true){
			// If it is in the cart increment quantity
			cartQuantity = rcq.lookupQuantity(user, productId);
			updateQuantity += cartQuantity;
			ucq.doIncrementQuantity(user, productId, updateQuantity);
			
		} else {
			// Else, add to cart
			ucq.doAdd(user, productId, updateQuantity);
		}

		String url = "GenerateCart";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
