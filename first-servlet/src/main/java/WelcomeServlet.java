import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//@WebServlet("/hello")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        out.println("Welcome to Servlets");
        out.println("<font face=\"arial\" size=\"5\" color=\"blue\">Hello world</font>");
    }
}
