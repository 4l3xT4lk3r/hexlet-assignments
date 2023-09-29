@import exercise.dto.users.UserPage
@param UserPage user

<!-- BEGIN -->

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
    <title>Хекслет</title>
</head>
<body>
    <table class="table table-bordered table-hover mt-3">
        <thead>
        <th class="col-1">ID</th>
        <th class="col-1">Фамилия</th>
        <th class="col-1">Имя</th>
        <th class="col-1">Email</th>
        </thead>
        <tbody>
            <tr>
                <td>${user.getUser().getId()}</td>
                <td>${user.getUser().getLastName()}</td>
                <td>${user.getUser().getFirstName()}</td>
                <td>${user.getUser().getEmail()}</td>
            </tr>
        </tbody>
    </table>
</body>
</html>
<!-- END -->

<!-- END -->
