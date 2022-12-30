package com.cavemen.dbscw.entities.userForAuthorizationService;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserForAuthorizationRepository extends CrudRepository<UserForAuthorization,String>{

}
