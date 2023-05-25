package com.ayc.simplecoffeeorder;

import com.ayc.simplecoffeeorder.model.Coffee;
import com.ayc.simplecoffeeorder.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
@RequiredArgsConstructor
public class ApplicationStartup implements CommandLineRunner {

    private final CoffeeService coffeeService;

    @Override
    public void run(String... args) {
        if (coffeeService.count() > 0) {
            log.info("Coffee list is already filled");
        } else {
            saveDefaultCoffees();
        }
    }

    private void saveDefaultCoffees() {
        List<Coffee> coffees = new ArrayList<>();
        fillCoffees(coffees);
        coffeeService.saveAll(coffees);
        log.info("Coffee list is filled");
    }

    private void fillCoffees(List<Coffee> coffees) {
        coffees.add(new Coffee("Espresso", 20.0));
        coffees.add(new Coffee("Double Espresso", 29.0));
        coffees.add(new Coffee("Cappuccino", 27.0));
        coffees.add(new Coffee("Caffe Latte", 27.0));
        coffees.add(new Coffee("Mocha", 32.0));
        coffees.add(new Coffee("Americano", 25.0));
        coffees.add(new Coffee("Hot Water", 5.0));
    }
}
