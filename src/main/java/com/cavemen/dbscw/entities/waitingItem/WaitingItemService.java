package com.cavemen.dbscw.entities.waitingItem;

import com.cavemen.dbscw.entities.article.Article;
import com.cavemen.dbscw.entities.article.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WaitingItemService {
    private final WaitingItemRepository waitingItemRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public WaitingItemService(WaitingItemRepository waitingItemRepository, ArticleRepository articleRepository) {
        this.waitingItemRepository = waitingItemRepository;
        this.articleRepository = articleRepository;
    }

    public List<WaitingItem> getItems(){
        return waitingItemRepository.findAll();
    }

    public List<WaitingItem> getItemsDescByPrice(){
        return waitingItemRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
    }

    public WaitingItem addItem(WaitingItem waitingItem){
            Optional<Article> article = articleRepository.findById(waitingItem.getId());
            waitingItem.setArticle(article.get());
            return waitingItemRepository.save(waitingItem);
    }

    public Optional<WaitingItem> getItemById(String id){
        return waitingItemRepository.findById(id);
    }

    public WaitingItem updateItem(WaitingItem waitingItem){
        return waitingItemRepository.save(waitingItem);
    }

    public void deleteItem(String id){
        waitingItemRepository.deleteById(id);
    }
}
