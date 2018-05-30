package edu.acc.jweb.moorcat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
        
        Integer id = integerIdFromParameter(request);
        if (id == null) {
            response.sendError(404, "Not Found");
        } else {
            OrderManager orderManager = (OrderManager) getServletContext().getAttribute("orderManager");
            Order order = orderManager.findOrderById(id);
            if (order == null) {
                response.sendError(404, "Not Found");
            }
            else {
                ArrayList<OrderItem> orderItems = null;
                orderItems = orderManager.getItemsForOrder(id);
                request.setAttribute("order", order);
                request.setAttribute("orderItems", orderItems);
                getServletContext().getRequestDispatcher("/WEB-INF/views/pick.jsp").forward(request, response);
            }
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                response.sendRedirect("/MoorCat/orders"); 
        }
    
        
    private Integer integerIdFromParameter(HttpServletRequest request) {
        String str = request.getParameter("id");
        if (str == null) {
            return null;
        } else if (str.isEmpty()) {
            return null;
        } else try {
            return Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            return null;
        }
    }
}
