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
        
        String str = request.getParameter("id");
        Integer id = integerFromString(str);
        
        if (id == null) {
            response.sendError(404, "Not Found");
        } else {
            OrderManager orderManager = (OrderManager) getServletContext().getAttribute("orderManager");
            Order order = orderManager.findOrderById(id);
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
        Integer id = integerFromString(str);
        if (id == null) {
            response.sendError(404, "Not Found");
        } else {
            OrderManager orderManager = (OrderManager) getServletContext().getAttribute("orderManager");
            orderManager.updateOrderStatus(id, "Shipped");
            response.sendRedirect("/MoorCat/orders");
        }
    }
    
    private Integer integerFromString(String str) {
        if (str == null) {
            return null;
        } else if (str.isEmpty()) {
            return null;
        } else try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return null;
        }
    }
}
