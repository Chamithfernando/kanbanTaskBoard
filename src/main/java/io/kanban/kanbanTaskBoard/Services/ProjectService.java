package io.kanban.kanbanTaskBoard.Services;

import io.kanban.kanbanTaskBoard.Exceptions.ProjectIdException;
import io.kanban.kanbanTaskBoard.domain.Project;
import io.kanban.kanbanTaskBoard.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdate(Project project){

        //Logic
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw  new ProjectIdException("Project '"+project.getProjectIdentifier().toUpperCase()+"' already exits");
        }


    }


    public Project findByProjectIdentifier(String projectId){

        Project project3 = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project3 == null){
            throw new ProjectIdException("Project ID '"+projectId+"' does not exist");
        }

        return project3;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectIdentifier(String projectId){
        Project project4 = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project4 == null){
            throw new ProjectIdException("Project with ID'"+projectId+"' does not exits");
        }
        projectRepository.delete(project4);
    }

    public Project UpdateProjectById(Project project,Long projectId ){

        Optional<Project> project4 = projectRepository.findById(projectId);
        if (project4 == null){
            throw new ProjectIdException("Project ID '"+projectId+"' does not exist");
        }
        return projectRepository.save(project);
    }

    public Optional<Project> findbyId(Long id){
       return projectRepository.findById(id);
    }

}
