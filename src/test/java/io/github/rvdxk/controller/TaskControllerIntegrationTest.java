package io.github.rvdxk.controller;

import io.github.rvdxk.model.Task;
import io.github.rvdxk.model.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles ("integration")

class TaskControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository repo;

    @Test
    void httpGet_returnsGivenTasks() throws Exception {
        //given
        int id = repo.save(new Task("foo", LocalDateTime.now())).getId();

        //when+then
        mockMvc.perform(get("/tasks/" + id))
                .andExpect(status().is2xxSuccessful()
                );
    }
}
