package com.cavemen.dbscw.entities.order;

import com.cavemen.dbscw.entities.article.ArticleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final ArticleRepository articleRepository;

    @Autowired
    public OrderService(OrderRepository repository, ArticleRepository articleRepository) {
        this.repository = repository;
        this.articleRepository = articleRepository;
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Optional<Order> createOrder(Order order){
        for(int i = 0; i < order.getWishList().size(); i++){
            if(articleRepository.findById(order.getWishList().get(i).getId()).isEmpty()){
                return Optional.empty();
            }
        }
        return Optional.of(repository.save(order));
    }

    public Optional<Order> getById(String id){
        return repository.findById(id);
    }

    public Optional<Order> addItem(OrderItem customKeyValue, String id){
        Optional<Order> order = repository.findById(id);
        order.get().addItem(customKeyValue);
        return Optional.of(repository.save(order.get()));
    }

    @PostConstruct
    public void init(){
        repository.deleteAll();
    }

}
