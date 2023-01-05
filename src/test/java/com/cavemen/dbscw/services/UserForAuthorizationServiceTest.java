package com.cavemen.dbscw.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.cavemen.dbscw.entities.userForAuthorizationService.UserForAuthorization;
import com.cavemen.dbscw.entities.userForAuthorizationService.UserForAuthorizationRepository;
import com.cavemen.dbscw.entities.userForAuthorizationService.UserForAuthorizationService;
import com.cavemen.dbscw.entities.worker.Role;
import com.cavemen.dbscw.entities.worker.Worker;
import com.cavemen.dbscw.entities.worker.WorkerService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserForAuthorizationServiceTest {

  private static UserForAuthorization user1 =
      new UserForAuthorization("1", "password1");

  private static UserForAuthorization user2 =
      new UserForAuthorization("2", "password2");

  private static Worker worker1 =
      new Worker(1L, "1", "name1", Role.WORKER);

  private static Worker worker2 =
      new Worker(2L, "2", "name2", Role.MANAGER);

  @Mock
  UserForAuthorizationRepository userForAuthorizationRepository;

  @Mock
  WorkerService workerService;

  private UserForAuthorizationService userForAuthorizationService;

  @BeforeEach
  void setService() {
    userForAuthorizationService =
        new UserForAuthorizationService(userForAuthorizationRepository, workerService);
  }

  @Test
  void getUsersTest() {
    when(userForAuthorizationRepository.findAll()).thenReturn(List.of(user1, user2));
    assertEquals(List.of(user1, user2), userForAuthorizationService.getUsers());
  }

  @Test
  void validationWorkerRoleTest() {
    when(userForAuthorizationRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
    when(userForAuthorizationRepository.existsById(user1.getId())).thenReturn(true);
    when(workerService.getByLogin(user1.getId())).thenReturn(worker1);
    assertEquals(user1.getId(), worker1.getLogin());
    assertEquals(userForAuthorizationService.validation(user1), worker1);
    assertEquals(workerService.getByLogin(user1.getId()).getRole(), Role.WORKER);
  }

  @Test
  void validationManagerRoleTest() {
    when(userForAuthorizationRepository.findById(user2.getId())).thenReturn(Optional.of(user2));
    when(userForAuthorizationRepository.existsById(user2.getId())).thenReturn(true);
    when(workerService.getByLogin(user2.getId())).thenReturn(worker2);
    assertEquals(user2.getId(), worker2.getLogin());
    assertEquals(userForAuthorizationService.validation(user2), worker2);
    assertEquals(workerService.getByLogin(user2.getId()).getRole(), Role.MANAGER);
  }

}
