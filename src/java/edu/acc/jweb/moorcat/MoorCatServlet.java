package edu.acc.jweb.moorcat;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/orders"})
public class MoorCatServlet extends HttpServlet {

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<Order> orders = null;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        OrderManager orderManager = (OrderManager) getServletContext().getAttribute("orderManager");
        orders = orderManager.getOrders(user.getDepartment());
        request.setAttribute("orders", orders);
        getServletContext().getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(request, response);
    }
}