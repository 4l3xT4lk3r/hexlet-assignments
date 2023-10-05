@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <a href="${NamedRoutes.postsPath()}">Все посты</a>
        </main>
    </div>
    <div>
        <h2>${page.getPost().getName()} </h2>
    </div>
    <div>
        <span>${page.getPost().getBody()} </span>
    </div>


`
)
