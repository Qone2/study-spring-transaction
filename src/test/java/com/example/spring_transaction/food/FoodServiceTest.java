package com.example.spring_transaction.food;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FoodServiceTest {

    @Autowired
    private FoodService foodService;

    @Test
    void saveAndNotRollback() {
        foodService.saveAndNotRollback();
    }

    @Test
    void saveAndCommit() {
        foodService.saveAndCommit();
    }
}