package com.example.spring_transaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestServiceTest {

    @Autowired
    private TestService testService;

    @Test
    void readUncommittedTest() {
        testService.readUncommittedTest();
    }

    @Test
    void readCommittedTest() {
        testService.readCommittedTest();
    }

    @Test
    void repeatableReadTest() {
        testService.repeatableReadTest();
    }
}