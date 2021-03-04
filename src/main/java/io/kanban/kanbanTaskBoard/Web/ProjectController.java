package io.kanban.kanbanTaskBoard.Web;

import io.kanban.kanbanTaskBoard.Exceptions.ProjectIdException;
import io.kanban.kanbanTaskBoard.Services.MappValidationErrorService;
import io.kanban.kanbanTaskBoard.Services.ProjectService;
import io.kanban.kanbanTaskBoard.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MappValidationErrorService mappValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){


//        if (result.hasErrors()) {
//            return new ResponseEntity<String>("invalid project object",HttpStatus.BAD_REQUEST);
//        }

        // SpringBoot user input validation by using Binding result
//        if (result.hasErrors()) {
//
//            Map<String,String> errorMap = new HashMap<>();
//            for (FieldError error : result.getFieldErrors()){
//                errorMap.put(error.getField() , error.getDefaultMessage());
//            }
//
//            return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
//        }


        ResponseEntity<?> errorMap = mappValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        Project project1 =  projectService.saveOrUpdate(project);

        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }

    @GetMapping("get/{projectId}")
    public ResponseEntity<?> getProjectId(@PathVariable String projectId){
        Project project2 = projectService.findByProjectIdentifier(projectId);
        return new ResponseEntity<Project>(project2,HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){
        return projectService.findAllProjects();
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
        projectService.deleteProjectIdentifier(projectId);

        return new ResponseEntity<String>("Project with Id '"+projectId+"' was deleted ",HttpStatus.OK);
    }

    @PutMapping("/update/{projectId}")
    ResponseEntity<Project> updateAppointment(@PathVariable(value = "projectId") Long projectId,
                                              @Validated @RequestBody Project project){

        Project result = projectService.findbyId(projectId)
                .orElseThrow(() -> new ProjectIdException("Project id not found for this id :: " + projectId));


        Project project2 = projectService.UpdateProjectById(project,projectId);
        return ResponseEntity.ok().body(project2);
    }

}
