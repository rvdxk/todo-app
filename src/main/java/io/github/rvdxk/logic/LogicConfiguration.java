package io.github.rvdxk.logic;

import io.github.rvdxk.TaskConfigurationProperties;
import io.github.rvdxk.model.ProjectRepository;
import io.github.rvdxk.model.TaskGroupRepository;
import io.github.rvdxk.model.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
    public class LogicConfiguration {
    @Bean
    ProjectService projectService(
            final ProjectRepository repository,
            final TaskGroupRepository taskGroupRepository,
            final TaskGroupService taskGroupService,
            final TaskConfigurationProperties config
    ) {
        return new ProjectService(repository, taskGroupRepository, taskGroupService, config);
    }

    @Bean
    TaskGroupService taskGroupService (
        final TaskGroupRepository taskGroupRepository,
        final TaskRepository repository
    ) {
        return new TaskGroupService(taskGroupRepository, repository);
    }
}
