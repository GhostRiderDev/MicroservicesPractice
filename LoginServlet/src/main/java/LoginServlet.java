import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class LoginServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT u.name FROM User u WHERE username=? and password=?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                out.println("Welcome " + name);
                out.println("Click <a href=\"/LoginServlet_war/Register.jsp\">here<a> to register");
            } else {
                out.println("Invalid username or password");
            }
        } catch (SQLException e) {
            out.println(e);
        }
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
