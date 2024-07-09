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
    void nonRepeatableReadTest() {
        testService.nonRepeatableReadTest();
    }

//    @Test
//    void nonRepeatableReadTest2() {
//        testService.nonRepeatableReadTest2();
//    }

    @Test
    void propagationTest() {
        assertThrows(RuntimeException.class, () -> testService.propagationTest());
//        testService.propagationTest();
    }

    @Test
    void propagationTest2() {
        assertThrows(RuntimeException.class, () -> testService.propagationTest2());
//        testService.propagationTest2();
    }

    @Test
    void nonRepeatableReadAvoided() {
        testService.nonRepeatableReadAvoided();
    }

//    @Test
//    void nonRepeatableReadAvoided2() {
//        testService.nonRepeatableReadAvoided2();
//    }

    @Test
    void dirtyReadTest() {
        testService.dirtyReadTest();
    }

    @Test
    void phantomReadTest() {
        testService.phantomReadTest();
    }

    @Test
    void phantomReadAvoided() {
        testService.phantomReadAvoided();
    }
}