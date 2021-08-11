package io.johnston.ppmtool.services;

import io.johnston.ppmtool.domain.Backlog;
import io.johnston.ppmtool.domain.Project;
import io.johnston.ppmtool.domain.ProjectTask;
import io.johnston.ppmtool.exceptions.ProjectNotFoundException;
import io.johnston.ppmtool.repositories.BacklogRepository;
import io.johnston.ppmtool.repositories.ProjectRepository;
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

  @Autowired
  private ProjectRepository projectRepository;

  public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
    // Project task to be added to a specific project, project != null
    try {
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
    } catch (Exception e) {
      throw new ProjectNotFoundException("Project not found");
    }
  }

  public List<ProjectTask> findBacklogById(String project_id) {
    Project project = projectRepository.findByProjectIdentifier(project_id);

    if (project ==null) {
      throw new ProjectNotFoundException("Project with ID: " + project_id + " does not exist");
    }

    return projectTaskRepository.findByProjectIdentifierOrderByPriority(project_id);
  }

  public ProjectTask findProjectTaskByProjectSequence(String backlog_id, String projectTask_id) {
    // Check if project id exists
    Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
    if (backlog == null) {
      throw new ProjectNotFoundException("Project ID " + backlog_id + " doesnot exist.");
    }

    // Check if project task id exists
    ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectTask_id);
    if (projectTask == null) {
      throw new ProjectNotFoundException("Project task id: " + projectTask_id + " not found");
    }

    // Check if the project task is under the project
    if (!projectTask.getProjectIdentifier().equals(backlog_id)) {
      throw new ProjectNotFoundException("Project task id: " + projectTask_id +
          " is not under project id " + backlog_id + ".");
    }

    return projectTask;
  }

  public ProjectTask updateByProjectSequence(ProjectTask updatedProjectTask,
                                             String backlog_id, String projectTask_id) {
    // For validation project_id and projectTask_id, so ignore return value
    findProjectTaskByProjectSequence(backlog_id, projectTask_id);
    // Update project task
    return projectTaskRepository.save(updatedProjectTask);
  }
}
