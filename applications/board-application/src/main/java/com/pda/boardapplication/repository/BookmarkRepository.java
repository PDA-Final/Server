package com.pda.boardapplication.repository;

import com.pda.boardapplication.entity.Bookmark;
import com.pda.boardapplication.entity.BookmarkPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkPk> {
}
