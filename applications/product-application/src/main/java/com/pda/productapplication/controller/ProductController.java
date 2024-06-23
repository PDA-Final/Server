package com.pda.productapplication.controller;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.exceptionhandler.exceptions.NotFoundException;
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

@Tag(name = "[Product]", description = "Product API")
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
        log.info(searchConditionDto.getSort());

        List<ProductDto.BasicRespDto> products =
                productService.getProducts(pageNo, size, searchConditionDto);
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
            @PathVariable("productId") Long productId
    ) {
        log.debug("Product id: {}", productId);
        Map<String, Object> result = new HashMap<>();

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

        // TODO : category validation

        switch (categoryName.toLowerCase()) {
            case "카드":
                ProductDto.CardSummaryRespDto cardSummaryRespDto
                        = productService.getCardSummary(productId);
                result.put("card product summary", cardSummaryRespDto);
                break;
            case "예적금":
                ProductDto.SavingSummaryRespDto savingSummaryRespDto
                        = productService.getSavingSummary(productId);
                result.put("saving product summary", savingSummaryRespDto);
                break;
            case "펀드":
                ProductDto.FundSummaryRespDto fundSummaryRespDto
                        = productService.getFundSummary(productId);
                result.put("fund product summary", fundSummaryRespDto);
                break;
            case "대출":
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

        // TODO : category validation

        switch (categoryName.toLowerCase()) {
            case "카드":
                ProductDto.CardDetailRespDto cardDetailRespDto
                        = productService.getCardDetail(productId);
                result.put("card product detail", cardDetailRespDto);
                break;
            case "예적금":
                ProductDto.SavingDetailRespDto savingDetailRespDto
                        = productService.getSavingDetail(productId);
                result.put("saving product detail", savingDetailRespDto);
                break;
            case "펀드":
                ProductDto.FundDetailRespDto fundDetailRespDto
                        = productService.getFundDetail(productId);
                result.put("fund product detail", fundDetailRespDto);
                break;
            case "대출":
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

    @GetMapping("/search")
    @Operation(summary = "Search for products", description = "Search for products by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "204", description = "No content")
    })
    public GlobalResponse<Object> searchProductByName(
            @RequestParam("name") String name,
            @RequestParam(required = false, defaultValue = "0", value = "pageNo") int pageNo,
            @RequestParam(required = false, defaultValue = "10", value = "size") int size,
            ProductDto.SearchConditionDto searchConditionDto
    ) {
        if(searchConditionDto != null)
            log.info(searchConditionDto.getCategory());

        log.debug("Get searched product list. page {}, size {}", pageNo, size);
        Map<String, Object> result = new HashMap<>();

        assert searchConditionDto != null;
        List<ProductDto.BasicRespDto> products = productService.searchProductByName(name, pageNo, size, searchConditionDto);
        result.put("searched products", products);

        return ApiUtils.success("success", result);
    }

    @PostMapping("/owned/normal")
    @Operation(summary = "Get Products owned by normal user", description = "Return product information that normal user own.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "204", description = "No content")
    })
    public GlobalResponse<Object> getProductsOwnedByNormalUser(
            @RequestBody Map<String, String> productsReqMap,
            @RequestParam(required = false, defaultValue = "0", value = "pageNo") int pageNo,
            @RequestParam(required = false, defaultValue = "10", value = "size") int size
    ) {
        log.debug("Get products owned by normal user. productsReq: {}", productsReqMap);
        log.debug("pageNum: {}, size: {}", pageNo, size);
        Map<String, Object> result = new HashMap<>();

        List<ProductDto.BasicRespDto> products = productService.getProductsByNames(productsReqMap, pageNo, size);
        result.put("products", products);

        return ApiUtils.success("success", result);
    }

    @PostMapping("/owned/corp")
    @Operation(summary = "Get Products owned by corp user", description = "Return product information that corp user own.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "204", description = "No content")
    })
    public GlobalResponse<Object> getProductsOwnedByCorpUser(
            @RequestParam(value = "corpId") Long corpId,
            @RequestParam(required = false, defaultValue = "0", value = "pageNo") int pageNo,
            @RequestParam(required = false, defaultValue = "10", value = "size") int size
    ) {
        log.debug("Get products owned by corp user. corpId: {}", corpId);
        log.debug("pageNum: {}, size: {}", pageNo, size);
        Map<String, Object> result = new HashMap<>();

        List<ProductDto.BasicRespDto> products = productService.getProductsByCorpId(corpId, pageNo, size);
        result.put("products", products);

        return ApiUtils.success("success", result);
    }
}