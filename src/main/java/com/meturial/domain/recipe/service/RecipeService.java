package com.meturial.domain.recipe.service;

import com.meturial.domain.recipe.domain.Recipe;
import com.meturial.domain.recipe.domain.repository.RecipeRepository;
import com.meturial.domain.recipe.domain.repository.RecipeSequenceRepository;
import com.meturial.domain.recipe.domain.repository.vo.QueryRecipeDetailVo;
import com.meturial.domain.recipe.domain.repository.vo.QueryRecipeReviewVo;
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

import java.util.Comparator;
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

        return QueryRecipeDetailResponse.builder()
                .recipeId(recipeDetailVo.getRecipeId())
                .name(recipeDetailVo.getName())
                .starRating(getAverageStarRating(starRatingList))
                .starCount(starRatingList.size())
                .recipeImageUrl(recipeDetailVo.getRecipeImageUrl())
                .recipeCategory(List.of(recipeDetailVo.getRecipeCategory().replace(" ", "").split(",")))
                .recipeMaterial(List.of(recipeDetailVo.getRecipeMaterial().replace(" ", "").split(",")))
                .recipeSequence(recipeSequenceList)
                .build();
    }

    private float getAverageStarRating(List<Float> starRatingList) {
        return (float) starRatingList.stream().mapToDouble(Float::floatValue).sum() / starRatingList.size();
    }

    @Transactional(readOnly = true)
    public QueryRecipeRankingListResponse queryRecipeRankingList(String type) {
        List<QueryRecipeReviewVo> recipeReviewList = recipeRepository.queryRecipeReviewList();
        List<RecipeRankingElement> recipeRankingList = recipeRepository.findAll()
                .stream()
                .flatMap(recipe -> recipeReviewList
                        .stream()
                        .filter(recipeReview -> recipeReview.getRecipeId().equals(recipe.getId()))
                        .map(recipeReview -> {
                            Float starRating = recipeReview.getStarRating() != null ? recipeReview.getStarRating() / recipeReview.getStarCount() : 0;
                            return buildRecipeRankingElement(recipe, recipeReview, starRating);
                        }))
                .toList();

        switch (type) {
            case STAR_RATING ->
                    recipeRankingList = recipeRankingList.stream().sorted(Comparator.comparing(RecipeRankingElement::getStarRating).reversed()).toList();
            case STAR_COUNT ->
                    recipeRankingList = recipeRankingList.stream().sorted(Comparator.comparing(RecipeRankingElement::getStarCount).reversed()).toList();
        }

        return new QueryRecipeRankingListResponse(recipeRankingList);
    }

    private RecipeRankingElement buildRecipeRankingElement(Recipe recipe, QueryRecipeReviewVo review, Float starRating) {
        return RecipeRankingElement.builder()
                .recipeId(recipe.getId())
                .name(recipe.getName())
                .starRating(starRating)
                .starCount(review.getStarCount())
                .recipeImageUrl(recipe.getFoodImageUrl())
                .recipeCategory(List.of(recipe.getCategory().replace(" ", "").split(",")))
                .recipeMaterial(List.of(recipe.getMaterial().replace(" ", "").split(",")))
                .build();
    }
}
