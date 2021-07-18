package io.johnston.ppmtool.web;

import io.johnston.ppmtool.domain.Project;
import io.johnston.ppmtool.services.MapValidationErrorService;
import io.johnston.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
  @Autowired
  private ProjectService projectService;

  @Autowired
  private MapValidationErrorService mapValidationErrorService;

  @PostMapping("")
  // ResponseEntity<> is a type that allow us to have more control on JSON response
  // BindingResult is an error phase, an analysis of an obj.
  public ResponseEntity<?> CreateNewProject(@Valid @RequestBody Project project,
                                                  BindingResult result) {
    ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);

    if (errorMap != null) {
      return errorMap;
    }

    // Save request data to database
    Project project1 = projectService.saveOrUpdateProject(project);

    return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
  }
}
