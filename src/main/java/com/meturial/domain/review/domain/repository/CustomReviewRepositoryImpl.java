package com.meturial.domain.review.domain.repository;

import com.meturial.domain.review.domain.Review;
import com.meturial.domain.review.domain.repository.vo.QQueryReviewDetailVo;
import com.meturial.domain.review.domain.repository.vo.QueryReviewDetailVo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.meturial.domain.recipe.domain.QRecipe.recipe;
import static com.meturial.domain.review.domain.QReview.review;

@RequiredArgsConstructor
@Component
public class CustomReviewRepositoryImpl implements CustomReviewRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> queryReviewListByRecipeId(UUID recipeId) {
        return queryFactory
                .selectFrom(review)
                .innerJoin(recipe)
                .on(review.recipe.id.eq(recipe.id))
                .where(recipe.id.eq(recipeId))
                .orderBy(review.createdAt.desc())
                .fetch();
    }

    @Override
    public List<Review> queryMyReviewList(UUID userId) {
        return queryFactory
                .selectFrom(review)
                .innerJoin(recipe)
                .on(review.recipe.id.eq(recipe.id))
                .where(review.user.id.eq(userId))
                .orderBy(review.createdAt.desc())
                .fetch();
    }

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
                .innerJoin(recipe)
                .on(review.recipe.id.eq(recipe.id))
                .where(review.id.eq(reviewId))
                .fetchOne());
    }

    @Override
    public List<Float> queryStarRatingListByRecipeId(UUID recipeId) {
        return queryFactory
                .select(review.starRating)
                .from(review)
                .innerJoin(recipe)
                .on(review.recipe.id.eq(recipe.id))
                .where(recipe.id.eq(recipeId))
                .fetch();
    }

    @Override
    public Float querySumStarRatingByRecipeId(UUID recipeId) {
        return queryFactory
                .select(review.starRating.sum().floatValue())
                .from(review)
                .innerJoin(recipe)
                .on(review.recipe.id.eq(recipe.id))
                .where(recipe.id.eq(recipeId))
                .fetchOne();
    }
}
