package com.cavemen.dbscw.services;

import com.cavemen.dbscw.entities.article.Article;
import com.cavemen.dbscw.entities.article.ArticleRepository;
import com.cavemen.dbscw.entities.article.ArticleService;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

  private static Article article1 =
      new Article("1", "лопата", "shovel",
          "Epicentre", Date.valueOf(LocalDate.of(2024, 10, 2)));

  private static Article article2 =
      new Article("2", "лопата", "shovel",
          "Epicentre", Date.valueOf(LocalDate.of(2024, 10, 3)));

  @Mock
  ArticleRepository articleRepository;


  private ArticleService articleService;

  @BeforeEach
  void setService() {
    articleService = new ArticleService(articleRepository);
  }

  @Test
  void getArticlesTest() {
    when(articleRepository.findAll()).thenReturn(List.of(article1, article2));
    assertEquals(List.of(article1, article2), articleService.getArticles());
  }

  @Test
  void addArticleTest() {
    when(articleRepository.save(article1)).thenReturn(article1);
    assertEquals(article1, articleService.addArticle(article1));
  }

  @Test
  void getArticleByIdTest() {
    when(articleRepository.findById("1")).thenReturn(Optional.ofNullable(article1));
    assertEquals(article1, articleService.getArticleById("1").get());
  }

  @Test
  void updateArticleTest() {
    when(articleRepository.save(article1)).thenReturn(article1);
    assertEquals(article1, articleService.updateArticle(article1));
  }

  @Test
  void deleteArticleTest() {
    doNothing().when(articleRepository).deleteById("1");
    assertDoesNotThrow(() -> articleService.deleteArticle("1"));
  }
}
