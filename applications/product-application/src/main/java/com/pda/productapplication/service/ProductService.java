package com.pda.productapplication.service;

import com.pda.productapplication.entity.Product;
import com.pda.productapplication.entity.Review;
import com.pda.productapplication.repository.CardProductRepository;
import com.pda.productapplication.service.dto.response.ProductListResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardProductService {

    private CardProductRepository cardProductRepository;

//    public String findById(Long id) {
//
//    }
    public List<ProductListResponse> getCardProducts()  {
        List<Product> productList = new ArrayList<>();

        return productList.stream()
                .map((product) -> ProductListResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .reviewCount(product.getReviews().size())
                        .avgRating(getAvgRating(product.getReviews()))
                        .build())
                .toList();
    }

    private float getAvgRating(List<Review> reviews) {
        float sum = 0;
        for (Review review: reviews) {
            sum += review.getRating();
        }

        return sum / reviews.size();
    }
}
