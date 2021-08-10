package io.johnston.ppmtool.services;

import io.johnston.ppmtool.domain.Backlog;
import io.johnston.ppmtool.domain.ProjectTask;
import io.johnston.ppmtool.repositories.BacklogRepository;
import io.johnston.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

  @Autowired
  private BacklogRepository backlogRepository;

  @Autowired
  private ProjectTaskRepository projectTaskRepository;

  public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
    // Project task to be added to a specific project, project != null
    // If project != null, then backlog != null
    Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

    // Set the backlog to project
    projectTask.setBacklog(backlog);

    // The project task sequence starts from 0, if i is deleted, then new
    // task should not use i again.
    Integer backlogSequence = backlog.getPTSequence();
    backlogSequence++;
    backlog.setPTSequence(backlogSequence);

    // Add sequence to project task
    projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
    projectTask.setProjectIdentifier(projectIdentifier);

    // Init priority is null
    if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
      projectTask.setPriority(3);
    }

    // Init status is null
    if (projectTask.getStatus() == null || projectTask.getStatus() == "") {
      projectTask.setStatus("TO_DO");
    }

    return projectTaskRepository.save(projectTask);
  }

  public List<ProjectTask> findBacklogById(String backlog_id) {
    return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
  }
}
