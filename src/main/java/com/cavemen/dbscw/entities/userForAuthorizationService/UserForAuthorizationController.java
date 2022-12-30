package com.cavemen.dbscw.entities.userForAuthorizationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserForAuthorizationController {

  private final UserForAuthorizationService userForAuthorizationService;

  public UserForAuthorizationController(UserForAuthorizationService userForAuthorizationService) {
    this.userForAuthorizationService = userForAuthorizationService;
  }

  @PostMapping(
      value = "/authorization",
      consumes = "application/json",
      produces = "application/json"
  )
  public ResponseEntity<UserForAuthorization> authorization(@RequestBody UserForAuthorization userForAuthorization){
    return new ResponseEntity<>(userForAuthorizationService.validation(userForAuthorization),HttpStatus.OK);
  }
}
