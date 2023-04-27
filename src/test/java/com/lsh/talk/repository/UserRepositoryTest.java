package com.lsh.talk.repository;

import com.lsh.talk.DbIntegrationTest;
import com.lsh.talk.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest extends DbIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
       userRepository.deleteAll();
    }

    @Test
    void findById() {

        // 테스트 데이터 생성
        User user = new User();
        user.setName("test");
        user.setPassword("qwer1234");
        user.setHashType("");
        user.setUpdateDate(Instant.now());
        user.setCreateDate(Instant.now());

        // 저장
        userRepository.save(user);

        // 조회
        Optional<User> foundUser = userRepository.findById(user.getUserId());

        // 검증
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo(user.getName());
        assertThat(foundUser.get().getPassword()).isEqualTo(user.getPassword());
        assertThat(foundUser.get().getHashType()).isEqualTo(user.getHashType());
    }
}