package com.meturial.domain.review.domain.repository;

import com.meturial.domain.review.domain.repository.vo.QQueryReviewDetailVo;
import com.meturial.domain.review.domain.repository.vo.QueryReviewDetailVo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static com.meturial.domain.recipe.domain.QRecipe.recipe;
import static com.meturial.domain.review.domain.QReview.review;

@RequiredArgsConstructor
@Component
public class CustomReviewRepositoryImpl implements CustomReviewRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<QueryReviewDetailVo> queryReviewDetail(UUID reviewId) {
        return Optional.ofNullable(queryFactory.select(
                        new QQueryReviewDetailVo(
                                recipe.id,
                                recipe.name,
                                review.user.name,
                                review.starRating,
                                review.content,
                                review.reviewImageUrl,
                                review.createdAt
                        ))
                .from(review)
                .leftJoin(recipe)
                .on(review.recipe.id.eq(recipe.id))
                .where(review.id.eq(reviewId))
                .fetchOne());
    }
}
