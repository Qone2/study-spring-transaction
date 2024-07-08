package com.example.spring_transaction.user;

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

    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback(false)
    void saveAndNotRollback() {
        userService.saveAndNotRollback();
    }

//    @Test
//    @Rollback(false)
//    void saveAndRollback() {
//        userService.saveAndRollback();
//    }

    @Test
    void saveAndCommit() {
        userService.saveAndCommit();
    }

    @Test
    @Rollback(value = false)
    void readUncommittedTest() {
        userService.readUncommittedTest();
    }

    @Test
    @Rollback(value = false)
    void readCommittedTest() {
        userService.readCommittedTest();
    }

    @Test
    @Rollback(value = false)
    void readCommittedTest2() {
        userService.readCommittedTest2();
    }

    @Test
    @Rollback(value = false)
    void repeatableReadTest() {
        userService.repeatableReadTest();
    }

    @Test
    void saveAndNotCommit() {
        userService.saveAndNotCommit();
    }


}