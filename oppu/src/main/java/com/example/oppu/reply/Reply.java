package com.example.oppu.reply;

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
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    //baseEntity 상속받을 예정
    private LocalDateTime createDate;

    //게시글
    @ManyToOne
    private Board board;
}
