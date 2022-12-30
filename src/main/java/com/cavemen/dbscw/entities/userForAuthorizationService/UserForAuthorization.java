package com.cavemen.dbscw.entities.userForAuthorizationService;

import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("User")
public class UserForAuthorization implements Serializable {
  @Id
  private String id;
  private String password;
  public UserForAuthorization(){

  }

  public UserForAuthorization(String id, String password) {
    this.id = id;
    this.password = password;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserForAuthorization that)) {
      return false;
    }
    return getId().equals(that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
