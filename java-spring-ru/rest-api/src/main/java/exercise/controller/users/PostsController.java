package exercise.controller.users;

import java.util.List;

import exercise.Application;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController{
    @GetMapping("/users/{id}/posts")
    public List<Post> showUserPosts(@PathVariable String id) {
        List<Post> result = Application.posts.stream().filter(p -> p.getUserId() == Integer.parseInt(id)).toList();
        return result;
    }
    @PostMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPostByUser(@PathVariable String id, @RequestBody Post post) {
        post.setUserId(Integer.parseInt(id));
        Application.posts.add(post);
        return post;
    }

}
// END
