package com.example.oppu.board;

import com.example.oppu.reply.Reply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //유저 닉네임 삭제 예정
    @Column(length = 200)
    private String nickname;

    //게시글 카테고리
    private String category;

    //게시글 제목
    private String title;

    //게시글 작성자(유저 닉네임과 묶을 예정)
    private String writer;

    //게시글 내용
    private String content;

//    //게시글 조회수
//    private Long views;
//
//    //게시글 추천수
//    private int recommendNumber;
//
//    //게시글 신고수
//    private int reportNumber;

    //baseEntity 상속받고 삭제예정
    private LocalDateTime createDate;

    //댓글 리스트
    @OneToMany(mappedBy = "board")
    private List<Reply> replyList;
}
