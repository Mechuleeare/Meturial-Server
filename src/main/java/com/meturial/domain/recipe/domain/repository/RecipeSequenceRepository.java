package com.meturial.domain.recipe.domain.repository;

import com.meturial.domain.recipe.domain.RecipeSequence;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RecipeSequenceRepository extends CrudRepository<RecipeSequence, UUID> {
}
