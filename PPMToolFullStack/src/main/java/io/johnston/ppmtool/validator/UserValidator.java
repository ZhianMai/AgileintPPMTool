package io.johnston.ppmtool.validator;

import io.johnston.ppmtool.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

  private static final int PASSWORD_MIN_LENGTH = 6;
  @Override
  public boolean supports(Class<?> aClass) {
    return User.class.equals(aClass);
  }

  @Override
  public void validate(Object object, Errors errors) {
    User user = (User)object;

    if (user.getPassword().length() < PASSWORD_MIN_LENGTH) {
      errors.rejectValue("password","Length", "Password has to be at least 6 digits.");
    }

    if (!user.getPassword().equals(user.getConfirmPassword())) {
      errors.rejectValue("confirmPassword","Match", "Password and confirm password do not match.");
    }
  }
}
