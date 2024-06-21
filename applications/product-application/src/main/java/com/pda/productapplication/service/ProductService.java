package com.pda.productapplication.service;

import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.exceptionhandler.exceptions.NotFoundException;
import com.pda.productapplication.dto.ProductDto;
import com.pda.productapplication.entity.*;
import com.pda.productapplication.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CardRepository cardRepository;
    private final SavingRepository savingRepository;
    private final FundRepository fundRepository;
    private final LoanRepository loanRepository;
    private final BoardCountRepository boardCountRepository;
    private final CategoryRepository categoryRepository;
    private final CorpRepository corpRepository;

    /**
     * Get product list by page
     * @param pageNo page num
     * @param size page size
     * @param searchConditionDto search conditions
     */
    public List<ProductDto.BasicRespDto> getProducts(int pageNo, int size, ProductDto.SearchConditionDto searchConditionDto) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<Product> products;

        String categoryName = searchConditionDto.getCategory(); // "카드"
        String sortType = searchConditionDto.getSort(); // "인기순"

        if (categoryName == null || categoryName.isEmpty()) {
            // 카테고리 X : 모든 상품 조회
            products = productRepository.findAll(pageable);
        } else {
            // 카테고리 O : 해당 카테고리의 상품 조회
            Optional<Category> categoryOptional = categoryRepository.findByName(categoryName);
            if (categoryOptional.isPresent()) {
                Category category = categoryOptional.get();
                Long categoryId = category.getId();

                if ("최신순".equals(sortType)) {
                    products = productRepository.findByCategoryIdOrderByCreatedAt(categoryId, pageable);
                } else if ("인기순".equals(sortType)) {
                    products = productRepository.findByCategoryIdOrderByBoardCount(categoryId, pageable);
                    log.debug(products.toString());
                } else {
                    products = productRepository.findByCategoryId(categoryId, pageable);
                }
            } else {
                return List.of();
            }
        }

        return products.stream().map(product ->
                ProductDto.BasicRespDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .corpName(product.getCorp().getName())
                        .corpImage(product.getCorp().getLogoImg())
                        .cardImage(product.getCardImg())
                        .tags(convertToList(product.getTags()))
                        .boardCount(product.getBoardCount().getBoardCount())
                        .createdTime(product.getCreatedAt())
                        .build()
        ).collect(Collectors.toList());
    }

    /**
     * Get product
     * @param productId product id
     */
    public ProductDto.BasicRespDto getProduct(Long productId) {
        log.debug("Get product: {}", productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + productId));

        return ProductDto.BasicRespDto.builder()
                .id(product.getId())
                .name(product.getName())
                .corpName(product.getCorp().getName())
                .corpImage(product.getCorp().getLogoImg())
                .cardImage(product.getCardImg())
                .tags(convertToList(product.getTags()))
                .boardCount(product.getBoardCount().getBoardCount())
                .createdTime(product.getCreatedAt())
                .build();
    }

    /**
     * Get card product summary
     * @param productId product id
     */
    public ProductDto.CardSummaryRespDto getCardSummary(Long productId) {
        log.debug("Get summary of card: {}", productId);

        Card card = cardRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + productId));

        return ProductDto.CardSummaryRespDto.builder()
                .productId(card.getProductId())
                .notice(card.getNotice())
                .annualFee(card.getAnnualFee())
                .rewards(card.getRewards())
                .baseRecord(card.getBaseRecord())
                .mainBenefit(card.getMainBenefit())
                .subBenefit(card.getSubBenefit())
                .build();
    }

    /**
     * Get card product detail
     * @param productId product id
     */
    public ProductDto.CardDetailRespDto getCardDetail(Long productId) {
        log.debug("Get detail of card: {}", productId);

        Card card = cardRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + productId));

        return ProductDto.CardDetailRespDto.builder()
                .productId(card.getProductId())
                .description(card.getDescription())
                .terms(convertToList(card.getTerms()))
                .build();
    }

    /**
     * Get saving product summary
     * @param productId product id
     */
    public ProductDto.SavingSummaryRespDto getSavingSummary(Long productId) {
        log.debug("Get summary of saving: {}", productId);

        Saving saving = savingRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + productId));

        return ProductDto.SavingSummaryRespDto.builder()
                .productId(saving.getProductId())
                .interestRate(saving.getInterestRate())
                .primeInterestRate(saving.getPrimeInterestRate())
                .savingTerm(saving.getSavingTerm())
                .specialOfferSummary(saving.getSpecialOfferSummary())
                .specialOfferPeriod(saving.getSpecialOfferPeriod())
                .build();
    }

    /**
     * Get saving product detail
     * @param productId product id
     */
    public ProductDto.SavingDetailRespDto getSavingDetail(Long productId) {
        log.debug("Get detail of saving: {}", productId);

        Saving saving = savingRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + productId));

        return ProductDto.SavingDetailRespDto.builder()
                .productId(saving.getProductId())
                .joinPeriod(saving.getJoinPeriod())
                .joinAmount(saving.getJoinAmount())
                .joinTarget(saving.getJoinTarget())
                .specialConditions(saving.getSpecialConditions())
                .channel(saving.getChannel())
                .interestPaymentCycle(saving.getInterestPaymentCycle())
                .note(saving.getNote())
                .depositorProtection(saving.getDepositorProtection())
                .build();
    }

    /**
     * Get fund product summary
     * @param productId product id
     */
    public ProductDto.FundSummaryRespDto getFundSummary(Long productId) {
        log.debug("Get summary of fund: {}", productId);

        Fund fund = fundRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + productId));

        return ProductDto.FundSummaryRespDto.builder()
                .productId(fund.getProductId())
                .fundCode(fund.getFundCode())
                .stdPrice(fund.getStdPrice())
                .diffPrice(fund.getDiffPrice())
                .drvNav(fund.getDrvNav())
                .rt3m(fund.getRt3m())
                .ter(fund.getTer())
                .build();
    }

    /**
     * Get fund product detail
     * @param productId product id
     */
    public ProductDto.FundDetailRespDto getFundDetail(Long productId) {
        log.debug("Get detail of fund: {}", productId);

        Fund fund = fundRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + productId));

        return ProductDto.FundDetailRespDto.builder()
                .productId(fund.getProductId())
                .rt1m(fund.getRt1m())
                .rt3m(fund.getRt3m())
                .rt6m(fund.getRt6m())
                .rtYtd(fund.getRtYtd())
                .rt1y(fund.getRt1y())
                .rt3y(fund.getRt3y())
                .rt5y(fund.getRt5y())
                .riskGrade(fund.getRiskGrade())
                .riskGradeText(fund.getRiskGradeText())
                .feeGb(fund.getFeeGb())
                .category1(fund.getCategory1())
                .category2(fund.getCategory2())
                .infoObject(fund.getInfoObject())
                .infoStrategy(fund.getInfoStrategy())
                .region(fund.getRegion())
                .amtGb(fund.getAmtGb())
                .exceBm(fund.getExceBm())
                .riskGb(fund.getRiskGb())
                .rtGb(fund.getRtGb())
                .build();
    }

    /**
     * Get loan product summary
     * @param productId product id
     */
    public ProductDto.LoanSummaryRespDto getLoanSummary(Long productId) {
        log.debug("Get summary of loan: {}", productId);

        Loan loan = loanRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + productId));

        return ProductDto.LoanSummaryRespDto.builder()
                .productId(loan.getProductId())
                .minInterestRate(loan.getMinInterestRate())
                .maxInterestRate(loan.getMaxInterestRate())
                .maxLoanAmount(loan.getMaxLoanAmount())
                .build();
    }

    /**
     * Get loan product detail
     * @param productId product id
     */
    public ProductDto.LoanDetailRespDto getLoanDetail(Long productId) {
        log.debug("Get detail of loan: {}", productId);

        Loan loan = loanRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + productId));

        return ProductDto.LoanDetailRespDto.builder()
                .productId(loan.getProductId())
                .description(loan.getDescription())
                .build();
    }

    /**
     * Get random products
     * @param limit limit
     */
    public List<ProductDto.BasicRespDto> getRandomProducts(int limit) {
        List<Product> products =
                productRepository.findRandomProducts(limit);

        return products.stream().map((product) ->
                ProductDto.BasicRespDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .corpName(product.getCorp().getName())
                        .corpImage(product.getCorp().getLogoImg())
                        .cardImage(product.getCardImg())
                        .tags(convertToList(product.getTags()))
                        .boardCount(product.getBoardCount().getBoardCount())
                        .createdTime(product.getCreatedAt())
                        .build()
        ).collect(Collectors.toList());
    }

    /**
     * Count board associated with the product and Store
     * @param productId product id
     */
    public ProductDto.BoardCountReqDto incrementBoardCount (Long productId) {
        log.debug("Count board associated with the product. productId: {}", productId);

        BoardCount boardCount = boardCountRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("BoardCount not found with product id: " + productId));

        boardCount.incrementBoardCount();
        boardCountRepository.save(boardCount);

        return ProductDto.BoardCountReqDto.builder()
                .productId(boardCount.getProductId())
                .boardCount(boardCount.getBoardCount())
                .build();
    }

    /**
     * Search for products by name
     * @param name product name
     * @param pageNo page number
     * @param size size
     * @return searched products
     */
    public List<ProductDto.BasicRespDto> searchProductByName(String name, int pageNo, int size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<Product> products;

        products = productRepository.findByNameLike("%"+name+"%", pageable);

        return products.stream().map(product ->
                ProductDto.BasicRespDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .corpName(product.getCorp().getName())
                        .corpImage(product.getCorp().getLogoImg())
                        .cardImage(product.getCardImg())
                        .tags(convertToList(product.getTags()))
                        .boardCount(product.getBoardCount().getBoardCount())
                        .createdTime(product.getCreatedAt())
                        .build()
        ).collect(Collectors.toList());
    }

    /**
     * Get products by names : normal-user-owned products
     * @param productsReqMap requested products (product name, corp name)
     * @param pageNo page num
     * @param size size
     */
    public List<ProductDto.BasicRespDto> getProductsByNames(Map<String, String> productsReqMap, int pageNo, int size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        List<ProductDto.BasicRespDto> productDtoList = new ArrayList<>();

        productsReqMap.forEach((name, corpName) -> {
            Specification<Product> spec = Specification.where(ProductSpecification.equalProductName(name))
                    .and(ProductSpecification.equalCorpName(corpName));
            List<Product> products = productRepository.findAll(spec, pageable).getContent(); // 필터링된 상품

            log.debug("Filtered product: {}", products);

            productDtoList.addAll(products.stream().map(product -> ProductDto.BasicRespDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .corpName(product.getCorp().getName())
                    .corpImage(product.getCorp().getLogoImg())
                    .cardImage(product.getCardImg())
                    .tags(convertToList(product.getTags()))
                    .boardCount(product.getBoardCount().getBoardCount())
                    .createdTime(product.getCreatedAt())
                    .build()).toList());
        });
        return productDtoList;
    }


    /**
     * Get products by corpId : corp-user-owned products
     * @param corpId corporation id
     * @param pageNo page num
     * @param size size
     */
    public List<ProductDto.BasicRespDto> getProductsByCorpId(Long corpId, int pageNo, int size) {
        Pageable pageable = PageRequest.of(pageNo, size);

        Corp corp = corpRepository.findById(corpId)
                .orElseThrow(() -> new NotFoundException("Not found corpId: " + corpId));

        Page<Product> products = productRepository.findByCorpId(corpId, pageable);

        if (products.isEmpty())
            throw new NotFoundException("The products found with corpId does not exist: " + corpId);

        return products.stream().map(product ->
                ProductDto.BasicRespDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .corpName(product.getCorp().getName())
                        .corpImage(product.getCorp().getLogoImg())
                        .cardImage(product.getCardImg())
                        .tags(convertToList(product.getTags()))
                        .boardCount(product.getBoardCount().getBoardCount())
                        .createdTime(product.getCreatedAt())
                        .build()
        ).collect(Collectors.toList());
    }


    public List<String> convertToList(String data) {
        if (data == null || data.isEmpty()) {
            return Collections.emptyList();
        }
        // 양쪽 괄호 제거, 공백 제거, 리스트 변환
        return Arrays.stream(data.replace("[", "").replace("]", "").replace("'", "").split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}