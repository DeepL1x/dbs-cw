package com.cavemen.dbscw.entities.userForAuthorizationService;

import com.cavemen.dbscw.exception.CustomException;
import com.cavemen.dbscw.exception.ErrorCode;

public class UserForAuthorizationService {

  private final UserForAuthorizationRepository userForAuthorizationRepository;

  public UserForAuthorizationService(UserForAuthorizationRepository userForAuthorizationRepository) {
    this.userForAuthorizationRepository = userForAuthorizationRepository;
  }

  public UserForAuthorization validation(UserForAuthorization userForAuthorization) {
      if(!userForAuthorizationRepository.existsById(userForAuthorization.getId()))
        throw new CustomException(ErrorCode.Invalid_User_Login_Or_Password);
      if(!userForAuthorizationRepository.findById(userForAuthorization.getId())
          .get().getPassword().
          equals(userForAuthorization.getPassword()))
        throw new CustomException(ErrorCode.Invalid_User_Login_Or_Password);
      //TODO звідси починати конект з Neo4j
      return userForAuthorization;
      //Цей ретурн видалити і повертати нашого юзера з даними

  }
}
