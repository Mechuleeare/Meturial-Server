package com.meturial.domain.recipe.domain.repository;

import com.meturial.domain.recipe.domain.repository.vo.QQueryChoiceRecipeListVo;
import com.meturial.domain.recipe.domain.repository.vo.QueryChoiceRecipeListVo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.meturial.domain.recipe.domain.QChoiceRecipe.choiceRecipe;
import static com.meturial.domain.recipe.domain.QRecipe.recipe;
import static com.meturial.domain.review.domain.QReview.review;

@RequiredArgsConstructor
@Component
public class CustomChoiceRecipeRepositoryImpl implements CustomChoiceRecipeRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<QueryChoiceRecipeListVo> queryChoiceRecipeList(UUID userId) {
        return queryFactory.select(
                        new QQueryChoiceRecipeListVo(
                                choiceRecipe.id,
                                choiceRecipe.recipe.id,
                                recipe.name,
                                review.starRating,
                                review.recipe.id.count(),
                                recipe.foodImageUrl,
                                recipe.category
                        ))
                .from(choiceRecipe)
                .innerJoin(recipe)
                .on(choiceRecipe.recipe.id.eq(recipe.id))
                .leftJoin(review)
                .on(choiceRecipe.recipe.id.eq(review.recipe.id))
                .where(choiceRecipe.user.id.eq(userId))
                .orderBy(choiceRecipe.id.asc())
                .groupBy(choiceRecipe.recipe.id)
                .fetch();
    }
}
