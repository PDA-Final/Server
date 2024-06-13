package com.pda.productapplication.controller;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.productapplication.service.ProductService;
import com.pda.productapplication.service.dto.response.ProductListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Product", description = "투핀 상품 API")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class CardProductController {
    private final ProductService productService;

    @GetMapping("/all")
    @Operation(summary = "상품 리스트 조회", description = "모든 상품들을 리스트 형식으로 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<List<ProductListResponse>> getCardProducts() {
        return ApiUtils.success(
                "상품 리스트 조회", productService.getCardProducts());
    }
}