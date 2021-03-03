package io.kanban.kanbanTaskBoard.repositories;

import io.kanban.kanbanTaskBoard.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    public boolean FindProjectIdentifier(boolean projectIdentifier);

}
