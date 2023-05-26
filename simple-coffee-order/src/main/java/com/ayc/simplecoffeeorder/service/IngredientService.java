package com.ayc.simplecoffeeorder.service;

import com.ayc.simplecoffeeorder.model.Ingredient;
import com.ayc.simplecoffeeorder.repository.IngredientDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientDao ingredientDao;

    public long count() {
        return ingredientDao.count();
    }

    public List<Ingredient> saveAll(List<Ingredient> ingredients) {
        return ingredientDao.saveAll(ingredients);
    }

    public List<Ingredient> findAll() {
        return ingredientDao.findAll();
    }
}
