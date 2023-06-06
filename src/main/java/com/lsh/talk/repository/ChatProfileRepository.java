package com.lsh.talk.repository;

import com.lsh.talk.domain.ChatProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatProfileRepository extends JpaRepository<ChatProfile, UUID> {

}
