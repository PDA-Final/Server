package com.pda.userapplication.services;

import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.userapplication.domains.UserDetail;
import com.pda.userapplication.domains.vo.UserId;
import com.pda.userapplication.services.in.GetUserAssetsUseCase;
import com.pda.userapplication.services.in.dto.res.AssetInfoServiceResponse;
import com.pda.userapplication.services.out.GetAssetsOutputPort;
import com.pda.userapplication.services.out.ReadUserDetailOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetService implements GetUserAssetsUseCase {
    private final ReadUserDetailOutputPort readUserDetailOutputPort;
    private final GetAssetsOutputPort getAssetsOutputPort;

    @Override
    public List<AssetInfoServiceResponse> getAccounts(Long userId) {
        UserDetail user = readUserDetailOutputPort.findUserDetailById(UserId.of(userId))
            .orElseThrow(() -> new BadRequestException("자산이 연결되어 있지 않습니다."));

        return getAssetsOutputPort.getAssets(user)
            .getAccounts().stream()
            .map(account -> AssetInfoServiceResponse.builder()
                .image(account.getLogo())
                .cash(account.getCash())
                .name(account.getName())
                .productType(account.getAccountType())
                .number(account.getAccountNumber())
                .build())
            .toList();
    }
}
