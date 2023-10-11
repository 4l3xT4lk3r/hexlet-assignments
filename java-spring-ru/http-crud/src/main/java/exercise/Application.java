package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
//    GET /posts - Список всех постов
//    GET /posts/{id} – Просмотр конкретного поста
//    POST /posts – Создание нового поста
//    PUT /posts/{id} – Обновление поста
//    DELETE /posts/{id} – Удаление поста
    @GetMapping("/posts")
    public List<Post> index(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        int startId = (page - 1) * limit;
        int endId = startId + limit;
        return posts.stream()
                .filter(p -> posts.indexOf(p) >= startId && posts.indexOf(p) < endId)
                .toList();
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> show(@PathVariable String id) {
        return posts.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @PostMapping("/posts")
    public Post create(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/posts/{id}")
    public Post update(@PathVariable String id, @RequestBody Post post) {
        Optional<Post> result = posts.stream().filter(p->p.getId().equals(id)).findFirst();
        if (result.isPresent()){
            Post oldPost = result.get();
            oldPost.setBody(post.getBody());
            oldPost.setTitle(post.getTitle());
            oldPost.setId(post.getId());
        }
        return post;
    }

    @DeleteMapping("/posts/{id}")
    public void delete(@PathVariable String id) {
        posts.removeIf(p->p.getId().equals(id));
    }
    // END
}
