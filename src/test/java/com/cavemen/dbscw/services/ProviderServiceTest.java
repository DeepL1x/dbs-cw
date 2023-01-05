package com.cavemen.dbscw.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.cavemen.dbscw.entities.provider.Provider;
import com.cavemen.dbscw.entities.provider.ProviderRepository;
import com.cavemen.dbscw.entities.provider.ProviderService;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProviderServiceTest {

  private static Provider provider1 =
      new Provider(1L,"name1","country1");

  private static Provider provider2 =
      new Provider(2L,"name2","country2");
  @Mock
  ProviderRepository providerRepository;


  private ProviderService providerService;

  private static final HashSet<String> set = new HashSet<>();;

  @BeforeEach
  void setService() {
    providerService = new ProviderService(providerRepository);
    set.add("category1");
    set.add("category2");
  }

  @Test
  void getAllProvidersTest() {
    when(providerRepository.findAll()).thenReturn(List.of(provider1, provider2));
    assertEquals(List.of(provider1, provider2), providerService.getAllProviders());
  }

  @Test
  void addProviderTest() {
    when(providerRepository.save(provider1)).thenReturn(provider1);
    assertEquals(provider1,
        providerService.addProvider(new Provider(1L, "name1", "country1")));
  }

  @Test
  void getProviderByIdTest() {
    when(providerRepository.findById(1L)).thenReturn(Optional.ofNullable(provider1));
    assertEquals(provider1, providerService.getProviderById(1L).get());
  }

  @Test
  void updateProviderTest() {
    when(providerRepository.save(provider1)).thenReturn(provider1);
    assertEquals(provider1, providerService.updateProvider(
        new Provider(1L, "name1", "country1")));
  }

  @Test
  void getCategoriesTest() {
    when(providerRepository.getCategories(1L)).thenReturn(set);
    assertEquals(new HashSet<String>(set), providerService.getCategories(1L));
  }

  @Test
  void deleteProviderTest() {
    doNothing().when(providerRepository).deleteById(1L);
    assertDoesNotThrow(() -> providerService.deleteProvider(1L));
  }
}
