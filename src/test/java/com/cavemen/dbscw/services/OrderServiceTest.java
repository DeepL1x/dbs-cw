package com.cavemen.dbscw.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.cavemen.dbscw.entities.article.Article;
import com.cavemen.dbscw.entities.article.ArticleRepository;
import com.cavemen.dbscw.entities.order.Order;
import com.cavemen.dbscw.entities.order.OrderItem;
import com.cavemen.dbscw.entities.order.OrderRepository;
import com.cavemen.dbscw.entities.order.OrderService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

  static ArrayList<OrderItem> list1= new ArrayList<>();
  static ArrayList<OrderItem> list2= new ArrayList<>();

  static ArrayList<OrderItem> list3= new ArrayList<>();

  private static Article article =
          new Article("1", "лопата", "shovel",
                  "Epicentre", Date.valueOf(LocalDate.of(2024, 10, 2)));

  private static final Order order1 =
      new Order("1","address1",list1);

  private static final Order order2 =
      new Order("2","address2",list2);
  private static final Order order3 =
      new Order("2","address2",list3);

  private static final OrderItem orderItem1 =
          new OrderItem("1",1L);
  private static final OrderItem orderItem =
      new OrderItem("3",3L);

  @Mock
  OrderRepository orderRepository;

  @Mock
  ArticleRepository articleRepository;


  private OrderService orderService;

  @BeforeEach
  void setService() {
    list1.add(orderItem1);
    list2.add(new OrderItem("2",2L));
    list3.add(orderItem1);
    list3.add(orderItem);
    orderService = new OrderService(orderRepository,articleRepository);
  }

  @Test
  void getAllOrdersTest() {
    when(orderRepository.findAll()).thenReturn(List.of(order1, order2));
    assertEquals(List.of(order1, order2), orderService.getAllOrders());
  }

  @Test
  void createOrderTest() {
    when(orderRepository.save(order1)).thenReturn(order1);
    when(articleRepository.findById(order1.getId())).thenReturn(Optional.of(article));
    assertEquals(Optional.of(order1), orderService.createOrder(order1));
  }

  @Test
  void getByIdTest() {
    when(orderRepository.findById(order1.getId())).thenReturn(Optional.of(order1));
    assertEquals(order1, orderService.getById(order1.getId()).get());
  }

//  @Test
//  void addItemTest() {
//    when(orderRepository.findById(order1.getId())).thenReturn(Optional.of(order1));
//    order1.addItem(orderItem);
//    assertEquals(order3.getWishList(), order1.getWishList());
//  }

}
