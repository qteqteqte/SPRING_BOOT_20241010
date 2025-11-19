package com.example.demo.model.service;

import jakarta.validation.constraints.*;

import lombok.*; // 어노테이션 자동 생성
import com.example.demo.model.domain.Member;

@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Data // getter, setter, toString, equals 등 자동 생성

public class AddMemberRequest {

    // 조건: 공백 x, 특수문자 x (한글, 영문 대소문자, 숫자만 허용)
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]+$", message = "이름에는 특수문자를 사용할 수 없습니다.")
    private String name;

    // 조건: 공백 x, 이메일 형식
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    // 조건: 패턴 o (8글자 이상, 대소문자 포함)
    // 설명: (?=.*[a-z]) 소문자 포함, (?=.*[A-Z]) 대문자 포함, .{8,} 8글자 이상
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "비밀번호는 8자 이상이어야 하며, 대문자와 소문자를 모두 포함해야 합니다.")
    private String password;

    // 조건: 패턴 o, 만 19세 이상 ~ 90세 이하
    // 타입이 String이므로 정규식으로 처리: 19 또는 20~89 또는 90
    @NotBlank(message = "나이는 필수 입력 값입니다.")
    @Pattern(regexp = "^(19|[2-8][0-9]|90)$", message = "나이는 19세 이상 90세 이하만 가능합니다.")
    private String age;

    private String mobile;

    private String address;

    public Member toEntity() { // Member 생성자를 통해 객체 생성
        return Member.builder()
            .name(name)
            .email(email)
            .password(password)
            .age(age)
            .mobile(mobile)
            .address(address)
            .build();
    }
}