package com.cavemen.dbscw.entities.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getArticles(){
        return articleRepository.findAll();
    }

    public Article addArticle(Article article){
        return articleRepository.save(article);
    }

    public Optional<Article> getArticleById(String id){
        return articleRepository.findById(id);
    }

    public Article updateArticle(Article article){
        return articleRepository.save(article);
    }

    public void deleteArticle(String id){
        articleRepository.deleteById(id);
    }
}
