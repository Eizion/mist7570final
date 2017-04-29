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
		String submitValue;
		
		
		user = (User) session.getAttribute("user");
		
		try {
			productId = (Integer) request.getAttribute("productId");
			updateQuantity = (Integer) request.getAttribute("updateQuantity");
			submitValue = (String) request.getAttribute("submitValue");
		} catch (NullPointerException e) {
			productId = Integer.parseInt(request.getParameter("productId"));
			updateQuantity = Integer.parseInt(request.getParameter("quantity"));
			submitValue = request.getParameter("submit");	
		}
		
		ReadCartQuery rcq = new ReadCartQuery("online_store", "root", "root");
		UpdateCartQuery ucq = new UpdateCartQuery("online_store", "root", "root");
		
		cartQuantity = rcq.lookupQuantity(user, productId);

		
		// Check to see if updating cart from cart page
		
		if (submitValue.equalsIgnoreCase("update") || submitValue.equalsIgnoreCase("remove") ) {
			
			if (updateQuantity == 0 || submitValue.equalsIgnoreCase("remove")) {
				
				ucq.doRemove(user, productId);
				
			} else {
	
				ucq.doUpdateQuantity(user, productId, updateQuantity, cartQuantity);
				
			
			}
			
		// Check to see if product is already in cart
			
		} else if (rcq.isInCart(user, productId) == true){
			// If it is in the cart increment quantity
			updateQuantity += cartQuantity;
			ucq.doUpdateQuantity(user, productId, updateQuantity, cartQuantity);
			
		} else {
			// Else, add to cart
			ucq.doAdd(user, productId, updateQuantity);
		}

		String url = "cart";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
