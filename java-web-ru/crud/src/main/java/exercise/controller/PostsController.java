package exercise.controller;

import java.sql.ClientInfoStatus;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    private static final int RECORDS_SIZE = 5;

    // BEGIN
    public static void index(Context ctx) {
        int page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        List<Post> posts = PostRepository.getEntities();

        int maxPage = posts.size() / RECORDS_SIZE;
        if (posts.size() % RECORDS_SIZE > 0 || posts.isEmpty()) {
            maxPage++;
        }

        if (page > maxPage) {
            page = maxPage;
        }

        int firstIndex = (page - 1) * RECORDS_SIZE;
        int lastIndex = page * RECORDS_SIZE;
        if (lastIndex > posts.size()) {
            lastIndex = posts.size();
        }
        PostsPage postsPage;
        if (!posts.isEmpty()) {
            postsPage = new PostsPage(posts.subList(firstIndex, lastIndex), page);
        } else {
            postsPage = new PostsPage(posts, page);
        }
        ctx.render("posts/index.jte", Collections.singletonMap("postsPage", postsPage));
    }

    public static void show(Context ctx) {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);
        Optional<Post> post = PostRepository.find(id);
        if ( post.isPresent() ) {
            PostPage page = new PostPage(post.get());
            ctx.render("posts/show.jte", Collections.singletonMap("page", page));
        }else {
            throw new NotFoundResponse("Page not found");
        }
    }


    // END
}
