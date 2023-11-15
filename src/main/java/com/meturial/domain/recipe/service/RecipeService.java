package com.meturial.domain.recipe.service;

import com.meturial.domain.recipe.domain.repository.RecipeRepository;
import com.meturial.domain.recipe.domain.repository.RecipeSequenceRepository;
import com.meturial.domain.recipe.domain.repository.vo.QueryRecipeDetailVo;
import com.meturial.domain.recipe.domain.repository.vo.QueryRecipeRankingVo;
import com.meturial.domain.recipe.exception.RecipeNotFoundException;
import com.meturial.domain.recipe.presentation.dto.response.CategoryElement;
import com.meturial.domain.recipe.presentation.dto.response.QueryCategoryResponse;
import com.meturial.domain.recipe.presentation.dto.response.QueryRecipeDetailResponse;
import com.meturial.domain.recipe.presentation.dto.response.QueryRecipeRankingListResponse;
import com.meturial.domain.recipe.presentation.dto.response.RecipeRankingElement;
import com.meturial.domain.recipe.presentation.dto.response.RecipeSequenceElement;
import com.meturial.domain.review.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RecipeService {

    private static final String STAR_RATING = "starRating";
    private static final String STAR_COUNT = "starCount";

    private final RecipeRepository recipeRepository;
    private final RecipeSequenceRepository recipeSequenceRepository;
    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public QueryCategoryResponse queryCategory() {
        List<CategoryElement> categoryList = recipeRepository.queryCategory()
                .stream()
                .map(CategoryElement::of)
                .toList();

        return new QueryCategoryResponse(categoryList);
    }

    @Transactional(readOnly = true)
    public QueryRecipeDetailResponse queryRecipeDetailByRecipeId(UUID recipeId) {
        QueryRecipeDetailVo recipeDetailVo = recipeRepository.queryRecipeDetailByRecipeId(recipeId)
                .orElseThrow(() -> RecipeNotFoundException.EXCEPTION);

        List<RecipeSequenceElement> recipeSequenceList = recipeSequenceRepository.queryRecipeSequenceListByRecipeId(recipeId)
                .stream()
                .map(RecipeSequenceElement::of)
                .toList();

        List<Float> starRatingList = reviewRepository.queryStarRatingListByRecipeId(recipeId);
        int starCount = starRatingList.size();
        double avgStarRating = starRatingList.stream().mapToDouble(Float::floatValue).sum() / starCount;

        return QueryRecipeDetailResponse.builder()
                .recipeId(recipeDetailVo.getRecipeId())
                .name(recipeDetailVo.getName())
                .starRating((float) avgStarRating)
                .starCount(starCount)
                .recipeImageUrl(recipeDetailVo.getRecipeImageUrl())
                .recipeCategory(List.of(recipeDetailVo.getRecipeCategory().replace(" ", "").split(",")))
                .recipeMaterial(List.of(recipeDetailVo.getRecipeMaterial().replace(" ", "").split(",")))
                .recipeSequence(recipeSequenceList)
                .build();
    }

    @Transactional(readOnly = true)
    public QueryRecipeRankingListResponse queryRecipeRankingList(String type) {
        List<QueryRecipeRankingVo> recipeRankingVo = switch (type) {
            case STAR_RATING -> recipeRepository.queryRecipeRankingListOrderByStarRating();
            case STAR_COUNT -> recipeRepository.queryRecipeRankingListOrderByStarCount();
            default -> List.of();
        };

        List<RecipeRankingElement> recipeRankingElements = recipeRankingVo
                .stream()
                .map(this::buildRecipeRankingElement)
                .toList();

        return new QueryRecipeRankingListResponse(recipeRankingElements);
    }

    private RecipeRankingElement buildRecipeRankingElement(QueryRecipeRankingVo recipeRankingVo) {
        return RecipeRankingElement.builder()
                .recipeId(recipeRankingVo.getRecipeId())
                .name(recipeRankingVo.getName())
                .starRating(recipeRankingVo.getStarRating())
                .starCount(recipeRankingVo.getStarCount())
                .recipeImageUrl(recipeRankingVo.getRecipeImageUrl())
                .recipeCategory(List.of(recipeRankingVo.getRecipeCategory().replace(" ", "").split(",")))
                .recipeMaterial(List.of(recipeRankingVo.getRecipeMaterial().replace(" ", "").split(",")))
                .build();
    }
}
