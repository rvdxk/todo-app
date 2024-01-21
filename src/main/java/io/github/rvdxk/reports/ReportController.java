package io.github.rvdxk.reports;

import io.github.rvdxk.model.Task;
import io.github.rvdxk.model.TaskRepository;
import io.github.rvdxk.model.event.TaskDone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final TaskRepository taskRepository;
    private final PersistedTaskEventRepository eventRepository;

    public ReportController(TaskRepository taskRepository, PersistedTaskEventRepository eventRepository) {
        this.taskRepository = taskRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/count/{id}")
    ResponseEntity<TaskWithChangesCount> readTaskWithCount(@PathVariable int id) {
        return taskRepository.findById(id)
                .map(task -> new TaskWithChangesCount(task, eventRepository.findByTaskId(id)))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/doneBeforeDeadline")
    ResponseEntity<List<TaskDoneBeforeDeadline>> readTaskDoneBeforeDeadline() {
        List<TaskDoneBeforeDeadline> tasks = new ArrayList<>();
        taskRepository.findAll().stream().filter(task -> {
            if (task.getDeadline() == null && task.isDone())
                return true;

            Optional<PersistedTaskEvent> latestEvent = eventRepository.findByTaskId(task.getId()).stream()
                    .filter(event -> event.occurrence.isBefore(task.getDeadline()))
                    .max(Comparator.comparing(event -> event.occurrence));
            if (latestEvent.isPresent() && latestEvent.get().name.equals("TaskDoneEvent"))
                return true;
            return false;
        }).forEach(task -> tasks.add(new TaskDoneBeforeDeadline(task)));
        return ResponseEntity.ok(tasks);
    }

    private static class TaskWithChangesCount {
        public String description;
        public boolean done;
        public int changesCount;

        private TaskWithChangesCount(final Task task, final List<PersistedTaskEvent> events) {
            description = task.getDescription();
            done = task.isDone();
            changesCount = events.size();
        }
    }

    private static class TaskDoneBeforeDeadline {
        public int taskId;
        public String description;

        private TaskDoneBeforeDeadline(final Task task) {
            taskId = task.getId();
            description = task .getDescription();
        }
    }

}
