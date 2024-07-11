package com.example.spring_transaction.user;

import com.example.spring_transaction.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void saveAndNotRollback() {
        userService.saveAndNotRollback();
    }

    @Test
    void saveAndRollback() {
        userService.saveAndRollback();
    }

    @Test
    void saveAndCommit() {
        userService.saveAndCommit();
    }

//    @Test
//    @Rollback(value = false)
//    void readUncommittedTest() {
//        userService.readUncommittedTest();
//    }
//
//    @Test
//    @Rollback(value = false)
//    void readCommittedTest() {
//        userService.readCommittedTest();
//    }
//
//    @Test
//    @Rollback(value = false)
//    void readCommittedTest2() {
//        userService.readCommittedTest2();
//    }
//
//    @Test
//    @Rollback(value = false)
//    void repeatableReadTest() {
//        userService.repeatableReadTest();
//    }

    @Test
    void saveAndNotCommit() {
        userService.saveAndNotCommit();
    }

    @Test
    void changeAndCommit() {
        User user = userService.getUserById(1L);
        userService.changeAndCommit(1L);
    }

    @Test
    void foreachTest() {
        userService.insertUsers();
    }
}