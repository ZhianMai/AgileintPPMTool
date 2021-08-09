package io.johnston.ppmtool.services;

import io.johnston.ppmtool.domain.Backlog;
import io.johnston.ppmtool.domain.Project;
import io.johnston.ppmtool.exceptions.ProjectIdException;
import io.johnston.ppmtool.repositories.BacklogRepository;
import io.johnston.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProjectService {

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private BacklogRepository backlogRepository;

  public Project saveOrUpdateProject(Project project) {
    String tempProjectIdentifier = project.getProjectIdentifier().toUpperCase();

    try {
      project.setProjectIdentifier(tempProjectIdentifier);

      if (project.getId() == null) {
        Backlog backlog = new Backlog();
        project.setBacklog(backlog);
        backlog.setProject(project);
        backlog.setProjectIdentifier(tempProjectIdentifier);
      }

      if (project.getId() != null) {
        project.setBacklog(backlogRepository.findByProjectIdentifier(tempProjectIdentifier));
      }

      return projectRepository.save(project);
    } catch (Exception e) {
      throw new ProjectIdException("Project ID '" +
          project.getProjectIdentifier().toUpperCase() + "' already exists");
    }
  }

  public Project findProjectByIdentifier (String projectId) {
    Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

    if (project == null) {
      // Using the old exception so far
      throw new ProjectIdException("Project ID '" + projectId + "' does not exist.");
    }

    return project;
  }

  public Iterable<Project> findProjectByName(String projectName) {
    return projectRepository.findByProjectName(projectName);
  }

  public Iterable<Project> findAllProjects() {
    return projectRepository.findAll();
  }

  public void deleteProjectByIdentifier(String projectId) {
    Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

    if (project == null) {
      // Using the old exception so far
      throw new ProjectIdException("Cannot delete project with ID '" + projectId +
          "': does not exist.");
    }

    projectRepository.delete(project);
  }
}
