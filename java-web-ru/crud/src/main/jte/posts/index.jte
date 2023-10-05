@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage
@param PostsPage postsPage

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <a href="${NamedRoutes.postsPath()}">Все посты</a>
        </main>
    </div>
    <table  class="table table-bordered table-hover mt-3">
        <thead>
        <th>id</th>
        <th>Name</th>
        </thead>
        <tbody>
        @for(var post : postsPage.getPosts())
            <tr>
                <td><a href="${NamedRoutes.postsPath()}/${post.getId()}">${post.getId()}</a></td>
                <td>${post.getName() +" "+ post.getName()}</td>
            </tr>
        @endfor
        </tbody>
    </table>
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center mt-5">
            <li class="page-item">
                <a class="page-link"
                   href="${NamedRoutes.postsPath()}?page=${postsPage.getCurrentPage()>1 ? postsPage.getCurrentPage()-1:1 }">Previous</a>
            </li>
            <li class="page-item">
                <a class="page-link"
                   href="${NamedRoutes.postsPath()}?page=${postsPage.getCurrentPage()+1}">Next</a>
            </li>
        </ul>
    </nav>
`
)
