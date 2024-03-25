import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class RegisterServlet extends HttpServlet {
    private Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Servlet", "root", "root");
        } catch (Exception exception) {
          exception.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO USER values ('" + username + "','" + name + "','" + password + "')");
            statement.execute();
            out.println("Registered Successfully<br>");
            out.println("Click <a href=\"/LoginServlet_war/Login.jsp\">here<a> to login");
        } catch (SQLException e) {
            out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        try {
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
