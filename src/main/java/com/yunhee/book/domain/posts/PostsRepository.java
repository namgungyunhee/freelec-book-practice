package com.yunhee.book.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// 제너릭에는 Entity 이름, PK key 타입이 들어간다.
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
