package exercise.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

// END
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
// BEGIN
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper om;
    @Autowired
    private Faker faker;
    @Autowired
    private TaskRepository taskRepository;
    private Task testTask;

    @BeforeEach
    public void setUp() {
        testTask = new Task();
        testTask.setTitle(faker.lorem().word());
        testTask.setDescription(faker.lorem().paragraph());
        taskRepository.save(testTask);
    }

    @Test
    public void testShow() throws Exception {
        mockMvc.perform(get("/tasks/{id}", testTask.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(testTask)));
    }

    @Test
    public void testCreate() throws Exception{
        Task task = new Task();
        task.setTitle(faker.lorem().word());
        task.setDescription(faker.lorem().word());
        MockHttpServletRequestBuilder request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task));
        mockMvc.perform(request).andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {
        Task task = new Task();
        task.setTitle(faker.lorem().word());
        task.setDescription(faker.lorem().paragraph());

        MockHttpServletRequestBuilder request = put("/tasks/{id}", testTask.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        var actualPost = taskRepository.findById(testTask.getId()).get();

        assertThat(actualPost.getTitle()).isEqualTo(task.getTitle());
        assertThat(actualPost.getDescription()).isEqualTo(task.getDescription());
    }

    @Test
    public void testDelete() throws Exception {

        mockMvc.perform(delete("/tasks/{id}", testTask.getId()))
                .andExpect(status().isOk());
        assertThat(taskRepository.findAll()).isEmpty();
    }

}
// END

