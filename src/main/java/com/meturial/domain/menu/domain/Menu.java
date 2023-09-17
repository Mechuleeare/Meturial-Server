package com.meturial.domain.menu.domain;

import com.meturial.domain.menu.domain.type.MenuType;
import com.meturial.domain.recipe.domain.ChoiceRecipe;
import com.meturial.global.entity.BaseUUIDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Entity
@Table(name = "tbl_menu")
public class Menu extends BaseUUIDEntity {

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(9)", nullable = false)
    private MenuType menuType;

    @Column(columnDefinition = "BIT(1) default 1", nullable = false)
    private Boolean isActivated;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "BINARY(16)", name = "choice_recipe_id", nullable = false)
    private ChoiceRecipe choiceRecipe;
}
