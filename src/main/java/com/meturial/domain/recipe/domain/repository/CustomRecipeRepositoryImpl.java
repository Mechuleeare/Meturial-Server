package com.meturial.domain.recipe.domain.repository;

import com.meturial.domain.recipe.domain.Category;
import com.meturial.domain.recipe.domain.repository.vo.QQueryRecipeDetailVo;
import com.meturial.domain.recipe.domain.repository.vo.QQueryRecipeReviewVo;
import com.meturial.domain.recipe.domain.repository.vo.QueryRecipeDetailVo;
import com.meturial.domain.recipe.domain.repository.vo.QueryRecipeReviewVo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.meturial.domain.recipe.domain.QCategory.category;
import static com.meturial.domain.recipe.domain.QRecipe.recipe;
import static com.meturial.domain.review.domain.QReview.review;

@RequiredArgsConstructor
@Component
public class CustomRecipeRepositoryImpl implements CustomRecipeRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Category> queryCategory() {
        return queryFactory
                .selectFrom(category)
                .fetch();
    }

    @Override
    public Optional<QueryRecipeDetailVo> queryRecipeDetailByRecipeId(UUID recipeId) {
        return Optional.ofNullable(queryFactory.select(
                        new QQueryRecipeDetailVo(
                                recipe.id,
                                recipe.name,
                                recipe.foodImageUrl,
                                recipe.category,
                                recipe.material
                        ))
                .from(recipe)
                .leftJoin(review)
                .on(recipe.id.eq(review.recipe.id))
                .where(recipe.id.eq(recipeId))
                .fetchOne());
    }

    @Override
    public List<QueryRecipeReviewVo> queryRecipeReviewList() {
        return queryFactory
                .select(
                        new QQueryRecipeReviewVo(
                                recipe.id,
                                review.starRating.sum(),
                                review.id.count()
                        )
                )
                .from(recipe)
                .leftJoin(review)
                .on(recipe.id.eq(review.recipe.id))
                .groupBy(recipe.id)
                .fetch();
    }
}
