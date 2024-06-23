package com.pda.userapplication.services;

import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.tofinenums.user.UserRole;
import com.pda.userapplication.domains.PortfolioSubscribeLog;
import com.pda.userapplication.domains.User;
import com.pda.userapplication.domains.vo.UserId;
import com.pda.userapplication.services.in.PortfolioUseCase;
import com.pda.userapplication.services.in.dto.req.PortfolioSubscribeServiceRequest;
import com.pda.userapplication.services.out.CreditOutputPort;
import com.pda.userapplication.services.out.PortfolioSubscribeOutputPort;
import com.pda.userapplication.services.out.ReadUserOutputPort;
import com.pda.userapplication.services.out.dto.req.TransferCreditRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PortfolioService implements PortfolioUseCase {
    private final ReadUserOutputPort readUserOutputPort;
    private final PortfolioSubscribeOutputPort portfolioSubscribeOutputPort;
    private final CreditOutputPort creditOutputPort;

    @Transactional
    @Override
    public void subscribe(final PortfolioSubscribeServiceRequest request) {
        if (request.getMyId().equals(request.getToId())) throw new BadRequestException("본인 포트폴리오를 구독할 필요가 없습니다");

        User myUser = readUserOutputPort.getUserByUserId(UserId.of(request.getMyId()));
        User targetUser = readUserOutputPort.getUserByUserId(UserId.of(request.getToId()));

        if (!targetUser.getRole().equals(UserRole.FINFLUENCER))
            throw new BadRequestException("핀플루언서만 포트폴리오가 잠겨있습니다.");

        List<PortfolioSubscribeLog> logs = portfolioSubscribeOutputPort
            .findSubscribeLogsBy(myUser.getId(), targetUser.getId());

        logs.forEach(log -> {
            if (log.isSubscribed()) throw new BadRequestException("남아있는 구독이 있습니다.");
        });

        portfolioSubscribeOutputPort.subscribe(myUser.getId(), targetUser.getId());
        creditOutputPort.transferCredit(TransferCreditRequest.builder()
                .token(request.getToken())
                .amount(50L)
                .toUserId(targetUser.getId())
                .token(request.getToken())
            .build());
    }
}
