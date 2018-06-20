package edu.acc.jweb.moorcat;

import java.io.IOException;
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
            String host = "smtp.gmail.com";
            String port = "587";
            String user = "atypicalcat";
            String pass = "quickcat";
            String recipient = "AtypicalCat@gmail.com";
            String subject = "shipment notification";
            String content = "Hello:\nYour order has been shipped.";
            
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
