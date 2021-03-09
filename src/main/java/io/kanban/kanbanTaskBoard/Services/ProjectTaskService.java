package io.kanban.kanbanTaskBoard.Services;

import io.kanban.kanbanTaskBoard.domain.Backlog;
import io.kanban.kanbanTaskBoard.domain.ProjectTask;
import io.kanban.kanbanTaskBoard.repositories.BacklogRepository;
import io.kanban.kanbanTaskBoard.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){

        // all project task to add the specific project.
        // != null project

        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

        //Set the backlog to the project task

        projectTask.setBacklog(backlog);

        //We want to project task sequence to be like this  IDE1 -1 , and IDE1-2
        Integer BacklogSequence = backlog.getPTSequence();


       //Update the backlog sequence
        BacklogSequence++;

        //Increment BackLogSequence into database
        //Keeping going the sequence
        backlog.setPTSequence(BacklogSequence);

        // Add sequence to Project task
        projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        //Initilal status when start is null

        if (projectTask.getStatus() == "" || projectTask.getStatus() == null){
            projectTask.setStatus("TO_DO");
        }

        //Initial priority when priorty null
        if (projectTask.getPriorty()== null){ // In the future we need to ProjectTask.getPriorty() ==0 to handle the form
            projectTask.setPriorty(3); // 3 is considerd as low priorty
        }



        return  projectTaskRepository.save(projectTask);

    }
}
