package com.pda.boardapplication.service;

import com.pda.boardapplication.repository.BoardRepository;
import com.pda.boardapplication.repository.CommentRepository;
import com.pda.boardapplication.repository.UpdateAuthorRequest;
import com.pda.boardapplication.utils.UserUtils;
import com.pda.kafkautils.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserUpdateService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    @KafkaListener(topics = "user-update", concurrency = "3")
    public void userUpdate(UserUpdateDto userUpdateDto) {
        log.info("Consumer: user {} has been update", userUpdateDto.getUserId());
        boardRepository.updateAuthorByUserId(from(userUpdateDto));
        commentRepository.updateAuthorByUserId(from(userUpdateDto));
    }

    private UpdateAuthorRequest from(UserUpdateDto dto) {
        return UpdateAuthorRequest.builder()
            .id(dto.getUserId())
            .userType(UserUtils.getUserRoleCode(dto.getRole()))
            .profileImage(dto.getProfileImage())
            .nickname(dto.getNickname())
            .build();
    }
}
