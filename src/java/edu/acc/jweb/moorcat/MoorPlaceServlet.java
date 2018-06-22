package edu.acc.jweb.moorcat;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/place"})
public class MoorPlaceServlet extends HttpServlet {

@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        @SuppressWarnings (value="unchecked")
        ArrayList<OrderItem> new_order_items = (ArrayList<OrderItem>) session.getAttribute("new_order_items");
        Order order = (Order) session.getAttribute("order");
        OrderManager orderManager = (OrderManager) getServletContext().getAttribute("orderManager");
        
        int order_id = orderManager.addOrderReturnPK(order.getFirst_name(), order.getLast_name(), "Placed", order.getEmail());
        
        for (OrderItem order_item : new_order_items) {
            orderManager.addProductToOrder(order_id, order_item.getProduct_id(), order_item.getQuantity());
        }
        
        response.sendRedirect("/MoorCat/orders");
    }
}
