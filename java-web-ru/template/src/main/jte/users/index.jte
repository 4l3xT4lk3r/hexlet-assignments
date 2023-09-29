@import exercise.dto.users.UsersPage
@param UsersPage users

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
@if(users.getUsers().isEmpty())
    <p>Пока не добавлено ни одного пользователя</p>
@else
    <table class="table table-bordered table-hover mt-3">
    <thead>
    <th class="col-1">ID</th>
    <th class="col-1">Фамилия</th>
    <th class="col-1">Имя</th>
    <th class="col-1">Email</th>
    </thead>
        <tbody>
        @for(var user : users.getUsers())
            <tr>
                <td><a href="/users/${user.getId()}">${user.getId()}</a></td>
                <td>${user.getLastName()}</td>
                <td>${user.getFirstName()}</td>
                <td>${user.getEmail()}</td>
            </tr>
        @endfor
        </tbody>
    </table>
@endif
</body>
</html>
<!-- END -->
