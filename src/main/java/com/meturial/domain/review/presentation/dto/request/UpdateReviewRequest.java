package com.meturial.domain.review.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateReviewRequest {

    @NotNull(message = "별점을 남겨주세요.")
    private Float starRating;

    @NotBlank(message = "내용을 남겨주세요.")
    @Size(max = 255, message = "내용은 최대 255자 이하여야 합니다.")
    private String content;

    private String reviewImageUrl;
}
