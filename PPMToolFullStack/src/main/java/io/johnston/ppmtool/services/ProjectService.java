package io.johnston.ppmtool.services;

import io.johnston.ppmtool.domain.Project;
import io.johnston.ppmtool.repositories.Projectrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
  @Autowired
  private Projectrepository projectrepository;

  public Project saveOrUpdateProject(Project project) {
    // Logic here
    return projectrepository.save(project);
  }
}
