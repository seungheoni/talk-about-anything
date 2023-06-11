package com.lsh.talk.service;

import com.lsh.talk.domain.ChatProfile;
import com.lsh.talk.dto.request.ProfileNameChangeRequest;
import com.lsh.talk.repository.ChatProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatProfileServiceImpl implements ChatProfileService {

    private final ChatProfileRepository chatProfileRepository;

    @Override
    @Transactional
    public void editProfileName(ProfileNameChangeRequest profileNameChangeRequest) {

        ChatProfile chatProfile = chatProfileRepository.findByChatUserId(profileNameChangeRequest.getChatUserId()).orElseThrow();
        chatProfile.setName(profileNameChangeRequest.getNameToChange());
    }
}
