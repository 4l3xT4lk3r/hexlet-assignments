@template.layout.page(
content = @`
    <h1>Пользователи</h1>

    <form action="/users" method="post">
        <div><label>Имя<input name="firstName" required type="text"></label></div>
        <div><label>Фамилия<input name="lastName" required type="text"></label></div>
        <div><label>Email<input name="email" required type="text"></label></div>
        <div><label>Пароль<input name="password" required type="password"></label></div>
        <div><input type="submit" value="Создать"></div>
    </form>
`
)
