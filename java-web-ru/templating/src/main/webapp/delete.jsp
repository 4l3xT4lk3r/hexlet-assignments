<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<!DOCTYPE html>
<html>
<head>
<title>Show</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
<meta charset="UTF-8">
</head>
<body>
          <form action='/users/delete?id=${user.get("id")}' method="post">
              <font>Вы действительно хотите удалить этого пользователя?</font>
              <table border="1">
                          <tr><td><b>id</b></td><td>${user.get("id")}</td></tr>
                          <tr><td><b>firstName</b></td><td>${user.get("firstName")}</td></tr>
                          <tr><td><b>lastName</b></td><td>${user.get("lastName")}</td></tr>
                          <tr><td><b>email</b></td><td>${user.get("email")}</td></tr>
              </table>
              <button type="submit" class="btn btn-danger">Удалить</button>
          </form>
</body>
</html>
<!-- END -->
