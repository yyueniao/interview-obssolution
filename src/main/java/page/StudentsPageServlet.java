package page;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for students page.
 */
public class StudentsPageServlet extends HttpServlet {

  private static final int PASS_MARK = 60;

  /**
   * Getting students page ui.
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    String studentsString = fetch(
      "http://localhost:8080/demo/api/student/list"
    );
    ObjectMapper mapper = new ObjectMapper();
    List<Student> students = mapper.readValue(
      studentsString,
      new TypeReference<List<Student>>() {}
    );
    Map<String, List<Student>> departmentStudentsMap = groupStudentsByDepartment(
      students
    );

    request.setAttribute("userId", request.getParameter("userId"));
    request.setAttribute("departmentStudentsMap", departmentStudentsMap);
    request.setAttribute("departments", departmentStudentsMap.keySet());
    request.setAttribute("passMark", PASS_MARK);

    RequestDispatcher dispatcher = request.getRequestDispatcher(
      "/students.jsp"
    );
    dispatcher.forward(request, response);
  }

  private String fetch(String urlString) throws IOException {
    URL url = new URL(urlString);

    URLConnection connection = url.openConnection();

    InputStream inputStream = connection.getInputStream();
    BufferedReader bufferedReader = new BufferedReader(
      new InputStreamReader(inputStream)
    );
    String line;
    StringBuilder responseBuilder = new StringBuilder();
    while ((line = bufferedReader.readLine()) != null) {
      responseBuilder.append(line);
    }
    return responseBuilder.toString();
  }

  private Map<String, List<Student>> groupStudentsByDepartment(
    List<Student> students
  ) {
    Map<String, List<Student>> map = new LinkedHashMap<String, List<Student>>();
    for (Student student : students) {
      String department = student.getDepartment();
      List<Student> curStudentList = map.get(department);
      if (curStudentList == null) {
        curStudentList = new ArrayList<Student>();
      }
      curStudentList.add(student);
      map.put(department, curStudentList);
    }
    return map;
  }
}
