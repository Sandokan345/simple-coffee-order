package com.ayc.simplecoffeeorder.service;

import com.ayc.simplecoffeeorder.model.Coffee;
import com.ayc.simplecoffeeorder.repository.CoffeeDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CoffeeService {

    private final CoffeeDao coffeeDao;

    public List<Coffee> saveAll(List<Coffee> coffees) {
        log.info("Saving all coffees: {}", coffees.stream().map(Coffee::getName).toArray());
        return coffeeDao.saveAll(coffees);
    }

    public long count() {
        return coffeeDao.count();
    }

    public List<Coffee> findAll() {
        return coffeeDao.findAll();
    }
}
