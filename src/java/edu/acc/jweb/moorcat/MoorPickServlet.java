package edu.acc.jweb.moorcat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/pick"})
public class MoorPickServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String str = request.getParameter("order_id");
        Integer order_id = UtilityMethods.integerFromString(str);
        if (order_id == null) {
            response.sendError(404, "Not Found");
        } else {
            OrderManager orderManager = (OrderManager) getServletContext().getAttribute("orderManager");
            Order order = orderManager.findOrderById(order_id);
            if (order == null) {
                response.sendError(404, "Not Found");
            }
            else {
                ArrayList<OrderItem> orderItems = null;
                orderItems = orderManager.getItemsForOrder(order_id);
                request.setAttribute("order", order);
                request.setAttribute("orderItems", orderItems);
                getServletContext().getRequestDispatcher("/WEB-INF/views/pick.jsp").forward(request, response);
            }
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String str = request.getParameter("order_id");
        Integer order_id = UtilityMethods.integerFromString(str);
        OrderManager orderManager = (OrderManager) getServletContext().getAttribute("orderManager");
        ArrayList<OrderItem> orderItems = orderManager.getItemsForOrder(order_id);
        for (OrderItem orderItem : orderItems) {
            String product_id = Integer.toString(orderItem.getProduct_id());
            str = request.getParameter(product_id);
            Integer picked = UtilityMethods.integerFromString(str);
            orderManager.updatePicked(order_id, orderItem.getProduct_id(), picked);
        }
        //determine if picking complete
        if (orderManager.pickComplete(order_id)) {
            orderManager.updateOrderStatus(order_id, "Picked");
        }
        response.sendRedirect("/MoorCat/orders"); 
    }
}
