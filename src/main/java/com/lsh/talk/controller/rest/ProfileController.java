package com.lsh.talk.controller.rest;

import com.lsh.talk.dto.request.ProfileNameChangeRequest;
import com.lsh.talk.service.ChatProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ChatProfileService chatProfileService;

    /**
     * 프로필 이름 변경
     * @param profileNameChangeRequest 변경할 프로필 이름 정보와 변경할 유저id
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editProfile(@RequestBody ProfileNameChangeRequest profileNameChangeRequest) {

        chatProfileService.editProfileName(profileNameChangeRequest);
    }
}
