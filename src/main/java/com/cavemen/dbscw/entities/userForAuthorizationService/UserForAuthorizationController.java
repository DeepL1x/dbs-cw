package com.cavemen.dbscw.entities.userForAuthorizationService;

import com.cavemen.dbscw.entities.readyItem.ReadyItem;
import com.cavemen.dbscw.entities.worker.Worker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
  public ResponseEntity<Worker> authorization(@RequestBody String login, @RequestBody String password){
    UserForAuthorization userForAuthorization = new UserForAuthorization(login, password);
    return new ResponseEntity<>(userForAuthorizationService.validation(userForAuthorization),HttpStatus.OK);
  }

  @PostMapping(
      value = "/addUsersForAuth"
//      consumes = "application/json",
//      produces = "application/json"
  )
  public void addUsersForAuth() {
    UserForAuthorization user1 = new UserForAuthorization("login1","password1");
    UserForAuthorization user2 = new UserForAuthorization("login2","password2");
    userForAuthorizationService.save(user1);
    userForAuthorizationService.save(user2);
  }
}
