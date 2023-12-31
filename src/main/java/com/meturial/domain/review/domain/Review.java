package com.meturial.domain.review.domain;

import com.meturial.domain.recipe.domain.Recipe;
import com.meturial.domain.review.exception.ReviewIsNotMineException;
import com.meturial.domain.user.domain.User;
import com.meturial.global.entity.BaseUUIDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Entity
@Table(name = "tbl_review")
public class Review extends BaseUUIDEntity {

    @Column(columnDefinition = "FLOAT", nullable = false)
    private Float starRating;

    @Column(columnDefinition = "VARCHAR(500)", nullable = false)
    private String content;

    @Column(columnDefinition = "VARCHAR(255)")
    private String reviewImageUrl;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "BINARY(16)", name = "recipe_id", nullable = false)
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "BINARY(16)", name = "user_id", nullable = false)
    private User user;

    public void checkReviewIsMine(UUID userId) {
        if (!userId.equals(this.user.getId())) {
            throw ReviewIsNotMineException.EXCEPTION;
        }
    }

    public void updateReview(Float starRating, String content, String reviewImageUrl) {
        this.starRating = starRating;
        this.content = content;
        this.reviewImageUrl = reviewImageUrl;
    }

    public String getReviewWriterName() {
        return this.user.getName();
    }

    public String getReviewRecipeName() {
        return this.recipe.getName();
    }
}
