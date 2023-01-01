package com.cavemen.dbscw.entities.userForAuthorizationService;

import com.cavemen.dbscw.entities.worker.Worker;
import com.cavemen.dbscw.entities.worker.WorkerService;
import com.cavemen.dbscw.exception.CustomException;
import com.cavemen.dbscw.exception.ErrorCode;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserForAuthorizationService {

  private final UserForAuthorizationRepository userForAuthorizationRepository;
  private final WorkerService workerService;
  public UserForAuthorizationService(UserForAuthorizationRepository userForAuthorizationRepository, WorkerService workerService) {
    this.userForAuthorizationRepository = userForAuthorizationRepository;
    this.workerService = workerService;
  }

  public Worker validation(UserForAuthorization userForAuthorization) {
      if(!userForAuthorizationRepository.existsById(userForAuthorization.getId()))
        throw new CustomException(ErrorCode.Invalid_User_Login_Or_Password);
      if(!userForAuthorizationRepository.findById(userForAuthorization.getId())
          .get().getPassword().
          equals(userForAuthorization.getPassword()))
        throw new CustomException(ErrorCode.Invalid_User_Login_Or_Password);
      //TODO звідси починати конект з Neo4j
      return workerService.getByLogin(userForAuthorization.getId());
  }

  public void save(UserForAuthorization user) {
    userForAuthorizationRepository.save(user);
  }

  public List<UserForAuthorization> getUsers() {
    return Streamable.of(userForAuthorizationRepository.findAll()).toList();
  }
}
