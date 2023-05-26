package com.ayc.simplecoffeeorder.repository;

import com.ayc.simplecoffeeorder.model.Coffee;
import com.ayc.simplecoffeeorder.model.CoffeeRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeRecipeDao extends JpaRepository<CoffeeRecipe, Long> {

    List<CoffeeRecipe> findByCoffee(Coffee coffee);
}
