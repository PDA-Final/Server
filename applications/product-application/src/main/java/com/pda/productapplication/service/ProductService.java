package com.pda.productapplication.service;

import com.pda.exceptionhandler.exceptions.NotFoundException;
import com.pda.productapplication.dto.ProductDto;
import com.pda.productapplication.entity.*;
import com.pda.productapplication.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
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
    private final CategoryRepository categoryRepository; // TODO

    /**
     * Get product list by page
     * @param pageNo page num
     * @param size page size
     */
    public List<ProductDto.BasicRespDto> getProducts(int pageNo, int size) {
        List<Product> products =
                productRepository.findAll(PageRequest.of(pageNo, size)).getContent();

        return products.stream().map((product) ->
                ProductDto.BasicRespDto.builder()
                        .name(product.getName())
                        .corpName(product.getCorp().getName())
                        .corpImage(product.getCorp().getLogoImg())
                        .cardImage(product.getCardImg())
                        .usefulCnt(product.getUsefulCnt())
                        .tags(splitTags(product.getTags()))
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

        Product product = productRepository.findById(productId).orElseThrow(NotFoundException::new);

        return ProductDto.BasicRespDto.builder()
                .name(product.getName())
                .corpName(product.getCorp().getName())
                .corpImage(product.getCorp().getLogoImg())
                .cardImage(product.getCardImg())
                .usefulCnt(product.getUsefulCnt())
                .tags(splitTags(product.getTags()))
                .createdTime(product.getCreatedAt())
                .build();
    }

    /**
     * Get card product summary
     * @param productId product id
     */
    public ProductDto.CardSummaryRespDto getCardSummary(Long productId) {
        log.debug("Get summary of card: {}", productId);

        Card card = cardRepository.findById(productId).orElseThrow(NotFoundException::new);

        return ProductDto.CardSummaryRespDto.builder()
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

        Card card = cardRepository.findById(productId).orElseThrow(NotFoundException::new);

        return ProductDto.CardDetailRespDto.builder()
                .description(card.getDescription())
                .terms(card.getTerms())
                .build();
    }

    /**
     * Get saving product summary
     * @param productId product id
     */
    public ProductDto.SavingSummaryRespDto getSavingSummary(Long productId) {
        log.debug("Get summary of saving: {}", productId);

        Saving saving = savingRepository.findById(productId).orElseThrow(NotFoundException::new);

        return ProductDto.SavingSummaryRespDto.builder()
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

        Saving saving = savingRepository.findById(productId).orElseThrow(NotFoundException::new);

        return ProductDto.SavingDetailRespDto.builder()
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

        Fund fund = fundRepository.findById(productId).orElseThrow(NotFoundException::new);

        return ProductDto.FundSummaryRespDto.builder()
                .fundCode(fund.getFundCode())
                .stdPrice(fund.getStdPrice())
                .diffPrice(fund.getDiffPrice())
                .drvNav(fund.getDrvNav())
                .setDate(fund.getSetDate())
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

        Fund fund = fundRepository.findById(productId).orElseThrow(NotFoundException::new);

        return ProductDto.FundDetailRespDto.builder()
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
                .refBm(fund.getRefBm())
                .region(fund.getRegion())
                .amtGb(fund.getAmtGb())
                .exceBm(fund.getExceBm())
                .riskGb(fund.getRiskGb())
                .rtGb(fund.getRtGb())
                .smallScaleYn(fund.getSmallScaleYn())
                .build();
    }

    /**
     * Get loan product summary
     * @param productId product id
     */
    public ProductDto.LoanSummaryRespDto getLoanSummary(Long productId) {
        log.debug("Get summary of loan: {}", productId);

        Loan loan = loanRepository.findById(productId).orElseThrow(NotFoundException::new);

        return ProductDto.LoanSummaryRespDto.builder()
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

        Loan loan = loanRepository.findById(productId).orElseThrow(NotFoundException::new);

        return ProductDto.LoanDetailRespDto.builder()
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
                        .name(product.getName())
                        .corpName(product.getCorp().getName())
                        .corpImage(product.getCorp().getLogoImg())
                        .cardImage(product.getCardImg())
                        .usefulCnt(product.getUsefulCnt())
                        .tags(splitTags(product.getTags()))
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

        BoardCount boardCount =
                boardCountRepository.findById(productId).orElseThrow(NotFoundException::new);

        boardCount.incrementBoardCount();
        boardCountRepository.save(boardCount);

        return ProductDto.BoardCountReqDto.builder()
                .boardCount(boardCount.getBoardCount())
                .build();
    }

    private List<String> splitTags(String tags) {
        return Arrays.stream(tags.split("[,#]"))
                .map(String::trim)
                .filter(tag -> !tag.isEmpty())
                .collect(Collectors.toList());
    }
}