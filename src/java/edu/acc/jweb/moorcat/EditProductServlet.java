package edu.acc.jweb.moorcat;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/editproduct"})
public class EditProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String str = request.getParameter("product_id");
        Integer product_id = UtilityMethods.integerFromString(str);
        if (product_id == null) {
            response.sendError(404, "Not Found");
        } else {
            ProductManager productManager = (ProductManager) getServletContext().getAttribute("productManager");
            Product product = productManager.findProductByID(product_id);
            request.setAttribute("product", product);
            
            SupplierManager supplierManager = (SupplierManager) getServletContext().getAttribute("supplierManager");
            ArrayList<Supplier> suppliers = supplierManager.getSuppliers();
            request.setAttribute("suppliers", suppliers);

            CategoryManager categoryManager = (CategoryManager) getServletContext().getAttribute("categoryManager");
            ArrayList<Category> categories = categoryManager.getCategories();
            request.setAttribute("categories", categories);

            getServletContext().getRequestDispatcher("/WEB-INF/views/editproduct.jsp").forward(request, response);
        }
    }
}
