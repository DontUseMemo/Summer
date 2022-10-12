package com.example.oppu.board;

import com.example.oppu.base_entity.BaseTimeEntity;
import com.example.oppu.member.Member;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    //유저 닉네임 삭제 예정
//    @Column(length = 200)
//    private String nickname;

    //게시글 카테고리
    private String category;

    //게시글 제목
    private String title;

    //게시글 작성자(유저 닉네임과 묶을 예정 -> 10월 11일 ManyToOne)
    @ManyToOne
    private Member writer;

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

    // 게시글 작성일
    private LocalDateTime createDate;

    // 게시글 수정일일
   private LocalDateTime modifyDate;

    //댓글 리스트
    @OneToMany(mappedBy = "board")
    private List<Reply> replyList;
}
