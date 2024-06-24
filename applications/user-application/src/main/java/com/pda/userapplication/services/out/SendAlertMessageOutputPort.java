package com.pda.userapplication.services.out;

import com.pda.userapplication.services.out.dto.req.SendMessageRequest;

public interface SendAlertMessageOutputPort {
    void sendAlertMessage(SendMessageRequest request);
}
