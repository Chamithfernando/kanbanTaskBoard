package io.kanban.kanbanTaskBoard.Web;

import io.kanban.kanbanTaskBoard.Services.MappValidationErrorService;
import io.kanban.kanbanTaskBoard.Services.ProjectTaskService;
import io.kanban.kanbanTaskBoard.domain.ProjectTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
        ProjectTask projectTask1 = projectTaskService.addProjectTask(Backlog_id.toUpperCase(),projectTask);

        return  new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
    }


//    @GetMapping("/get/{Backlog_id}")
//    ResponseEntity<ProjectTask> getBacklogById(@PathVariable Long Backlog_id){
//        Optional<ProjectTask> doctorResult = projectTaskService.findbyid(Backlog_id);
//        return doctorResult.map(response ->ResponseEntity.ok().body(response))
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }


    @GetMapping("/get/{Backlog_id}")
    public Iterable<ProjectTask> getProjectbacklog(@PathVariable String Backlog_id){
        return projectTaskService.findBacklogbyID(Backlog_id);
    }


    @GetMapping("/get/{Backlog_id}/{pt_id}")
    public ResponseEntity<?> getProjectTask(@PathVariable String Backlog_id,@PathVariable String pt_id ){
        ProjectTask projectTask1 = projectTaskService.findPTByProjectSequence(Backlog_id,pt_id);
        return  new ResponseEntity<ProjectTask>(projectTask1,HttpStatus.OK);
    }
}
