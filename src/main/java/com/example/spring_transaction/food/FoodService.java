package com.example.spring_transaction.food;

import com.example.spring_transaction.food.entity.Food;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public void saveAndNotRollback() {

        Food food = new Food();
        food.setName("Pizza");

        foodRepository.save(food);

        throw new RuntimeException("Throwing exception");
    }

    @Transactional()
    public void saveAndCommit() {

        Food food = new Food();
        food.setName("Pizza");

        foodRepository.save(food);
    }
}
