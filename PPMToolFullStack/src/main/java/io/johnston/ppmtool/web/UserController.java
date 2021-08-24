package io.johnston.ppmtool.web;

import io.johnston.ppmtool.domain.User;
import io.johnston.ppmtool.services.UserService;
import io.johnston.ppmtool.utils.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
    ResponseEntity<?> errorMap = MapValidationErrorService.mapValidationService(result);

    if (errorMap != null) {
      return errorMap;
    }

    User newUser = userService.saveUser(user);

    return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
  }
}
