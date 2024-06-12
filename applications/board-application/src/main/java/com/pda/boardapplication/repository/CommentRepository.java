package com.pda.boardapplication.repository;

import com.pda.boardapplication.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
