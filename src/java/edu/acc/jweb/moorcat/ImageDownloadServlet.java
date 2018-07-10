package edu.acc.jweb.moorcat;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/image")
public class ImageDownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ImageManager manager = (ImageManager) getServletContext().getAttribute("imageManager");
        String str = request.getParameter("id");
        Integer product_id = UtilityMethods.integerFromString(str);        
        ProductManager productManager = (ProductManager) getServletContext().getAttribute("productManager");
        Product product = productManager.findProductByID(product_id);
        
        if (product.getContentType() != null) {
            response.setStatus(200);
	    response.setContentType(product.getContentType());
            productManager.copyImageContent(product_id, response.getOutputStream());
        } else {
            response.sendError(404);
        }
        
    }
}
