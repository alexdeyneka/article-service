package com.article.service.repository;

import com.article.service.entity.User;
import com.article.service.utils.TestDataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    private static final List<User> usersList = new TestDataGenerator().generateUserList();

    @BeforeEach
    void setUp() {
        populateDB();
    }

    @Test
    void testFindAll() {
        List<User> testList = userRepo.findAll();
        assertEquals(testList.size(), usersList.size());
    }

    @Test
    void testSave() {
        userRepo.save(new User());
        List<User> testList = userRepo.findAll();
        assertEquals(testList.size(), usersList.size() + 1);
    }

    public void populateDB() {
        usersList.forEach(n -> userRepo.save(n));
    }
}