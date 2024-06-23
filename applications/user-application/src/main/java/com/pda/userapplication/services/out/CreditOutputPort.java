package com.pda.userapplication.services.out;

public interface CreditOutputPort {
    boolean consumeCredit(Long amount, String token);
}
