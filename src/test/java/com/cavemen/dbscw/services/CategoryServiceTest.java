package com.cavemen.dbscw.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.cavemen.dbscw.entities.category.Category;
import com.cavemen.dbscw.entities.category.CategoryRepository;
import com.cavemen.dbscw.entities.category.CategoryService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
  private static Category category1 =
      new Category(1L,"title1");

  private static Category category2 =
      new Category(2L,"title2");

  @Mock
  CategoryRepository categoryRepository;


  private CategoryService categoryService;

  @BeforeEach
  void setService() {
    categoryService = new CategoryService(categoryRepository);
  }

  @Test
  void getAllCategoriesTest() {
    when(categoryRepository.findAll()).thenReturn(List.of(category1, category2));
    assertEquals(List.of(category1, category2), categoryService.getAllCategories());
  }

  @Test
  void addCategoryTest() {
    when(categoryRepository.save(category1)).thenReturn(category1);
    assertEquals(category1, categoryService.addCategory(new Category(1L,"title1")));
  }

  @Test
  void getCategoryByIdTest() {
    when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(category1));
    assertEquals(category1, categoryService.getCategoryById(1L).get());
  }

  @Test
  void updateCategoryTest() {
    when(categoryRepository.save(category1)).thenReturn(category1);
    assertEquals(category1, categoryService.updateCategory(new Category(1L,"title1")));
  }

  @Test
  void deleteCategoryTest() {
    doNothing().when(categoryRepository).deleteById(1L);
    assertDoesNotThrow(() -> categoryService.deleteCategory(1L));
  }
}
