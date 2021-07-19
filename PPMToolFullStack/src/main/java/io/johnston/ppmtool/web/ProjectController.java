package io.johnston.ppmtool.web;

import io.johnston.ppmtool.domain.Project;
import io.johnston.ppmtool.utils.MapValidationErrorService;
import io.johnston.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

  @Autowired
  private ProjectService projectService;

  @PostMapping("")
  // ResponseEntity<> is a type that allow us to have more control on JSON response
  // BindingResult is an error phase, an analysis of an obj.
  public ResponseEntity<?> CreateNewProject(@Valid @RequestBody Project project,
                                                  BindingResult result) {
    ResponseEntity<?> errorMap = MapValidationErrorService.mapValidationService(result);

    if (errorMap != null) {
      return errorMap;
    }

    // Save request data to database
    Project project1 = projectService.saveOrUpdateProject(project);

    return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
  }

  @GetMapping("/{projectId}")
  public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
    Project project = projectService.findProjectByIdentifier(projectId);

    return new ResponseEntity<Project>(project, HttpStatus.OK);
  }

  @GetMapping("/name/{projectName}")
  public ResponseEntity<?> getProjectByName(@PathVariable String projectName) {
    Iterable<Project> projects = projectService.findProjectByName(projectName);

    return new ResponseEntity<Iterable<Project>>(projects, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<?>  getAllProjects() {
    // If no such project, return an empty JSON
    Iterable<Project> projects = projectService.findAllProjects();

    return new ResponseEntity<Iterable<Project>>(projects, HttpStatus.OK);
  }

  // This implementation works as well
  // public Iterable<Project> findAllProjects() {
  //   return projectService.findAllProjects();
  // }

  @DeleteMapping("/{projectId}")
  public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
    projectService.deleteProjectByIdentifier(projectId);
    String responseMessage = "Project with ID '" + projectId + "' deleted";

    return new ResponseEntity<String>(responseMessage, HttpStatus.OK);
  }

}
