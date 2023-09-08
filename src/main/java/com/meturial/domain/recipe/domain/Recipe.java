package com.meturial.domain.recipe.domain;

import com.meturial.global.entity.BaseUUIDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Entity
@Table(name = "tbl_recipe")
public class Recipe extends BaseUUIDEntity {

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String foodImageUrl;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    private String name;

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private String material;
}
