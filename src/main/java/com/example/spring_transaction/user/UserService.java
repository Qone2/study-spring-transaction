package com.example.spring_transaction.user;

import com.example.spring_transaction.user.entity.User;
import com.example.spring_transaction.user.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Service
public class UserService {

    private final UserRepositoryEm userRepository;
    private final EntityManager em;
    private final PlatformTransactionManager transactionManager;
    private final UserMapper userMapper;

    public UserService(UserRepositoryEm userRepository, EntityManagerFactory entityManagerFactory, PlatformTransactionManager transactionManager, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.em = entityManagerFactory.createEntityManager();
        this.transactionManager = transactionManager;
        this.userMapper = userMapper;
    }


    @Transactional
    public void saveAndNotRollback() {

        User user = new User();
        user.setName("Not Rollback User");

        userMapper.insert(user);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void readUncommittedTest() {
        long firstCount = userRepository.count();
//        try {
//            newTransactionNotCommit();
//        } catch (RuntimeException e) {
//            // 예외를 잡아서 롤백 처리
//            System.out.println("Caught exception: " + e.getMessage());
//        }

//        saveAndNotCommit();

        saveAndNotCommitWithEm();
        long secondCount = userRepository.count();
        System.out.println("First count: " + firstCount);
        System.out.println("Second count: " + secondCount);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void readCommittedTest() {
        long firstCount = userRepository.count();
//        try {
//            newTransactionNotCommit();
//        } catch (RuntimeException e) {
//            // 예외를 잡아서 롤백 처리
//            System.out.println("Caught exception: " + e.getMessage());
//        }

//        saveAndNotCommit();
        saveAndNotCommitWithEm();
        long secondCount = userRepository.count();
        System.out.println("First count: " + firstCount);
        System.out.println("Second count: " + secondCount);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void readCommittedTest2() {
        long firstCount = userRepository.count();
        saveAndCommit();
        long secondCount = userRepository.count();
        System.out.println("First count: " + firstCount);
        System.out.println("Second count: " + secondCount);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void repeatableReadTest() {
        User user = userRepository.getUserById(1L);
        System.out.println("User1: " + user.getName());
        changeAndCommit(user);
        User user2 = userRepository.getUserById(1L);
        System.out.println("User2: " + user2.getName());
    }

    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void changeAndCommit(User user) {
        user.setName("zxc");
        userRepository.save(user);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void changeAndNotCommit(User user) {

        userRepository.save(user);
    }

    public void saveAndNotCommitWithEm() {
        em.getTransaction().begin();

        User user = new User();
        user.setName("John Doe");

        em.persist(user);
        em.close();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void newTransactionNotCommit() {
        User user = new User();
        user.setName("Not Commit User");
        userRepository.save(user);
        // 강제로 예외를 발생시켜 트랜잭션을 롤백시킴
        throw new RuntimeException("Intentional Exception to Rollback Transaction");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAndRollback() throws Exception {

        User user = new User();
        user.setName("John Doe");

        userRepository.save(user);

        throw new Exception("Rollback this transaction");
    }

    @Transactional
    public void saveAndCommit() {

        User user = new User();
        user.setName("John Doe");

        userMapper.insert(user);
    }


    public void saveAndNotCommit() {

        User user = new User();
        user.setName("John Doe");

        userMapper.insert(user);
    }

    public long countUsers() {
        return userRepository.count();
    }
}
