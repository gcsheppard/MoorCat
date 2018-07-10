package edu.acc.jweb.moorcat;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(urlPatterns = {"/upload"})
@MultipartConfig
public class ImageUploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String str = request.getParameter("product_id");
        Integer product_id = UtilityMethods.integerFromString(str);
        
        Part part = request.getPart("image");
        if (part != null) {
            //ImageManager manager = (ImageManager) getServletContext().getAttribute("imageManager");
            ProductManager productManager = (ProductManager) getServletContext().getAttribute("productManager");
            InputStream imageStream = null;
            try {
                String fileName = part.getSubmittedFileName();
                String contentType = part.getContentType();
                imageStream = part.getInputStream();
                productManager.saveImage(fileName, contentType, imageStream, product_id);
            } finally {
                if (imageStream != null) {
                    try {
                        imageStream.close();
                    } catch (IOException ignored) {
                    }
                }
            }
            response.sendRedirect("/MoorCat/editproduct?product_id=" + product_id);
        }
    }
}
