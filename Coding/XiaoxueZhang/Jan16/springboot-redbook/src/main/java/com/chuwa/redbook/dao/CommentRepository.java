package com.chuwa.redbook.dao;

import com.chuwa.redbook.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByPostId(Long postId);

    Optional<Comment> findByIdAndPostId(Long commentId, Long postId);

    List<Comment> findByPostIdAndBodyContainingIgnoreCase(Long postId, String keyword);

    List<Comment> findByPostIdAndEmailIgnoreCase(Long postId, String email);

    // Top / newest comments for a post
    List<Comment> findTop5ByPostIdOrderByIdDesc(Long postId);

    // Pagination for comments under a post
    Page<Comment> findByPostId(Long postId, Pageable pageable);
}
