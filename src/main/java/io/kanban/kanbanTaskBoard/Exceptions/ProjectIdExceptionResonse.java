package io.kanban.kanbanTaskBoard.Exceptions;

public class ProjectIdExceptionResonse {


    private String projectIdentifier;

    public ProjectIdExceptionResonse(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }


}
