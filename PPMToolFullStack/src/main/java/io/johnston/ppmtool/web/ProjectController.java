package io.johnston.ppmtool.web;

import io.johnston.ppmtool.domain.Project;
import io.johnston.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
  @Autowired
  private ProjectService projectService;

  @PostMapping("")
  // ResponseEntity<> is a type that allow us to have more control on JSON response
  public ResponseEntity<Project> CreateNewProject(@RequestBody Project project) {
    // Save request data to database
    Project project1 = projectService.saveOrUpdateProject(project);

    return new ResponseEntity<Project>(project, HttpStatus.CREATED);
  }
}
