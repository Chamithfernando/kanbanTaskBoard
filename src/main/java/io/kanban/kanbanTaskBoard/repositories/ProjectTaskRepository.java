package io.kanban.kanbanTaskBoard.repositories;

import io.kanban.kanbanTaskBoard.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {


}
