package com.lsh.talk.repository;

import com.lsh.talk.DbIntegrationTest;
import com.lsh.talk.domain.ChatUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest extends DbIntegrationTest {

    @Autowired
    private ChatUserRepository userRepository;

    @BeforeEach
    public void setUp() {
       userRepository.deleteAll();
    }

    @Test
    void findById() {

        // 테스트 데이터 생성
        ChatUser user = ChatUser.builder()
                .uniqueName("test")
                .password("qwer1234")
                .hashType("")
                .updateDate(Instant.now())
                .createDate(Instant.now()).build();

        // 저장
        userRepository.save(user);

        // 조회
        Optional<ChatUser> foundUser = userRepository.findById(user.getId());

        // 검증
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUniqueName()).isEqualTo(user.getUniqueName());
        assertThat(foundUser.get().getPassword()).isEqualTo(user.getPassword());
        assertThat(foundUser.get().getHashType()).isEqualTo(user.getHashType());
    }
}