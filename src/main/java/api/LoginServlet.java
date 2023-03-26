package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import requestbody.LoginRequestBody;

/**
 * Servlet for login.
 */
public class LoginServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws IOException {
    String reqBodyString = req
      .getReader()
      .lines()
      .collect(Collectors.joining(System.lineSeparator()));
    ObjectMapper mapper = new ObjectMapper();
    LoginRequestBody reqBody = mapper.readValue(
      reqBodyString,
      LoginRequestBody.class
    );
    if (userIsValid(reqBody.getUserId(), reqBody.getPassword())) {
      resp.setStatus(HttpServletResponse.SC_OK);
      resp.getWriter().write("Login Success");
    } else {
      resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      resp.getWriter().write("Login Failed");
    }
    resp.getWriter().flush();
  }

  private boolean userIsValid(String userId, String password) {
    return (userId.equals("userId") && password.equals("password"));
  }
}
