package edu.acc.jweb.moorcat;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/ship"})
public class MoorShipServlet extends HttpServlet {
    
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
                request.setAttribute("order", order);
                ShipManager shipManager = (ShipManager) getServletContext().getAttribute("shipManager");
                ArrayList<ShipMethod> ship_methods = shipManager.getShipMethods();
                request.setAttribute("ship_methods", ship_methods);

                getServletContext().getRequestDispatcher("/WEB-INF/views/ship.jsp").forward(request, response);
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
        String str = request.getParameter("order_id");
        Integer order_id = UtilityMethods.integerFromString(str);
        if (order_id == null) {
            response.sendError(404, "Not Found");
        } else {
            OrderManager orderManager = (OrderManager) getServletContext().getAttribute("orderManager");
            orderManager.updateOrderStatus(order_id, "Shipped");
            Order order = orderManager.findOrderById(order_id);
            
            ArrayList<OrderItem> orderItems = orderManager.getItemsForOrder(order_id);
            
            String ship_method = request.getParameter("ship_method");
            Integer ship_method_id = UtilityMethods.integerFromString(ship_method);
            String tracking = request.getParameter("tracking");
            ShipManager shipManager = (ShipManager) getServletContext().getAttribute("shipManager");
            shipManager.addShipDetail(order_id, ship_method, tracking);
            String ship_method_name = shipManager.getShipMethodName(ship_method_id);
            
            String host = "smtp.gmail.com";
            String port = "587";
            String user = "atypicalcat";
            String pass = "quickcat";
            String recipient = order.email;
            String subject = "shipment notification";
            String content = "Hello:\n\nYour order number " + order_id + " has been shipped."
                    + "\nShipment is by " + ship_method_name + "; tracking number " + tracking + "."
                    + "\nThis shipment contains:\n";
            for (OrderItem orderItem : orderItems) {
                content = content + "(" + orderItem.getQuantity() + ") " + orderItem.getName() + "\n";
            }
            content = content + "\nThank you,\nsales@MoorCat.com\n512-555-1212\n";
            try {
                EmailUtility.sendEmail(host, port, user, pass, recipient, subject, content);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                response.sendRedirect("/MoorCat/orders");
            }
        }
    }
}
