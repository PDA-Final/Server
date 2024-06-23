package com.pda.userapplication.services;

import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.userapplication.domains.NormalUser;
import com.pda.userapplication.domains.vo.UserId;
import com.pda.userapplication.services.in.GetUserAssetsUseCase;
import com.pda.userapplication.services.out.GetAssetsOutputPort;
import com.pda.userapplication.services.out.ReadNormalUserOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssetService implements GetUserAssetsUseCase {
    private final ReadNormalUserOutputPort readNormalUserOutputPort;
    private final GetAssetsOutputPort getAssetsOutputPort;

    @Override
    public void getProductsBy(Long userId) {
        NormalUser user = readNormalUserOutputPort.findNormalUserByUserId(UserId.of(userId))
            .orElseThrow(() -> new BadRequestException("자산이 연결되어 있지 않습니다."));


    }
}
