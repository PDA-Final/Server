package com.pda.boardapplication.repository;

import com.pda.boardapplication.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<List<Comment>> findAllByBoardId(long boardId);

    @Modifying
    @Query("UPDATE Comment c  " +
        "SET c.authorNickname = :#{#user.nickname}, " +
        "c.authorProfile = :#{#user.profileImage}, " +
        "c.authorType = :#{#user.userType} " +
        "WHERE c.userId = :#{#user.id}"
    )
    void updateAuthorByUserId(@Param("user") UpdateAuthorRequest user);
}
