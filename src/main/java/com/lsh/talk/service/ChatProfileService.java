package com.lsh.talk.service;

import com.lsh.talk.dto.request.ProfileNameChangeRequest;

public interface ChatProfileService {

    void editProfileName(ProfileNameChangeRequest profileNameChangeRequest);
}
