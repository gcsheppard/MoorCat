package edu.acc.jweb.moorcat;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
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
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;

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
            Order order = orderManager.findOrderById(order_id);
            if (order == null) {
                response.sendError(404, "Not Found");
            }
            else {
                ArrayList<OrderItem> orderItems = null;
                orderItems = orderManager.getItemsForOrder(order_id);
            
                try {
                    // create PDF document
                    Document document = new Document();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    PdfWriter.getInstance(document, byteArrayOutputStream);
                    document.open();
                    
                    //define fonts and header text
                    Font blue24 = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, BaseColor.BLUE);
                    Font blue18 = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE);
                    Font blue12 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLUE);
                    Chunk blueText1 = new Chunk("Moor Cat", blue24);
                    Chunk blueText2 = new Chunk("Packing List", blue18);
                    Chunk blueText3 = new Chunk("Order #" + order_id + " for " 
                            + order.getFirst_name() + " " + order.getLast_name(), blue12);
                    //add header text to document
                    addParagraph(document, blueText1);
                    document.add(Chunk.NEWLINE);
                    addParagraph(document, blueText2);
                    document.add(Chunk.NEWLINE);
                    addParagraph(document, blueText3);
                    document.add(Chunk.NEWLINE);
                    //create table
                    PdfPTable table = new PdfPTable(3);
                    table.setWidthPercentage(40);
                    table.setWidths(new int[]{1, 3, 5});
                    //list ordered items in table
                    for (OrderItem orderItem : orderItems) {
                        Chunk chunk = new Chunk(Integer.toString(orderItem.getQuantity()));
                        addTextCell(table, chunk);
                        Image image = Image.getInstance("C:/j2ee/PDFWebApplication1/web/images/" + orderItem.getProduct_id() + ".jpg");
                        addImageCell(table, image);
                        chunk = new Chunk(orderItem.getName());
                        addTextCell(table, chunk);
                    }

                    document.add(table);
                    document.close();

                    // set response headers and content information
                    response.setHeader("Expires", "0");
                    response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                    response.setHeader("Pragma", "public");
                    response.setContentType("application/pdf");
                    response.setContentLength(byteArrayOutputStream.size());
                    response.setHeader("Content-Disposition", "attachment; filename=packlist.pdf");
                    
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
    
    private void addParagraph(Document document, Chunk text)
        throws DocumentException {
        
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(text);
        document.add(paragraph);
    }
    
    private void addTextCell(PdfPTable table, Chunk text) 
            throws DocumentException {
        
        PdfPCell cell = new PdfPCell();
        Paragraph paragraph = new Paragraph(text);
        cell.addElement(paragraph);
        table.addCell(cell);
    }
    
    private void addImageCell (PdfPTable table, Image image)
            throws DocumentException {
   
        PdfPCell cell = new PdfPCell(image, true);
        cell.setUseBorderPadding(true);
        table.addCell(cell);
    }        
}