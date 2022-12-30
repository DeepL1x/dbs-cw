package com.cavemen.dbscw.entities.userForAuthorizationService;

import com.cavemen.dbscw.exception.CustomException;
import com.cavemen.dbscw.exception.ErrorCode;
import jakarta.persistence.EntityNotFoundException;

public class UserForAuthorizationService {

  private final UserForAuthorizationRepository userForAuthorizationRepository;

  public UserForAuthorizationService(UserForAuthorizationRepository userForAuthorizationRepository) {
    this.userForAuthorizationRepository = userForAuthorizationRepository;
  }

  public UserForAuthorization validation(UserForAuthorization userForAuthorization) {
    try {
      userForAuthorizationRepository.findById(userForAuthorization.getId());
      //TODO звідси починати конект з Neo4j
      return userForAuthorization;
      //Цей ретурн видалити і повертати нашого юзера з даними
    }
    catch (EntityNotFoundException e) {
      throw new CustomException(ErrorCode.Invalid_User_Login_Or_Password);
    }
  }
}
