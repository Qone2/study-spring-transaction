package com.example.spring_transaction.food.mapper;

import com.example.spring_transaction.food.entity.Food;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FoodMapper {
    int insert(Food food);
}
