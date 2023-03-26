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
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for students page.
 */
public class StudentsPageServlet extends HttpServlet {

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
    request.setAttribute("userId", request.getParameter("userId"));
    request.setAttribute("students", students);

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
}
