import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Student;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet to list students.
 */
public class ListStudentsServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws IOException {
    List<Student> students = listStudents();

    ObjectMapper mapper = new ObjectMapper();
    String responseBodyString = mapper.writeValueAsString(students);
    
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");

    resp.getWriter().write(responseBodyString);
    resp.getWriter().flush();
  }

  private List<Student> listStudents() {
    List<Student> students = new ArrayList<>();
    students.add(new Student("123", "John Doe", "Computer Science", 90));
    students.add(new Student("456", "Jane Smith", "Mathematics", 85));
    students.add(new Student("789", "Bob Johnson", "Physics", 95));
    return students;
  }
}
