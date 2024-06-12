package com.pda.challengeapplication.emojis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmojiService {
    private final EmojiRepository emojiRepository;
    public Emoji createEmoji(EmojiRequestDTO emojiRequestDTO) {
        Emoji savedEmoji = emojiRepository.save(emojiRequestDTO.converToEmojiEntity());
        return emojiRepository.findFirstByOrderByIdDesc();
    }


    public List<Emoji> findAllEmoji() {
        return emojiRepository.findAll();
    }

    public Emoji findEmojiById(Integer id) {
        return emojiRepository.findById(id).get();
    }
}
