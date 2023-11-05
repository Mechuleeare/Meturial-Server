package com.meturial.domain.recipe.domain.repository;

import com.meturial.domain.recipe.domain.repository.vo.QQueryRecipeDetailVo;
import com.meturial.domain.recipe.domain.repository.vo.QQueryRecipeRankingVo;
import com.meturial.domain.recipe.domain.repository.vo.QueryRecipeDetailVo;
import com.meturial.domain.recipe.domain.repository.vo.QueryRecipeRankingVo;
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
public class CustomRecipeRepositoryImpl implements CustomRecipeRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<QueryRecipeDetailVo> queryRecipeDetailByRecipeId(UUID recipeId) {
        return Optional.ofNullable(queryFactory.select(
                        new QQueryRecipeDetailVo(
                                recipe.id,
                                recipe.name,
                                recipe.foodImageUrl,
                                recipe.category,
                                recipe.material,
                                review.starRating
                        ))
                .from(recipe)
                .innerJoin(review)
                .on(recipe.id.eq(review.recipe.id))
                .where(recipe.id.eq(recipeId))
                .fetchOne());
    }

    @Override
    public List<QueryRecipeRankingVo> queryRecipeRankingListOrderByStarRating() {
        return queryFactory.select(
                        new QQueryRecipeRankingVo(
                                recipe.id,
                                recipe.name,
                                review.starRating,
                                review.recipe.id.count(),
                                recipe.foodImageUrl,
                                recipe.category,
                                recipe.material
                        ))
                .from(recipe)
                .innerJoin(review)
                .on(recipe.id.eq(review.recipe.id))
                .orderBy(review.starRating.desc())
                .fetch();
    }

    @Override
    public List<QueryRecipeRankingVo> queryRecipeRankingListOrderByStarCount() {
        return queryFactory.select(
                        new QQueryRecipeRankingVo(
                                recipe.id,
                                recipe.name,
                                review.starRating,
                                review.recipe.id.count(),
                                recipe.foodImageUrl,
                                recipe.category,
                                recipe.material
                        ))
                .from(recipe)
                .innerJoin(review)
                .on(recipe.id.eq(review.recipe.id))
                .orderBy(review.recipe.id.count().desc())
                .fetch();
    }
}
