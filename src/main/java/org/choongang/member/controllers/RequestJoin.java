package org.choongang.member.controllers;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

// 커맨드 객체
@Data
public class RequestJoin {

    @NotBlank @Email
    private String email;

    @NotBlank @Size(min = 8)
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String userName;

    @NotBlank
    private String mobile;

    @AssertTrue // 필수항목으로 반드시 true가 되어야만 하는 애너테이션
    private boolean agree;
}
