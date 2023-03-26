package api;

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
    students.add(new Student("S1", "Student 1", "Dep 1", 35));
    students.add(new Student("S2", "Student 2", "Dep 1", 70));
    students.add(new Student("S3", "Student 3", "Dep 1", 60));
    students.add(new Student("S4", "Student 4", "Dep 1", 90));
    students.add(new Student("S5", "Student 5", "Dep 2", 30));
    students.add(new Student("S6", "Student 6", "Dep 3", 32));
    students.add(new Student("S7", "Student 7", "Dep 3", 70));
    students.add(new Student("S8", "Student 8", "Dep 3", 20));
    return students;
  }
}
