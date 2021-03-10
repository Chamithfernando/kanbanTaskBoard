package io.kanban.kanbanTaskBoard.repositories;

import io.kanban.kanbanTaskBoard.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

   // Optional<ProjectTask> findById(Long id);

   List <ProjectTask> findByProjectIdentifier(String projectId);


   ProjectTask findByProjectSequence(String sequence);

}
