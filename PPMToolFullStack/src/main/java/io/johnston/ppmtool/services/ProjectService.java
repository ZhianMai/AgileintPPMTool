package io.johnston.ppmtool.services;

import io.johnston.ppmtool.domain.Project;
import io.johnston.ppmtool.exceptions.ProjectIdException;
import io.johnston.ppmtool.repositories.Projectrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProjectService {

  @Autowired
  private Projectrepository projectrepository;

  public Project saveOrUpdateProject(Project project) {

    try {
      project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

      return projectrepository.save(project);
    } catch (Exception e) {
      throw new ProjectIdException("Project ID '" +
          project.getProjectIdentifier().toUpperCase() + "' already exists");
    }
  }

}
