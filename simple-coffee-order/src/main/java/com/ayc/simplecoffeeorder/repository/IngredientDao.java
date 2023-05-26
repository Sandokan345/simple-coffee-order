package com.ayc.simplecoffeeorder.repository;

import com.ayc.simplecoffeeorder.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientDao extends JpaRepository<Ingredient, Long> {
}
