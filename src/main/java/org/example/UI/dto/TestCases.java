package org.example.UI.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class TestCases {

    private String authorization1;
    private String signUp;
    private String passwordRestore;
    private String signUpWithInviteLink;
    private String authorization2;
}
