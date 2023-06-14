package exercise.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        File file = new File("src/main/resources/users.json");
        JsonMapper mapper = new JsonMapper();
        List users = mapper.readValue(file, List.class);
        return users;
        // END
    }

    private void showUsers(HttpServletRequest request,
                           HttpServletResponse response)
            throws IOException {
        // BEGIN
        response.setContentType("text/html;charset=UTF-8");
        List<Map<String, String>> users = getUsers();
        PrintWriter pw = response.getWriter();
        pw.write("<table>");
        for (Map<String, String> user : users) {
            pw.write("""
                    <tr>
                    <td>%s</td>
                    <td><a href=\"/users/%s\">%s %s</a></td>
                    </tr>
                    """
                    .formatted(user.get("id"),user.get("id"),user.get("firstName"),user.get("lastName")));
        }
        pw.write("</table>");
        // END
    }

    private void showUser(HttpServletRequest request,
                          HttpServletResponse response,
                          String id)
            throws IOException {

        // BEGIN
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        List<Map<String, String>> users = getUsers();
        if (users.stream().anyMatch(m -> m.get("id").equals(id))) {
            Map<String, String> user = users.stream().filter(m -> m.get("id").equals(id)).findFirst().orElseThrow();
            pw.write("""
                    <table border=\"1\" style=\"border-collapse:collapse\">
                    <tr><td><b>firstName</b></td><td>%s</td></tr>
                    <tr><td><b>lastName</b></td><td>%s</td></tr>
                    <tr><td><b>id</b></td><td>%s</td></tr>
                    <tr><td><b>email</b></td><td>%s</td></tr>
                    </table>                    
                    """
                    .formatted(user.get("firstName"),user.get("lastName"),user.get("id"),user.get("email")));
        } else {
            response.sendError(404,"Not Found");
        }
    }
    // END
}

