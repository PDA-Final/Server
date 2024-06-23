package com.pda.userapplication.services.in;

import com.pda.userapplication.services.in.dto.res.AvailableContactServiceResponse;

public interface IsAvailableContact {
    AvailableContactServiceResponse isAvailableContact(String contact);
}
