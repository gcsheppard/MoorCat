package edu.acc.jweb.moorcat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"", "/MoorCat", "/login"})
public class LoginServlet extends HttpServlet {
    public String flash;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        UserManager userManager = (UserManager) getServletContext().getAttribute("userManager");
        User user = userManager.validateUser(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            flash = "";
            request.setAttribute("flash", flash);
            //BookManager bookManager = (BookManager) getServletContext().getAttribute("bookManager");
            //ArrayList<Book> books = bookManager.getBooks();
            //request.setAttribute("books", books);
            response.sendRedirect("/MoorCat/orders");
        }
        else {
            flash = "Login failed.";
            request.setAttribute("flash", flash);
            getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);   
        }
    }
}