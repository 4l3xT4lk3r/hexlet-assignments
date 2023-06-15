<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<!DOCTYPE html>
<html>
<head>
<title>User</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
<meta charset="UTF-8">
</head>
<body>
        <table>
        <c:forEach var="users" items="${users}">
            <tr>
            <td>${users.get("id")}</td>
            <td><a href='/users/show?id=${users.get("id")}'>${users.get("firstName")} ${users.get("lastName")}</a></td>
            </tr>
        </c:forEach>
        </table>
</body>
</html>
<!-- END -->