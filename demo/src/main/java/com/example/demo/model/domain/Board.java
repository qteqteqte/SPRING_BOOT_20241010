package com.example.demo.model.domain;

import lombok.*; // 어노테이션 자동 생성
import jakarta.persistence.*; // 기존 javax 후속 버전

@Getter // setter는 없음(무분별한 변경 x)
@Entity // 아래 객체와 DB 테이블을 매핑. JPA가 관리
@Table(name = "board") // 테이블 이름을 지정. 없는 경우 클래스이름으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부 생성자 접근 방지

public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", updatable = false) //아이디
    private Long id;
    
    @Column(name = "title", nullable = false) // 제목
    private String title = "";

    @Column(name = "user", nullable = false) // 이름
    private String user = "";
    
    @Column(name = "newdate", nullable = false) // 날짜
    private String newdate = "";

    @Column(name = "content", nullable = false) // 내용
    private String content = "";

    @Column(name = "count", nullable = false) // 조회수
    private String count = "";
    
    @Column(name = "likec", nullable = false) // 좋아요
    private String likec = "";
    @Builder

    public Board(String title, String user, String newdate, String content, String count, String likec) {
        this.title = title;
        this.user = user;
        this.newdate = newdate;
        this.content = content;
        this.count = count;
        this.likec = likec;
    }

    public void update(String title, String user, String newdate, String content, String count, String likec) { // 현재 객체 상태 업데이트
        this.title = title;
        this.user = user;
        this.newdate = newdate;
        this.content = content;
        this.count = count;
        this.likec = likec;
    }
}