package com.example.oppu.reply;

import com.example.oppu.board.Board;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

//    private int recommendNumber;
//
//    private int reportNumber;

    private LocalDateTime createDate;

    @ManyToOne
    private Board board;
}
