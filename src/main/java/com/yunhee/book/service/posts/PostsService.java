package com.yunhee.book.service.posts;


import com.yunhee.book.domain.posts.Posts;
import com.yunhee.book.domain.posts.PostsRepository;
import com.yunhee.book.web.dto.PostsListResponseDto;
import com.yunhee.book.web.dto.PostsResponseDto;
import com.yunhee.book.web.dto.PostsSaveRequestDto;
import com.yunhee.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        // 더티체킹 후 수정사항이 있을 시 Transaction 종료하고 반영 됨
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }


    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        postsRepository.delete(posts);
    }

    // 트랜젝션의 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선되기 때문
    // 등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에서 사용할 것을 추천
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        // 람다식
        //.map(PostsListResponseDto::new) = .map(posts -> new PostsListResponseDto(posts))
        return postsRepository.findAllDesc()
                .stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
