@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
    <table  class="table table-bordered table-hover mt-3">
        <thead>
        <th>id</th>
        <th>Name</th>
        <th>Email</th>
        </thead>
        <tbody>
    @for(var user : page.getUsers())
    <tr>
        <td><a href="/users/${user.getId()}">${user.getId()}</a></td>
        <td>${user.getFirstName() +" "+ user.getLastName()}</td>
        <td>${user.getEmail()}</td>
    </tr>
    @endfor
        </tbody>
    </table>
`
)