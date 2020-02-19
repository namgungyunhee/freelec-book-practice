package com.yunhee.book.web.dto;

import com.yunhee.book.domain.posts.Posts;

import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entitiy) {
        this.id = entitiy.getId();
        this.title = entitiy.getTitle();
        this.author = entitiy.getAuthor();
        this.content = entitiy.getContent();
    }
}
