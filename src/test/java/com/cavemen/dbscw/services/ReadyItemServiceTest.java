package com.cavemen.dbscw.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.cavemen.dbscw.entities.article.Article;
import com.cavemen.dbscw.entities.article.ArticleRepository;
import com.cavemen.dbscw.entities.readyItem.ReadyItem;
import com.cavemen.dbscw.entities.readyItem.ReadyItemRepository;
import com.cavemen.dbscw.entities.readyItem.ReadyItemService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
class ReadyItemServiceTest {

  private static ReadyItem readyItem1 =
      new ReadyItem("1", 4L, 4L, "4", 20.5, "unit1");
  private static ReadyItem readyItem2 =
      new ReadyItem("2", 5L, 5L, "5", 21.5, "unit2");
  private static Article article =
          new Article("1", "лопата", "shovel",
                  "Epicentre", Date.valueOf(LocalDate.of(2024, 10, 2)));
  @Mock
  ReadyItemRepository readyItemRepository;

  @Mock
  ArticleRepository articleRepository;


  private ReadyItemService readyItemService;

  @BeforeEach
  void setService() {
    readyItemService = new ReadyItemService(readyItemRepository, articleRepository);
  }

  @Test
  void getReadyItemsTest() {
    when(readyItemRepository.findAll()).thenReturn(List.of(readyItem1, readyItem2));
    assertEquals(List.of(readyItem1, readyItem2), readyItemService.getReadyItems());
  }

  @Test
  void addReadyItemTest() {
    when(readyItemRepository.save(readyItem1)).thenReturn(readyItem1);
    when(articleRepository.findById(article.getId())).thenReturn(Optional.of(article));
    assertEquals(readyItem1,
        readyItemService.addReadyItem(readyItem1));
  }

  @Test
  void getReadyItemByIdTest() {
    when(readyItemRepository.findById("1")).thenReturn(Optional.of(readyItem1));
    assertEquals(readyItem1, readyItemService.getReadyItemById("1").get());
  }

  @Test
  void updateReadyItemTest() {
    when(readyItemRepository.save(readyItem1)).thenReturn(readyItem1);
    assertEquals(readyItem1,
        readyItemService.updateReadyItem(readyItem1));
  }

  @Test
  void getReadyItemsDescByPriceTest() {
    when(readyItemRepository.findAll(Sort.by(Sort.Direction.DESC, "price"))).thenReturn(List.of(readyItem2, readyItem1));
    assertEquals(List.of(readyItem2, readyItem1), readyItemService.getReadyItemsDescByPrice());
  }

  @Test
  void deleteCustomerTest() {
    doNothing().when(readyItemRepository).deleteById("1");
    assertDoesNotThrow(() -> readyItemService.deleteReadyItem("1"));
  }
}
