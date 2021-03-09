package io.kanban.kanbanTaskBoard.repositories;


import io.kanban.kanbanTaskBoard.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {


}
