package edu.acc.jweb.moorcat;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/edit"})
public class MoorEditServlet extends HttpServlet {

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
                request.setAttribute("order", order);
                getServletContext().getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(request, response);
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Integer id = integerIdFromParameter(request);
        if (id == null) {
            response.sendError(404, "Not Found");
        } else {
            String first_name = request.getParameter("first_name");
            String last_name = request.getParameter("last_name");
            String status = request.getParameter("status");

            Order order = new Order(first_name, last_name, status);
            OrderManager orderManager = (OrderManager) getServletContext().getAttribute("orderManager");
            HashMap<String,String> errors = orderManager.validOrder(order);

            if (errors.isEmpty()) {
                orderManager.updateOrder(id, first_name, last_name, status);
                response.sendRedirect("/MoorCat/orders"); 
            }
            else {
                request.setAttribute("order", order);
                request.setAttribute("errors", errors);
                getServletContext().getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(request, response);
            }
        }
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
