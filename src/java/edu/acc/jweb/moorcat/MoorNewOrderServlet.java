package edu.acc.jweb.moorcat;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/neworder"})
public class MoorNewOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        getServletContext().getRequestDispatcher("/WEB-INF/views/neworder.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");
        String isbn = request.getParameter("isbn");
        Integer rating = Integer.getInteger(request.getParameter("rating"));
        String reviewer = request.getParameter("reviewer");
        String review = request.getParameter("review");
        
        //Book book = new Book(title, author, genre, isbn, rating, reviewer, review);
        //BookManager bookManager = (BookManager) getServletContext().getAttribute("bookManager");
        //String errors = bookManager.validBook(book);
        
        //if (errors.isEmpty()) {
            //bookManager.addBook(title, author, genre, isbn, rating, reviewer, review);
            response.sendRedirect("/BookBlog2/home"); 
        //}
        //else {
            //put attribute book in request
            //request.setAttribute("book", book);
            //request.setAttribute("errors", errors);
            //forward to addbook.jsp
            getServletContext().getRequestDispatcher("/WEB-INF/views/addbook.jsp").forward(request, response);
        //}
    }
}
