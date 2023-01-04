package com.cavemen.dbscw.entities.readyItem;

import com.cavemen.dbscw.entities.article.Article;
import com.cavemen.dbscw.entities.article.ArticleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public List<ReadyItem> getReadyItems(){
        return readyItemRepository.findAll();
    }

    public List<ReadyItem> getReadyItemsDescByPrice(){
        return readyItemRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
    }

    public ReadyItem addReadyItem(ReadyItem readyItem){
        Optional<Article> article = articleRepository.findById(readyItem.getId());
        readyItem.setArticle(article.get());
        return readyItemRepository.save(readyItem);
    }

    public Optional<ReadyItem> getReadyItemById(String id){
        return readyItemRepository.findById(id);
    }

    public ReadyItem updateReadyItem(ReadyItem readyItem){
        ReadyItem itemEntity = readyItemRepository.findById(readyItem.getId()).get();
        ReadyItem returnEntity = new ReadyItem(
                readyItem.getTotalItemAmount(),
                readyItem.getReservedItemAmount(),
                readyItem.getStoragePlace(),
                readyItem.getPrice(),
                readyItem.getMeasureUnit()
                );
        BeanUtils.copyProperties(returnEntity, itemEntity, "id");

        return readyItemRepository.save(itemEntity);
    }

    public void deleteReadyItem(String id){
        System.out.println(id);
        readyItemRepository.deleteById(id);
    }
}
