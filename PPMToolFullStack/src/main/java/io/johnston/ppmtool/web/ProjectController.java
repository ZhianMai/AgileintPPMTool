package io.johnston.ppmtool.web;

import io.johnston.ppmtool.domain.Project;
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

  @PostMapping("")
  // ResponseEntity<> is a type that allow us to have more control on JSON response
  // BindingResult is an error phase, an analysis of an obj.
  public ResponseEntity<?> CreateNewProject(@Valid @RequestBody Project project,
                                                  BindingResult result) {
    // Returning a meaningful JSON obj can let frontend React work easier.
    if (result.hasErrors()) {
      // return new ResponseEntity<String>("Invalid Project object", HttpStatus.BAD_REQUEST);
      // JSON pattern
      // {
      //    "field": "error message, value: ..."
      // }
      Map<String, String> errorMap = new HashMap<>();

      for (FieldError error: result.getFieldErrors()) {
        String message = error.getDefaultMessage();
        Object rejectVal = error.getRejectedValue();
        message += "; value: ";

        if (rejectVal != null) {
          message += rejectVal.toString();
        } else {
          message += "null";
        }

        errorMap.put(error.getField(), message);
      }

      return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
    }

    // Save request data to database
    Project project1 = projectService.saveOrUpdateProject(project);

    return new ResponseEntity<Project>(project, HttpStatus.CREATED);
  }
}
