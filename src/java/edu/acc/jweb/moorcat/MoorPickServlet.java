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
        
        String str = request.getParameter("id");
        Integer id = integerFromString(str);
        
        //Integer id = integerIdFromParameter(request);
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
        
        //printParamInfo(request);
        
        String str = request.getParameter("order_id");
        Integer order_id = integerFromString(str);
        /*
        if (order_id == null) {
            System.out.println("order_id is null");
        } else {
            System.out.println("order_id is not null; it is: " + order_id);
        }
        */
        OrderManager orderManager = (OrderManager) getServletContext().getAttribute("orderManager");
        ArrayList<OrderItem> orderItems = null;
        orderItems = orderManager.getItemsForOrder(order_id);
        for (OrderItem orderItem : orderItems) {
            String product_id = Integer.toString(orderItem.getProduct_id());
            //System.out.println("product_id is: " + product_id);
            str = request.getParameter(product_id);
            Integer picked = integerFromString(str);
            /*
            if (picked == null) {
            System.out.println("picked is null");
            } else {
            System.out.println("picked is not null; it is: " + picked);
            }
            */
            orderManager.updatePicked(order_id, orderItem.getProduct_id(), picked);
        }
        //determine if picking complete
        if (orderManager.pickComplete(order_id)) {
            orderManager.updateOrderStatus(order_id, "Picked");
        }
        
        response.sendRedirect("/MoorCat/orders"); 
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
    
    private void printParamInfo(HttpServletRequest request) {
        Enumeration paramNames = request.getParameterNames();

        while(paramNames.hasMoreElements()) {
         String paramName = (String)paramNames.nextElement();
         System.out.println("paramName:" + paramName);
         String[] paramValues = request.getParameterValues(paramName);

         // Read single valued data
         if (paramValues.length == 1) {
            String paramValue = paramValues[0];
            if (paramValue.length() == 0)
               System.out.println("No Value");
            else
               System.out.println(paramValue);
         } else {
            // Read multiple valued data
            for(int i = 0; i < paramValues.length; i++) {
               System.out.println(paramValues[i]);
            }
         }
      }
    }
    
    /*    
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
    */
}
