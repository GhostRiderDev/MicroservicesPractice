import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String eno = req.getParameter("eno");
        String ename = req.getParameter("ename");
        String salary = req.getParameter("salary");
        out.println(
                "<table border>"
                        + "<tr><th>Emp Num</th><td>" + eno + "</td></tr>"
                        + "<tr><th>Emp Name</th><td>" + ename + "</td></tr>"
                        + "<tr><th>Emp Salary</th><td>" + salary + "</td></tr>"
                + "</table>"
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
