package edu.acc.jweb.moorcat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/neworder"})
public class MoorNewOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<OrderItem> new_order_items = new ArrayList<>();
        HttpSession session = request.getSession();
        session.setAttribute("new_order_items", new_order_items);   
        ProductManager productManager = (ProductManager) getServletContext().getAttribute("productManager");
        ArrayList<Product> products = productManager.getProductsNotOnOrder(0);
        request.setAttribute("products", products);
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
        
        HttpSession session = request.getSession();
        @SuppressWarnings (value="unchecked")
        ArrayList<OrderItem> new_order_items = (ArrayList<OrderItem>) session.getAttribute("new_order_items");
        if (new_order_items != null && !new_order_items.isEmpty()) {
            for (int i=0; i<new_order_items.size(); i++) {
                String product_id = Integer.toString(new_order_items.get(i).getProduct_id());
                String str = request.getParameter(product_id);
                Integer ordered = UtilityMethods.integerFromString(str);
                if (ordered != null && ordered >= 0) {
                    if (ordered == 0) {
                        new_order_items.remove(i);
                        i--;
                    } 
                    else if (ordered != new_order_items.get(i).getQuantity()) {
                        new_order_items.get(i).setQuantity(ordered);
                    }
                }
            }
        }
        
        String str = request.getParameter("new_product");
        Integer product_id = UtilityMethods.integerFromString(str);
        str = request.getParameter("new_quantity");
        Integer quantity = UtilityMethods.integerFromString(str);
        if (product_id != null && quantity > 0) {
            System.out.println("--3------------------------");
            System.out.println("product_id = " + product_id);
            System.out.println("quantity = " + quantity);
            System.out.println("------------------------");
            OrderItem orderItem = orderManager.getUnassignedOrderItem(product_id, quantity);
            if (new_order_items != null) {
                new_order_items.add(orderItem);
            }
        if (new_order_items == null) {
            System.out.println("--1------------------------");
            System.out.println("new_order_items is null");
            System.out.println("------------------------");
        } else {
            System.out.println("--1------------------------");
            System.out.println("new_order_items.size() = " + new_order_items.size());
            System.out.println("------------------------");
        }
        }
        
        if (new_order_items == null || new_order_items.isEmpty()) {
            errors.put("order items", "At least one order item is required.");
        }
        
        ProductManager productManager = (ProductManager) getServletContext().getAttribute("productManager");
        ArrayList<Product> products = productManager.getProductsNotOnOrder(0);
        request.setAttribute("products", products);
        request.setAttribute("order", order);
        if (new_order_items == null) {
            System.out.println("--2------------------------");
            System.out.println("new_order_items is null");
            System.out.println("------------------------");
        } else {
            System.out.println("--2------------------------");
            System.out.println("new_order_items.size() = " + new_order_items.size());
            System.out.println("------------------------");
        }
        request.setAttribute("new_order_items", new_order_items);
        //set errors to null if empty
        //save order to db
        request.setAttribute("errors", errors);
        getServletContext().getRequestDispatcher("/WEB-INF/views/neworder.jsp").forward(request, response);
    }
}
