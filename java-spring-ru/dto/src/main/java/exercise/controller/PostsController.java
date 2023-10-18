package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping(path = "/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<PostDTO> index() {
        List<PostDTO> posts = postRepository
                .findAll()
                .stream()
                .map(this::postToDto)
                .toList();
        posts.forEach(p -> p.setComments(commentRepository.findByPostId(p.getId())
                .stream()
                .map(this::commentToDTO)
                .toList()));
        return posts;
    }

    @GetMapping(path = "/{id}")
    public PostDTO show(@PathVariable long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Post with id %d not found", id)));
        PostDTO postDTO = postToDto(post);
        List<CommentDTO> comments = commentRepository.findByPostId(postDTO.getId())
                .stream()
                .map(this::commentToDTO)
                .toList();
        postDTO.setComments(comments);
        return postDTO;
    }

    private PostDTO postToDto(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());
        return postDTO;
    }

    private CommentDTO commentToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setBody(comment.getBody());
        commentDTO.setId(comment.getId());
        return commentDTO;
    }
}
// END
