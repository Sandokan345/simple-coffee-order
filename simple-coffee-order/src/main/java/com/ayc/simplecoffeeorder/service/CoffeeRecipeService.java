package com.ayc.simplecoffeeorder.service;

import com.ayc.simplecoffeeorder.model.CoffeeRecipe;
import com.ayc.simplecoffeeorder.repository.CoffeeRecipeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeRecipeService {

    private final CoffeeRecipeDao coffeeRecipeDao;

    public long count() {
        return coffeeRecipeDao.count();
    }

    public List<CoffeeRecipe> saveAll(List<CoffeeRecipe> coffeeRecipes) {
        return coffeeRecipeDao.saveAll(coffeeRecipes);
    }
}
