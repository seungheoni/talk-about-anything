package com.lsh.talk;

import com.lsh.talk.repository.ChatFriendRepository;
import com.lsh.talk.repository.ChatRoomParticipantRepository;
import com.lsh.talk.repository.ChatRoomRepository;
import com.lsh.talk.repository.ChatUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("dev") //@Profile이 dev인 클래스들을 로드
public class DbIntegrationTest {

    @Autowired
    private ChatUserRepository chatUserRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatFriendRepository chatFriendRepository;

    @Autowired
    private ChatRoomParticipantRepository chatRoomParticipantRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("talkAboutAnything")
            .withUsername("seongheon")
            .withPassword("qwer1234");

    static {
        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    public static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Test
    public void pingTest() {
        assertTrue(postgreSQLContainer.isRunning());
    }
}