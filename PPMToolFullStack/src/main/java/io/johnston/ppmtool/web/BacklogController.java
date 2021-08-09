package io.johnston.ppmtool.web;

import io.johnston.ppmtool.domain.ProjectTask;
import io.johnston.ppmtool.services.ProjectTaskService;
import io.johnston.ppmtool.utils.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

  @Autowired
  private ProjectTaskService projectTaskService;

  private MapValidationErrorService mapValidationErrorService;

  @PostMapping("/{backlog_id}")
  public ResponseEntity<?> addProjectTasktoBacklog(@Valid @RequestBody ProjectTask projectTask,
                                                   BindingResult result,
                                                   @PathVariable String backlog_id) {
    ResponseEntity<?> errorMap = MapValidationErrorService.mapValidationService(result);

    if (errorMap != null) {
      return errorMap;
    }

    ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask);

    return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
  }
}
