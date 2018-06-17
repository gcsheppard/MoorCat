package edu.acc.jweb.moorcat;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/neworder"})
public class MoorNewOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        getServletContext().getRequestDispatcher("/WEB-INF/views/neworder.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        OrderManager orderManager = (OrderManager) getServletContext().getAttribute("orderManager");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        Order order = new Order(first_name, last_name);
        HashMap<String,String> errors = orderManager.validOrder(order);
        
        //verify at least one order item
        //keep list of order items in session
        //if not at least one order item, add to errors
        //put order, order_items, errors in session
        //also determine products list for adding product
        getServletContext().getRequestDispatcher("/WEB-INF/views/neworder.jsp").forward(request, response);
    }
}
