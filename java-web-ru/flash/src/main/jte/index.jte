@import exercise.util.NamedRoutes

@template.layout.page(
    content = @`
        <h2>Welcome to Hexlet</h2>
    <div class="mx-auto p-4 py-md-5">
        <main>
            <a href="${NamedRoutes.postsPath()}">Все посты</a>
        </main>
    </div>
`
)
