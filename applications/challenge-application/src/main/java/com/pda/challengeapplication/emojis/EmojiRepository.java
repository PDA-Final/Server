package com.pda.challengeapplication.emojis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmojiRepository extends JpaRepository<Emoji, Integer> {
    Emoji findFirstByOrderByIdDesc();
    Emoji save(Emoji emoji);
    List<Emoji> findAll();
    Optional<Emoji> findById(long id);
}
