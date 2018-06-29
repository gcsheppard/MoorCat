package edu.acc.jweb.moorcat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/products"})
public class MoorProductListServlet extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        ProductManager productManager = (ProductManager) getServletContext().getAttribute("productManager");
        ArrayList<Product> products = productManager.getAllProducts();
        request.setAttribute("products", products);
        getServletContext().getRequestDispatcher("/WEB-INF/views/products.jsp").forward(request, response);
    }

}
