package com.cavemen.dbscw.entities.readyItem;

import com.cavemen.dbscw.entities.article.Article;
import com.cavemen.dbscw.entities.article.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReadyItemService {
    private final ReadyItemRepository readyItemRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public ReadyItemService(ReadyItemRepository readyItemRepository, ArticleRepository articleRepository) {
        this.readyItemRepository = readyItemRepository;
        this.articleRepository = articleRepository;
    }

    public List<com.cavemen.dbscw.entities.readyItem.ReadyItem> getReadyItems(){
        return readyItemRepository.findAll();
    }

    public com.cavemen.dbscw.entities.readyItem.ReadyItem addReadyItem(com.cavemen.dbscw.entities.readyItem.ReadyItem readyItem){
        Optional<Article> article = articleRepository.findById(readyItem.getId());
        readyItem.setArticle(article.get());
        return readyItemRepository.save(readyItem);
    }

    public Optional<com.cavemen.dbscw.entities.readyItem.ReadyItem> getReadyItemById(String id){
        return readyItemRepository.findById(id);
    }

    public com.cavemen.dbscw.entities.readyItem.ReadyItem updateReadyItem(com.cavemen.dbscw.entities.readyItem.ReadyItem readyItem){
        return readyItemRepository.save(readyItem);
    }

    public void deleteReadyItem(String id){
        readyItemRepository.deleteById(id);
    }
}
