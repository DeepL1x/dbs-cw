package com.cavemen.dbscw.entities.article;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/")
    public List<Article> getArticles(){
        return articleRepository.findAll();
    }
    @PostMapping("/0")
    public void addArticle(){
        articleRepository.save(
                new Article(
                "лопата",
                "shovel",
                "UkrSpecSystems"
                )
        );
    }
}
