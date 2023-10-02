@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
content = @`
    <table  class="table table-bordered table-hover mt-3">
        <tr>
            <td>id</td>
            <td>${page.getUser().getId()}</td>
        </tr>
        <tr>
            <td>Name</td>
            <td>${page.getUser().getFirstName() + " "+ page.getUser().getLastName()}</td>
        </tr>
        <tr>
            <td>Email</td>
            <td>${page.getUser().getEmail()}</td>
        </tr>
    </table>
`
)