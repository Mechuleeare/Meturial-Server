package com.meturial.domain.recipe.domain.repository;

import com.meturial.domain.recipe.domain.repository.vo.QQueryRecipeDetailVo;
import com.meturial.domain.recipe.domain.repository.vo.QueryRecipeDetailVo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
                                recipe.tip,
                                review.starRating
                        ))
                .from(recipe)
                .innerJoin(review)
                .on(recipe.id.eq(review.recipe.id))
                .where(recipe.id.eq(recipeId))
                .fetchOne());
    }
}
