package com.pda.userapplication.services;

import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.tofinenums.user.UserRole;
import com.pda.userapplication.domains.UserDetail;
import com.pda.userapplication.domains.PortfolioSubscribeLog;
import com.pda.userapplication.domains.User;
import com.pda.userapplication.domains.vo.UserId;
import com.pda.userapplication.services.in.PortfolioUseCase;
import com.pda.userapplication.services.in.dto.req.PortfolioSubscribeServiceRequest;
import com.pda.userapplication.services.in.dto.res.AbstractPortfolio;
import com.pda.userapplication.services.in.dto.res.DetailPortfolio;
import com.pda.userapplication.services.in.dto.res.GetPortfolioServiceResponse;
import com.pda.userapplication.services.in.dto.res.StockResponse;
import com.pda.userapplication.services.out.CreditOutputPort;
import com.pda.userapplication.services.out.GetAssetsOutputPort;
import com.pda.userapplication.services.out.PortfolioSubscribeOutputPort;
import com.pda.userapplication.services.out.ReadUserDetailOutputPort;
import com.pda.userapplication.services.out.ReadUserOutputPort;
import com.pda.userapplication.services.out.SendAlertMessageOutputPort;
import com.pda.userapplication.services.out.dto.req.SendMessageRequest;
import com.pda.userapplication.services.out.dto.req.TransferCreditRequest;
import com.pda.userapplication.services.out.dto.res.AccountResponse;
import com.pda.userapplication.services.out.dto.res.AssetInfoResponse;
import com.pda.userapplication.services.out.dto.res.PortfolioResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PortfolioService implements PortfolioUseCase {
    private final ReadUserOutputPort readUserOutputPort;
    private final ReadUserDetailOutputPort readUserDetailOutputPort;
    private final PortfolioSubscribeOutputPort portfolioSubscribeOutputPort;
    private final CreditOutputPort creditOutputPort;
    private final GetAssetsOutputPort getAssetsOutputPort;
    private final SendAlertMessageOutputPort sendAlertMessageOutputPort;

    @Override
    public GetPortfolioServiceResponse getPortfolios(Long myId, Long toUserId) {
        User myUser = readUserOutputPort.getUserByUserId(UserId.of(myId));
        UserDetail targetUser = readUserDetailOutputPort.findUserDetailById(UserId.of(toUserId))
            .orElseThrow(() -> new BadRequestException("자산연결이 안되어있습니다."));

        AssetInfoResponse assets = getAssetsOutputPort.getPortfolio(targetUser);
        if (targetUser.getId().equals(myUser.getId())) {
            log.debug("Get Portfolio: Myself");
            return GetPortfolioServiceResponse.of(getDetailPortfolio(assets));
        }

        if (targetUser.getRole().equals(UserRole.FINFLUENCER)){
            log.debug("Get Portfolio: Finfluencer");
            if (!isSubscribed(myUser.getId(), targetUser.getId()))
                throw new BadRequestException("구독 중이 아닙니다");

            return GetPortfolioServiceResponse.of(
                getAbstractPortfolio(getDetailPortfolio(assets)));
        }

        log.debug("Get Portfolio: Normal");
        return GetPortfolioServiceResponse.of(
            getAbstractPortfolio(getDetailPortfolio(assets)));
    }

    @Transactional
    @Override
    public void subscribe(final PortfolioSubscribeServiceRequest request) {
        if (request.getMyId().equals(request.getToId())) throw new BadRequestException("본인 포트폴리오를 구독할 필요가 없습니다");

        User myUser = readUserOutputPort.getUserByUserId(UserId.of(request.getMyId()));
        User targetUser = readUserOutputPort.getUserByUserId(UserId.of(request.getToId()));

        if (!targetUser.getRole().equals(UserRole.FINFLUENCER))
            throw new BadRequestException("핀플루언서만 포트폴리오가 잠겨있습니다.");

        if (isSubscribed(myUser.getId(), targetUser.getId()))
            throw new BadRequestException("이미 구독중입니다.");

        portfolioSubscribeOutputPort.subscribe(myUser.getId(), targetUser.getId());
        creditOutputPort.transferCredit(TransferCreditRequest.builder()
                .token(request.getToken())
                .amount(50L)
                .toUserId(targetUser.getId())
                .token(request.getToken())
            .build());

        sendAlertMessageOutputPort.sendAlertMessage(SendMessageRequest.builder()
            .message(String.format("%s님이 회원님의 포트폴리오를 구독하였습니다.", myUser.getNickname().toString()))
            .image(myUser.getProfileImage().toString())
            .messageType("CREDIT")
            .userId(targetUser.getId().toLong())
            .build());
    }

    private boolean isSubscribed(UserId myUserId, UserId toUserId) {
        List<PortfolioSubscribeLog> logs = portfolioSubscribeOutputPort
            .findSubscribeLogsBy(myUserId,toUserId);

        for (PortfolioSubscribeLog log : logs)
            if (log.isSubscribed()) return true;

        return false;
    }

    private DetailPortfolio getDetailPortfolio(AssetInfoResponse assets) {
        long totalAmount = 0;
        long savingAmount = 0;
        long depositAmount = 0;
        long cmaAmount = 0;
        long investAmount = 0;

        long foreignStockAmount = 0;
        long domesticStockAmount = 0;

        for (AccountResponse account : assets.getAccounts()) {
            totalAmount += account.getCash();
            if (account.getAccountType().equals("SAVING")) {
                savingAmount += account.getCash();
            } else if (account.getAccountType().equals("DEPOSIT")) {
                depositAmount += account.getCash();
            } else {
                cmaAmount += account.getCash();
            }
        }

        for (PortfolioResponse portfolio: assets.getPortfolio()) {
            investAmount += (long) portfolio.getPrice() *portfolio.getQuantity();

            if (portfolio.getStockType().equals("F")) {
                foreignStockAmount += (long) portfolio.getPrice()*portfolio.getQuantity();
            } else {
                domesticStockAmount += (long) portfolio.getPrice()*portfolio.getQuantity();
            }
        }

        totalAmount += investAmount;
        List<StockResponse> foreignStocks = new ArrayList<>();
        List<StockResponse> domesticStocks = new ArrayList<>();
        for (PortfolioResponse portfolio: assets.getPortfolio()) {
            if (portfolio.getStockType().equals("F")) {
                foreignStocks.add(StockResponse.builder()
                        .code(portfolio.getCode())
                        .name(portfolio.getName())
                        .rate(getRate(portfolio.getQuantity()*portfolio.getPrice(), foreignStockAmount))
                    .build());
            } else {
                domesticStocks.add(StockResponse.builder()
                    .code(portfolio.getCode())
                    .name(portfolio.getName())
                        .dartCode(portfolio.getDartCode())
                    .rate(getRate(portfolio.getQuantity()*portfolio.getPrice(), domesticStockAmount))
                    .build());
            }
        }

        return DetailPortfolio.builder()
            .totalAmount(totalAmount)
            .savingRate(getRate(savingAmount, totalAmount))
            .savingAmount(savingAmount)
            .depositRate(getRate(depositAmount, totalAmount))
            .depositAmount(depositAmount)
            .cmaRate(getRate(cmaAmount, totalAmount))
            .cmaAmount(cmaAmount)
            .investRate(getRate(investAmount, totalAmount))
            .investAmount(investAmount)
            .returnRate(assets.getPortfolioReturnRate())
            .foreignRatio(getRate(foreignStockAmount, investAmount))
            .foreignStocks(foreignStocks)
            .domesticRatio(getRate(domesticStockAmount, investAmount))
            .domesticStocks(domesticStocks)
            .build();
    }

    private AbstractPortfolio getAbstractPortfolio(DetailPortfolio detailPortfolio) {
        return AbstractPortfolio.builder()
            .totalAmount(getAmountRange(detailPortfolio.getTotalAmount()))
            .returnRate(detailPortfolio.getReturnRate())
            .investRate(detailPortfolio.getInvestRate())
            .depositRate(detailPortfolio.getDepositRate())
            .savingRate(detailPortfolio.getSavingRate())
            .cmaRate(detailPortfolio.getCmaRate())
            .foreignRatio(detailPortfolio.getForeignRatio())
            .foreignStocks(detailPortfolio.getForeignStocks())
            .domesticRatio(detailPortfolio.getDomesticRatio())
            .domesticStocks(detailPortfolio.getDomesticStocks())
            .build();
    }

    private long getAmountRange(long amount) {
        if (amount < 1000)
            return -1;

        return amount / 1000 * 1000;
    }

    private double getRate(long part, long total) {
        return (double) part / (double) total * 100;
    }
}
