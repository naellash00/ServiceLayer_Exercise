package com.example.newsmanagementsystem.Service;

import com.example.newsmanagementsystem.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewsArticleService {
    ArrayList<NewsArticle> newsArticles = new ArrayList<>();

    public ArrayList<NewsArticle> getNews() {
        return newsArticles;
    }

    public void addNews(NewsArticle newsArticle) {
        newsArticles.add(newsArticle);
    }

    public boolean updateNews(String id, NewsArticle news){
        // first case : id not found -> return false here
        // second case : news updated successfully -> return true here
        // third case : news not updated successfully -> handled by validation in controller
        for (int i = 0; i < newsArticles.size(); i++) {
            if(newsArticles.get(i).getId().equals(id)){
                newsArticles.set(i, news);
                return true;
            }
        }
        return false; // id dose not exist
    }

    public boolean deleteNews(String id){
        // case 1 -> id not found return false
        // case 2 -> id found return true and delete
        for(int i = 0; i < newsArticles.size(); i++) {
            if(newsArticles.get(i).getId().equals(id)){
                newsArticles.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean publish(String id){
        for (int i = 0; i < newsArticles.size(); i++) {
            if(newsArticles.get(i).getId().equals(id)){
                newsArticles.get(i).setPublished(true);
                return true;
            }
        }
        return false;
    }

    public ArrayList<NewsArticle> getPublishedNews(){
        ArrayList<NewsArticle> publishedNewsArticles = new ArrayList<>();
        for(NewsArticle news : newsArticles){
            if(news.isPublished())
                publishedNewsArticles.add(news);
        }
        return publishedNewsArticles;
    }

    public ArrayList<NewsArticle> getNewsByCategory(String category){
        ArrayList<NewsArticle> sameCategoryNews = new ArrayList<>();
        for(NewsArticle news : newsArticles){
            if(news.getCategory().equalsIgnoreCase(category)){
                sameCategoryNews.add(news);
            }
        }
        return sameCategoryNews;
    }
}
