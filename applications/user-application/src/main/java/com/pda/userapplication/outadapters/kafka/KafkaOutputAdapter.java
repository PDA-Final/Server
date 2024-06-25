package com.pda.userapplication.outadapters.kafka;

import com.pda.kafkautils.KafkaJson;
import com.pda.kafkautils.alert.AlertMessageDto;
import com.pda.kafkautils.credit.AddCreditDto;
import com.pda.kafkautils.user.UserUpdateDto;
import com.pda.userapplication.services.out.SendAlertMessageOutputPort;
import com.pda.userapplication.services.out.SendCreditOutputPort;
import com.pda.userapplication.services.out.SendUpdateUserOutputPort;
import com.pda.userapplication.services.out.dto.req.SendCreditOutputRequest;
import com.pda.userapplication.services.out.dto.req.SendMessageRequest;
import com.pda.userapplication.services.out.dto.req.UserUpdateOutputRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaOutputAdapter implements SendCreditOutputPort, SendUpdateUserOutputPort, SendAlertMessageOutputPort {
    private final KafkaTemplate<String, KafkaJson> kafkaTemplate;

    @Async
    @Override
    public void addCredit(final SendCreditOutputRequest request) {
        kafkaTemplate.send("add-credit", AddCreditDto.builder()
                .transactionDateTime(request.getTransactionDateTime())
                .amount(request.getAmount())
                .userId(request.getUserId())
            .build());
    }

    @Async
    @Override
    public void sendUserUpdate(final UserUpdateOutputRequest request) {
        kafkaTemplate.send("user-update", UserUpdateDto.builder()
                .userId(request.getUserId())
                .role(request.getRole())
                .nickname(request.getNickname())
                .profileImage(request.getProfileImage())
                .job(request.getJob())
            .build());
    }

    @Async
    @Override
    public void sendAlertMessage(final SendMessageRequest request) {
        kafkaTemplate.send("alert-msg", AlertMessageDto.builder()
                .clientId(request.getUserId())
                .content(request.getMessage())
                .targetId(request.getTargetId())
                .messageType(request.getMessageType())
                .thumbnail(request.getImage())
            .build());
    }
}
