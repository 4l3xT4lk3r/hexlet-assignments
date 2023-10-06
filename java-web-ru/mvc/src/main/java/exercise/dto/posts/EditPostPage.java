package exercise.dto.posts;

import java.util.List;
import java.util.Map;

import exercise.model.Post;
import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public final class EditPostPage {
    private final long id;
    private final String name;
    private final String body;
    private Map<String, List<ValidationError<Object>>> errors;
}

