package com.example.newsmanagementsystem.Controller;

import com.example.newsmanagementsystem.ApiResponse.ApiResponse;
import com.example.newsmanagementsystem.Model.NewsArticle;
import com.example.newsmanagementsystem.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/news/system")
@RequiredArgsConstructor
public class NewsArticleController {
    private final NewsArticleService newsService;

    @GetMapping("/get")
    public ResponseEntity getNews() {
        ArrayList<NewsArticle> newsArticles = newsService.getNews();
        return ResponseEntity.status(200).body(newsArticles);
    }

    @PostMapping("/add")
    public ResponseEntity addNews(@RequestBody @Valid NewsArticle news, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        newsService.addNews(news);
        return ResponseEntity.status(200).body(new ApiResponse("News Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateNews(@PathVariable String id, @RequestBody @Valid NewsArticle news, Errors errors) {
        boolean isUpdated = newsService.updateNews(id, news);
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("News Article Updated Successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("News Article Not Found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteNews(@PathVariable String id){
        boolean isDeleted = newsService.deleteNews(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("News Deleted Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID Not Found"));
    }

    @PutMapping("/publish/{id}")
    public ResponseEntity publish(@PathVariable String id){
        boolean isPublished = newsService.publish(id);
        if(isPublished)
            return ResponseEntity.status(200).body(new ApiResponse("News Article Published Successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("ID Not Found"));
    }

    @GetMapping("/get/published/news")
    public ResponseEntity getPublishedNews(){
        ArrayList<NewsArticle> publishedNews = newsService.getPublishedNews();
        return ResponseEntity.status(200).body(publishedNews);
    }

    @GetMapping("/get/by/category/{category}")
    public ResponseEntity getNewsByCategory(@PathVariable String category){
        ArrayList<NewsArticle> sameCategoryNews = newsService.getNewsByCategory(category);
        if(sameCategoryNews.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("Incorrect Category. Must be of the three: politics - sports - technology"));

        return ResponseEntity.status(200).body(sameCategoryNews);
    }

}
