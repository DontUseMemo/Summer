package com.example.oppu.magazine;

import com.example.oppu.base_entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Magazine extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //매거진 제목
    @Column(length = 40, nullable = false)
    private String title;

    //매거진 내용
    @ColumnDefault("'no content'")
    private String content;

    //매거진 작성자
    @Column(length = 40, nullable = false, updatable = false)
    private String writer;

    //매거진 조회수
    @ColumnDefault("0")
    @Column(nullable = false, updatable = false)
    private int  views;

    //매거진 추천수
    @Column(nullable = false, updatable = false)
    private int recommendNumber;
}