package com.cavemen.dbscw.entities.article;

import com.cavemen.dbscw.entities.readyItem.ReadyItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, String> {

}
