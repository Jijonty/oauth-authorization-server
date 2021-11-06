package com.baeldung.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticlesController {

    @PreAuthorize("hasAuthority('SCOPE_articles.read')")
    @GetMapping("/articles")
    public String[] getArticles() {
        return new String[]{"Article 1", "Article 2", "Article 3"};
    }
}