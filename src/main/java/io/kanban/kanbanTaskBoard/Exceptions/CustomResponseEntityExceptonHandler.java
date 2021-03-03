package io.kanban.kanbanTaskBoard.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RestController
public class CustomResponseEntityExceptonHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectException(ProjectIdException ex, WebRequest req){

        ProjectIdExceptionResonse ExceptionResonse = new ProjectIdExceptionResonse(ex.getMessage());
        return new ResponseEntity(ExceptionResonse, HttpStatus.BAD_REQUEST);
    }
}
