package com.pda.boardapplication.repository;

import com.pda.boardapplication.entity.Unlocked;
import com.pda.boardapplication.entity.UnlockedPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnlockedRepository extends JpaRepository<Unlocked, UnlockedPK> {

    public List<Unlocked> findAllByBoardId(long userId);
}
