package io.github.rvdxk.logic;

import io.github.rvdxk.TaskConfigurationProperties;
import io.github.rvdxk.model.*;
import io.github.rvdxk.model.projection.GroupReadModel;
import io.github.rvdxk.model.projection.GroupTaskWriteModel;
import io.github.rvdxk.model.projection.GroupWriteModel;
import io.github.rvdxk.model.projection.ProjectWriteModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectService {
    private ProjectRepository repository;
    private TaskGroupRepository taskGroupRepository;
    private TaskGroupService taskGroupService;
    private TaskConfigurationProperties config;

    public ProjectService(final ProjectRepository repository, final TaskGroupRepository taskGroupRepository,final TaskGroupService service, final TaskConfigurationProperties config) {
        this.repository = repository;
        this.taskGroupRepository = taskGroupRepository;
        this.taskGroupService = service;
        this.config = config;

    }

    public List<Project> readAll() {
        return repository.findAll();
    }

    public Project save(final ProjectWriteModel toSave) {
        return repository.save(toSave.toProject());
    }

    public GroupReadModel createGroup(LocalDateTime deadline, int projectId) {
        if (!config.getTemplate().isAllowMultipleTasks() && taskGroupRepository.existsByDoneIsFalseAndProject_Id(projectId)) {
            throw new IllegalStateException("Only one undone group from project is allowed");
        }
        return repository.findById(projectId)
                .map(project -> {
                    var targetGroup = new GroupWriteModel();
                    targetGroup.setDescription(project.getDescription());
                    targetGroup.setTasks(
                            project.getSteps().stream()
                                    .map(projectStep -> {
                                        var task = new GroupTaskWriteModel();
                                        task.setDescription(projectStep.getDescription());
                                        task.setDeadline(deadline.plusDays(projectStep.getDaysToDeadline()));
                                        return task;
                                    }
                                    ).collect(Collectors.toList())
                    );
                    try {
                        return taskGroupService.createGroup(targetGroup);
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                }).orElseThrow(() -> new IllegalArgumentException("Project with given id not found"));
    }
}

