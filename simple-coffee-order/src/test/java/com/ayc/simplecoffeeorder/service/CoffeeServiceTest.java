package com.ayc.simplecoffeeorder.service;

import com.ayc.simplecoffeeorder.model.Coffee;
import com.ayc.simplecoffeeorder.repository.CoffeeDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class CoffeeServiceTest {

    @Mock
    private CoffeeDao coffeeDao;

    private CoffeeService coffeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        coffeeService = new CoffeeService(coffeeDao);
    }

    @Test
    void testSaveAll() {
        List<Coffee> coffees = new ArrayList<>();
        // Mocking the saveAll method of coffeeDao
        when(coffeeDao.saveAll(anyList())).thenReturn(coffees);
        coffees.addAll(Arrays.asList(
                Coffee.builder().name("Americano").price(25.0).build(),
                Coffee.builder().name("Espresso").price(20.0).build(),
                Coffee.builder().name("Mocha").price(32.0).build()
        ));
        // Calling the saveAll method of coffeeService
        List<Coffee> savedCoffees = coffeeService.saveAll(coffees);
        // Verifying the saveAll method of coffeeDao is called once
        Assertions.assertEquals(coffees, savedCoffees);
        // Verifying the saveAll method of coffeeDao is called once
        verify(coffeeDao, times(1)).saveAll(coffees);
    }
}
