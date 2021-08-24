package io.johnston.ppmtool.services;

import io.johnston.ppmtool.domain.User;
import io.johnston.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public User saveUser(User newUser) {
    newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

    // Username has to be unique, throw exception.

    // Confirm password match.

    return userRepository.save(newUser);
  }
}
