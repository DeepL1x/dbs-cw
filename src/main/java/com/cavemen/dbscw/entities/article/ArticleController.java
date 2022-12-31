package com.cavemen.dbscw.entities.article;

import com.cavemen.dbscw.entities.userForAuthorizationService.UserForAuthorization;
import com.cavemen.dbscw.entities.userForAuthorizationService.UserForAuthorizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;
    private final UserForAuthorizationRepository userForAuthorizationRepository;

    @Autowired
    public ArticleController(ArticleService articleService, UserForAuthorizationRepository userForAuthorizationRepository) {
        this.articleService = articleService;
        this.userForAuthorizationRepository = userForAuthorizationRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Article>> getArticles(){
//        userForAuthorizationRepository.save(new UserForAuthorization("3","password"));
//        System.out.println(userForAuthorizationRepository.findById("3").get().getPassword());
        return new ResponseEntity<>(articleService.getArticles(), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Optional<Article>> getArticleId(@PathVariable("id") String id){
        return new ResponseEntity<>(articleService.getArticleById(id), HttpStatus.OK);
    }

    @PostMapping(
            value = "/add",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Article> addArticle(@RequestBody Article article){
        return new ResponseEntity<>(articleService.addArticle(article), HttpStatus.OK);
    }

    @PutMapping(
            value = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Article> updateArticle(@RequestBody Article article){
        return new ResponseEntity<>(articleService.updateArticle(article), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable("id") String id){
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}