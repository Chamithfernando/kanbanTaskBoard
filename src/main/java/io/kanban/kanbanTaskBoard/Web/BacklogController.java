package io.kanban.kanbanTaskBoard.Web;

import io.kanban.kanbanTaskBoard.Services.MappValidationErrorService;
import io.kanban.kanbanTaskBoard.Services.ProjectTaskService;
import io.kanban.kanbanTaskBoard.domain.Backlog;
import io.kanban.kanbanTaskBoard.domain.ProjectTask;
import io.kanban.kanbanTaskBoard.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private MappValidationErrorService mappValidationErrorService;

    @PostMapping("/save/{Backlog_id}")
    public ResponseEntity<?> addProjectBacklog(@Valid @RequestBody ProjectTask projectTask,
                                               BindingResult result, @PathVariable String Backlog_id){

        ResponseEntity<?> errorMap = mappValidationErrorService.MapValidationService(result);
        if (errorMap != null){
            return errorMap;
        }
        ProjectTask projectTask1 = projectTaskService.addProjectTask(Backlog_id,projectTask);

        return  new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
    }

}
