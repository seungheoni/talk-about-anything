package com.lsh.talk.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProfileNameChangeRequest {

    private UUID chatUserId;

    private String nameToChange;
}
