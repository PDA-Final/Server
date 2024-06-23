package com.pda.userapplication.services.out;

import com.pda.userapplication.domains.PortfolioSubscribeLog;
import com.pda.userapplication.domains.vo.UserId;

import java.util.List;

public interface PortfolioSubscribeOutputPort {
    PortfolioSubscribeLog subscribe(UserId fromUserId, UserId toUserId);
    List<PortfolioSubscribeLog> findSubscribeLogsBy(UserId fromUserId, UserId toUserId);
}
