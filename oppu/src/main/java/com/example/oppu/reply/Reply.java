package com.example.oppu.reply;

import com.example.oppu.base_entity.BaseTimeEntity;
import com.example.oppu.board.Board;
import com.example.oppu.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reply extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    //댓글 내용
    @Column
    private String content;

    @ManyToOne
    private Member writer;

//    //댓글 추천수
//    private int recommendNumber;
//
//    //댓글 신고수
//    private int reportNumber;

//    // 댓글 작성일
//    private LocalDateTime createDate;
//
//    //댓글 수정일
//    private LocalDateTime modifyDate;

    //게시글
    @ManyToOne
    private Board board;
}
