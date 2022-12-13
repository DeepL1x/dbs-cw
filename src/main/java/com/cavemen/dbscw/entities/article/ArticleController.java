package com.cavemen.dbscw.entities.article;

import org.springframework.web.bind.annotation.*;

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
    @PostMapping("{temp}")
    public void addArticle(@PathVariable("temp") String temp){
        articleRepository.save(
                new Article(
                "лопата" + temp,
                "shovel",
                "UkrSpecSystems"
                )
        );
    }
}
