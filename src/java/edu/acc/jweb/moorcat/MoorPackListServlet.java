package edu.acc.jweb.moorcat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet(urlPatterns = {"/packlist"})
public class MoorPackListServlet extends HttpServlet {

@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String str = request.getParameter("order_id");
        Integer order_id = UtilityMethods.integerFromString(str);
        if (order_id == null) {
            response.sendError(404, "Not Found");
        } else {
            OrderManager orderManager = (OrderManager) getServletContext().getAttribute("orderManager");
            //get order
            //get order items
            
                try {
                    // Get the text that will be added to the PDF
                    String text = request.getParameter("text");
                    if (text == null || text.trim().length() == 0) {
                         text = "You didn't enter any text.";
                    }
                    // create PDF document
                    Document document = new Document();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    PdfWriter.getInstance(document, byteArrayOutputStream);
                    document.open();
                    document.add(new Paragraph(String.format(
                        "You have submitted the following text using the %s method:",
                        request.getMethod())));
                    document.add(new Paragraph(text));
                    document.close();

                    // set response headers and content information
                    response.setHeader("Expires", "0");
                    response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                    response.setHeader("Pragma", "public");
                    response.setContentType("application/pdf");
                    response.setContentLength(byteArrayOutputStream.size());
                    
                    // write ByteArrayOutputStream to the ServletOutputStream
                    OutputStream outputStream = response.getOutputStream();
                    byteArrayOutputStream.writeTo(outputStream);
                    outputStream.flush();
                    outputStream.close();
                }
                catch(DocumentException e) {
                    throw new IOException(e.getMessage());
                }
        }
    }
}
