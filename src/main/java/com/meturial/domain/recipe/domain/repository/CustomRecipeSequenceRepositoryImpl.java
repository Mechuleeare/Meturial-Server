package com.meturial.domain.recipe.domain.repository;

import com.meturial.domain.recipe.domain.RecipeSequence;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.meturial.domain.recipe.domain.QRecipeSequence.recipeSequence;

@RequiredArgsConstructor
@Component
public class CustomRecipeSequenceRepositoryImpl implements CustomRecipeSequenceRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<RecipeSequence> queryRecipeSequenceListByRecipeId(UUID recipeId) {
        return queryFactory
                .selectFrom(recipeSequence)
                .where(recipeSequence.recipe.id.eq(recipeId))
                .orderBy(recipeSequence.sequence.asc())
                .fetch();
    }
}
