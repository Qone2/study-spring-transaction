package com.example.spring_transaction;

import com.example.spring_transaction.food.FoodService;
import com.example.spring_transaction.user.entity.User;
import com.example.spring_transaction.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {
    private final UserService userService;
    private final FoodService foodService;

    public TestService(UserService userService, FoodService foodService) {
        this.userService = userService;
        this.foodService = foodService;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void readUncommittedTest() {
        long firstCount = userService.countUsers();
        userService.saveAndNotCommit();
        long secondCount = userService.countUsers();
        System.out.println("First count: " + firstCount);
        System.out.println("Second count: " + secondCount);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
    public void readCommittedTest() {
        long firstCount = userService.countUsers();
        try {
            userService.saveAndRollback();
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
        long secondCount = userService.countUsers();
        System.out.println("First count: " + firstCount);
        System.out.println("Second count: " + secondCount);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void repeatableReadTest() {
        User user = userService.getUserById(1L);
        userService.changeAndCommit(user);
        User user2 = userService.getUserById(1L);
        System.out.println("First user: " + user.getName());
        System.out.println("Second user: " + user2.getName());
    }
}
