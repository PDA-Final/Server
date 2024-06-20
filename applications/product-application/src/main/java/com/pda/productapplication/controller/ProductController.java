package com.pda.productapplication.controller;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.productapplication.dto.ProductDto;
import com.pda.productapplication.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Product", description = "Product API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/products")
public class ProductController {
//    @GetMapping("/test")
//    @Operation(summary = "인증 & 인가 테스트", description = "인가 테스트를 위한 API입니다.",
//            security = @SecurityRequirement(name = "bearerAuth"))
//    public GlobalResponse test(@AuthUser AuthUserInfo userInfo) {
//        return ApiUtils.success("처리 성공", userInfo);
//    }

    private final ProductService productService;

    @GetMapping
    @Operation(summary = "Get product list", description = "Get product lists by category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "204", description = "No content")
    })
    public GlobalResponse<Object> getProducts(
            @RequestParam(required = false, defaultValue = "0", value = "pageNo") int pageNo,
            @RequestParam(required = false, defaultValue = "10", value = "size") int size,
            ProductDto.SearchConditionDto searchConditionDto
    ) {
        log.debug("Get product lists page {}, size {}", pageNo, size);
        Map<String, Object> result = new HashMap<>();

        log.info(searchConditionDto.getCategory());

        List<ProductDto.BasicRespDto> products = productService.getProducts(pageNo, size);
        result.put("products", products);

        return ApiUtils.success("success", result);
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get product item basic", description = "Get product item basic info.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public GlobalResponse<Object> getProductBasic(
            @PathVariable("productId") Long productId,
            ProductDto.SearchConditionDto searchConditionDto
    ) {
        log.debug("Product id: {}", productId);
        Map<String, Object> result = new HashMap<>();

        log.info(searchConditionDto.getCategory());

        ProductDto.BasicRespDto basicRespDto
                = productService.getProduct(productId);
        result.put("product basic", basicRespDto);

        return ApiUtils.success("success", result);
    }

    @GetMapping("/{productId}/summary")
    @Operation(summary = "Get product item summary", description = "Get product item summary info.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public GlobalResponse<Object> getProductSummary(
            @PathVariable("productId") Long productId,
            ProductDto.SearchConditionDto searchConditionDto
    ) {
        log.debug("Product id: {}", productId);
        Map<String, Object> result = new HashMap<>();

        log.info(searchConditionDto.getCategory());
        String categoryName = searchConditionDto.getCategory();

        switch (categoryName.toLowerCase()) {
            case "card":
                ProductDto.CardSummaryRespDto cardSummaryRespDto
                        = productService.getCardSummary(productId);
                result.put("card product summary", cardSummaryRespDto);
                break;
            case "saving":
                ProductDto.SavingSummaryRespDto savingSummaryRespDto
                        = productService.getSavingSummary(productId);
                result.put("saving product summary", savingSummaryRespDto);
                break;
            case "fund":
                ProductDto.FundSummaryRespDto fundSummaryRespDto
                        = productService.getFundSummary(productId);
                result.put("fund product summary", fundSummaryRespDto);
                break;
            case "loan":
                ProductDto.LoanSummaryRespDto loanSummaryRespDto
                        = productService.getLoanSummary(productId);
                result.put("loan product summary", loanSummaryRespDto);
                break;
            default:
                throw new IllegalArgumentException("Invalid category name: " + categoryName);
        }

        return ApiUtils.success("success", result);
    }

    @GetMapping("/{productId}/details")
    @Operation(summary = "Get product item detail", description = "Get product item detail info.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public GlobalResponse<Object> getProductDetail(
            @PathVariable("productId") Long productId,
            ProductDto.SearchConditionDto searchConditionDto
    ) {
        log.debug("Product id: {}", productId);
        Map<String, Object> result = new HashMap<>();

        log.info(searchConditionDto.getCategory());
        String categoryName = searchConditionDto.getCategory();

        switch (categoryName.toLowerCase()) {
            case "card":
                ProductDto.CardDetailRespDto cardDetailRespDto
                        = productService.getCardDetail(productId);
                result.put("card product detail", cardDetailRespDto);
                break;
            case "saving":
                ProductDto.SavingDetailRespDto savingDetailRespDto
                        = productService.getSavingDetail(productId);
                result.put("saving product detail", savingDetailRespDto);
                break;
            case "fund":
                ProductDto.FundDetailRespDto fundDetailRespDto
                        = productService.getFundDetail(productId);
                result.put("fund product detail", fundDetailRespDto);
                break;
            case "loan":
                ProductDto.LoanDetailRespDto loanDetailRespDto
                        = productService.getLoanDetail(productId);
                result.put("loan product detail", loanDetailRespDto);
                break;
            default:
                throw new IllegalArgumentException("Invalid category name: " + categoryName);
        }

        return ApiUtils.success("success", result);
    }

    @GetMapping("/random")
    @Operation(summary = "Get random products", description = "Get random products to the homepage.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "204", description = "No content")
    })
    public GlobalResponse<Object> getRandomProducts(
            @RequestParam(value = "limit", required = true) int limit
    ) {
        log.debug("Get random products. limit: {}", limit);
        Map<String, Object> result = new HashMap<>();

        List<ProductDto.BasicRespDto> randomProducts = productService.getRandomProducts(limit);
        result.put("randomProducts", randomProducts);

        return ApiUtils.success("success", result);
    }

    @PostMapping("/{productId}/boards")
    @Operation(summary = "Increase board count", description = "Count the board associated with the product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public GlobalResponse<Object> notifyBoardCreation(@PathVariable("productId") Long productId) {
        log.debug("Product id: {}", productId);
        Map<String, Object> result = new HashMap<>();

        ProductDto.BoardCountReqDto boardCountReqDto
                = productService.incrementBoardCount(productId);
        result.put("board count", boardCountReqDto);

        return ApiUtils.success("success", result);
    }
}