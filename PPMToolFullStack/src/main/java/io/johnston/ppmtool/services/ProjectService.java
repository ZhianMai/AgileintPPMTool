package io.johnston.ppmtool.services;

import io.johnston.ppmtool.domain.Project;
import io.johnston.ppmtool.exceptions.ProjectIdException;
import io.johnston.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProjectService {

  @Autowired
  private ProjectRepository projectRepository;

  public Project saveOrUpdateProject(Project project) {
    try {
      project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

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
    Iterable<Project> projects = projectRepository.findByProjectName(projectName);

    return projects;
  }

  public Iterable<Project> findAllProjects() {
    return projectRepository.findAll();
  }
}
