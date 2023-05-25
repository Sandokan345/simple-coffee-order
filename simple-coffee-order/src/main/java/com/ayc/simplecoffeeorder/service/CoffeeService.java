package com.ayc.simplecoffeeorder.service;

import com.ayc.simplecoffeeorder.model.Coffee;
import com.ayc.simplecoffeeorder.repository.CoffeeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeService {

    private final CoffeeDao coffeeDao;

    public List<Coffee> saveAll(List<Coffee> coffees) {
        return coffeeDao.saveAll(coffees);
    }
}
