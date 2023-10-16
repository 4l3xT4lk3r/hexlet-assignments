package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("posts")
public class PostsController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<Post> index() {
        return postRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Post show(@PathVariable long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Post with id %d not found", id)));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        postRepository.save(post);
        return post;
    }
    @PutMapping(path = "/{id}")
    public Post update(@PathVariable long id, @RequestBody Post post) {
        Post postToUpdate = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Post with id %d not found", id)));
        postToUpdate.setBody(post.getBody());
        postToUpdate.setTitle(post.getTitle());
        postRepository.save(postToUpdate);
        return post;
    }

    @DeleteMapping(path = "/{id}")
    public Post delete(@PathVariable long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Post with %d not found", id)));
        postRepository.delete(post);
        commentRepository.deleteByPostId(id);
        return post;
    }

}
// END
