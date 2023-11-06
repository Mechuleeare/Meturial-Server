package com.meturial.domain.review.domain.repository;

import com.meturial.domain.review.domain.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
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
}
