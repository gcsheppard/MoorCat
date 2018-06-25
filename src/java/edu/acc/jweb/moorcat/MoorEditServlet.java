package edu.acc.jweb.moorcat;

import java.io.IOException;
import java.util.ArrayList;
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
                ArrayList<OrderItem> orderItems = orderManager.getItemsForOrder(order_id);
                request.setAttribute("order", order);
                request.setAttribute("orderItems", orderItems);
                ProductManager productManager = (ProductManager) getServletContext().getAttribute("productManager");
                ArrayList<Product> products = productManager.getProductsNotOnOrder(order_id);
                request.setAttribute("products", products);
                getServletContext().getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(request, response);
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        OrderManager orderManager = (OrderManager) getServletContext().getAttribute("orderManager");
        ArrayList<OrderItem> orderItems = null;

        String str = request.getParameter("order_id");
        Integer order_id = UtilityMethods.integerFromString(str);
        if (order_id == null) {
            response.sendError(404, "Not Found");
        } else {
            String first_name = request.getParameter("first_name");
            String last_name = request.getParameter("last_name");
            String email = request.getParameter("email");

            Order order = new Order(first_name, last_name, email);
            order.setId(order_id);
            HashMap<String,String> errors = orderManager.validOrder(order);

            if (errors.isEmpty()) {
                orderManager.updateOrder(order_id, first_name, last_name, email);
                errors = null;
            }
            orderItems = orderManager.getItemsForOrder(order_id);
            for (OrderItem orderItem : orderItems) {
                String product_id = Integer.toString(orderItem.getProduct_id());
                str = request.getParameter(product_id);
                Integer ordered = UtilityMethods.integerFromString(str);
                if (ordered != null && ordered >= 0) {
                    if (ordered == 0) {
                        orderManager.removeProductFromOrder(order_id, orderItem.getProduct_id());
                    } 
                    else if (ordered != orderItem.getQuantity()) {
                        orderManager.updateOrderedQuantity(order_id, orderItem.getProduct_id(), ordered);
                    }
                }
            }
            //add product?
            str = request.getParameter("new_product");
            Integer product_id = UtilityMethods.integerFromString(str);
            str = request.getParameter("new_quantity");
            Integer quantity = UtilityMethods.integerFromString(str);
            if (product_id != null && quantity > 0) {
                if (orderManager.productInOrder(order_id, product_id)) {
                    orderManager.addQuantityToOrderedItem(order_id, product_id, quantity);
                } else {
                    orderManager.addProductToOrder(order_id, product_id, quantity);
                }
            }
            
            orderItems = null;
            orderItems = orderManager.getItemsForOrder(order_id);
            request.setAttribute("order", order);
            request.setAttribute("orderItems", orderItems);
            request.setAttribute("errors", errors);
            ProductManager productManager = (ProductManager) getServletContext().getAttribute("productManager");
            ArrayList<Product> products = productManager.getProductsNotOnOrder(order_id);
            request.setAttribute("products", products);
            getServletContext().getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(request, response);
        }
    }
}

