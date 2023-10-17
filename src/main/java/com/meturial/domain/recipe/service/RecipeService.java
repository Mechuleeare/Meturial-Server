package com.meturial.domain.recipe.service;

import com.meturial.domain.recipe.domain.RecipeSequence;
import com.meturial.domain.recipe.domain.repository.RecipeRepository;
import com.meturial.domain.recipe.domain.repository.RecipeSequenceRepository;
import com.meturial.domain.recipe.domain.repository.vo.QueryRecipeDetailVo;
import com.meturial.domain.recipe.exception.RecipeNotFoundException;
import com.meturial.domain.recipe.exception.RecipeSequenceNotFoundException;
import com.meturial.domain.recipe.presentation.dto.response.QueryRecipeDetailResponse;
import com.meturial.domain.review.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeSequenceRepository recipeSequenceRepository;
    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public QueryRecipeDetailResponse queryRecipeDetailByRecipeId(UUID recipeId) {
        QueryRecipeDetailVo recipeDetail = recipeRepository.queryRecipeDetailByRecipeId(recipeId)
                .orElseThrow(() -> RecipeNotFoundException.EXCEPTION);

        List<RecipeSequence> recipeSequence = recipeSequenceRepository.queryRecipeSequenceListByRecipeId(recipeId)
                .orElseThrow(() -> RecipeSequenceNotFoundException.EXCEPTION);

        return QueryRecipeDetailResponse.builder()
                .recipeId(recipeDetail.getRecipeId())
                .name(recipeDetail.getName())
                .starRating(recipeDetail.getStarRating())
                .starCount(reviewRepository.countByRecipeId(recipeId)) // TODO: 10/17/23 count 쿼리로 수정
                .recipeImageUrl(recipeDetail.getRecipeImageUrl())
                .recipeCategory(List.of(recipeDetail.getRecipeCategory().split(",")))
                .recipeMaterial(List.of(recipeDetail.getRecipeCategory().split(",")))
                .recipeTip(recipeDetail.getRecipeTip())
                .recipeSequence(recipeSequence)
                .build();
    }
}
