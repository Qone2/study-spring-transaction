package com.example.spring_transaction.user;

import com.example.spring_transaction.user.entity.User;
import com.example.spring_transaction.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;


    public void saveAndNotRollback() {

        User user = new User();
        user.setName("Not Rollback User");

        userMapper.insert(user);
        throw new RuntimeException("Throwing exception");
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void readUncommittedTest() {
        long firstCount = userMapper.count();
//        try {
//            newTransactionNotCommit();
//        } catch (RuntimeException e) {
//            // 예외를 잡아서 롤백 처리
//            System.out.println("Caught exception: " + e.getMessage());
//        }

//        saveAndNotCommit();

//        saveAndNotCommitWithEm();
        long secondCount = userMapper.count();
        System.out.println("First count: " + firstCount);
        System.out.println("Second count: " + secondCount);
    }

//    public void dirtyReadTest() {
//        User user = userMapper.selectById(1L);
//        changeAndNotCommit(user);
//        long secondCount = userMapper.count();
//        System.out.println("First count: " + firstCount);
//        System.out.println("Second count: " + secondCount);
//    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void readCommittedTest() {
        long firstCount = userMapper.count();
//        try {
//            newTransactionNotCommit();
//        } catch (RuntimeException e) {
//            // 예외를 잡아서 롤백 처리
//            System.out.println("Caught exception: " + e.getMessage());
//        }

//        saveAndNotCommit();
//        saveAndNotCommitWithEm();
        long secondCount = userMapper.count();
        System.out.println("First count: " + firstCount);
        System.out.println("Second count: " + secondCount);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void readCommittedTest2() {
        long firstCount = userMapper.count();
        saveAndCommit();
        long secondCount = userMapper.count();
        System.out.println("First count: " + firstCount);
        System.out.println("Second count: " + secondCount);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void repeatableReadTest() {
        User user = userMapper.selectById(1L);
        System.out.println("User1: " + user.getName());
        changeAndCommit(1L);
        User user2 = userMapper.selectById(1L);
        System.out.println("User2: " + user2.getName());
    }

    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void changeAndCommit(long id) {
        changeAndCommit(id, "asd");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void changeAndCommit(long id, String name) {
        User newUser = new User();
        newUser.setId(id);
        newUser.setName(name);
        userMapper.update(newUser);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void changeAndNotCommit(long id) {
        changeAndNotCommit(id, "asd");
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void changeAndNotCommit(long id, String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        userMapper.update(user);
    }

//    public void saveAndNotCommitWithEm() {
//        em.getTransaction().begin();
//
//        User user = new User();
//        user.setName("John Doe");
//
//        em.persist(user);
//        em.close();
//    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void newTransactionNotCommit() {
        User user = new User();
        user.setName("Not Commit User");
        userMapper.insert(user);
        // 강제로 예외를 발생시켜 트랜잭션을 롤백시킴
        throw new RuntimeException("Intentional Exception to Rollback Transaction");
    }

    @Transactional
    public void saveAndRollback() {

        User user = new User();
        user.setName("John Doe");

        userMapper.insert(user);

        throw new RuntimeException("Rollback this transaction");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAndCommit() {

        User user = new User();
        user.setName("John Doe");

        userMapper.insert(user);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void saveAndNotCommit() {

        User user = new User();
        user.setName("John Doe");

        userMapper.insert(user);
    }

    public long countUsers() {
        return userMapper.count();
    }
}
