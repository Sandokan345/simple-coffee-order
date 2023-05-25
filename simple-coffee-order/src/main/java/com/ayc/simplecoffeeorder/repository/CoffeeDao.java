package com.ayc.simplecoffeeorder.repository;

import com.ayc.simplecoffeeorder.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeDao extends JpaRepository<Coffee, Long> {
}
