package com.cavemen.dbscw.entities.userForAuthorizationService;

import com.cavemen.dbscw.entities.readyItem.ReadyItem;
import com.cavemen.dbscw.entities.worker.Worker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserForAuthorizationController {

  private final UserForAuthorizationService userForAuthorizationService;

  public UserForAuthorizationController(UserForAuthorizationService userForAuthorizationService) {
    this.userForAuthorizationService = userForAuthorizationService;
  }

  @GetMapping("/usersForAuth")
  public ResponseEntity<List<UserForAuthorization>> getUsersForAuth() {
    return new ResponseEntity<>(userForAuthorizationService.getUsers(), HttpStatus.OK);
  }

  @PostMapping(
      value = "/authorization",
      consumes = "application/json",
      produces = "application/json"
  )
  public ResponseEntity<Worker> authorization(@RequestBody UserForAuthorization user){
    return new ResponseEntity<>(userForAuthorizationService.validation(user),HttpStatus.OK);
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
