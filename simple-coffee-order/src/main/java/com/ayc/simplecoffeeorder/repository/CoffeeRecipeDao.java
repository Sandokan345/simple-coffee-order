package com.ayc.simplecoffeeorder.repository;

import com.ayc.simplecoffeeorder.model.CoffeeRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRecipeDao extends JpaRepository<CoffeeRecipe, Long> {
}
