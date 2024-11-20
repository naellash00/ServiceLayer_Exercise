package com.example.newsmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NewsArticle {
    @NotEmpty(message = "ID Cannot Be Empty")
    @Size(max = 100, message = "ID Cant Be Longer Than 100")
    private String id;

    @NotEmpty(message = "Author Cannot Be Null")
    @Size(min = 2, max = 20, message = "Author Must Be Between 2-20 Characters")
    private String author;

    @NotEmpty(message = "Content Cannot Be Empty")
    @Size(min = 200, message = "Content must be longer than 200")
    private String content;

    @NotEmpty(message = "Category Cannot Be Empty")
    @Pattern(regexp = "^(politics|sports|technology)$", message = "category must be one of these: politics - sports - technology" )
    private String category;

    @NotEmpty(message = "Image URL Cannot Be Empty")
    private String imageURL;

    @AssertFalse
    private boolean isPublished;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishDate;

}
