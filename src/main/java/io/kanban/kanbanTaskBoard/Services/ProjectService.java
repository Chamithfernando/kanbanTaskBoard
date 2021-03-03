package io.kanban.kanbanTaskBoard.Services;

import io.kanban.kanbanTaskBoard.Exceptions.ProjectIdException;
import io.kanban.kanbanTaskBoard.domain.Project;
import io.kanban.kanbanTaskBoard.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    public Boolean FindProjectIdentifier(Project project) {
         projectRepository.FindProjectIdentifier(project.getProjectIdentifier().isBlank());
         boolean x = project.getProjectIdentifier().isBlank();
         return x;
    }

    public Project saveOrUpdate(Project project) {

        //Logic
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            if (!FindProjectIdentifier(project)) {
                return projectRepository.save(project);
            }
        return null;
        } catch (Exception e) {
            throw new ProjectIdException("Project '" + project.getProjectIdentifier().toUpperCase() + "' already exits");
        }


    }

}
