package com.yunhee.book.web.dto;

import java.time.LocalDateTime;

import com.yunhee.book.domain.posts.Posts;

// 구조체와 같은 존재
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }

}
