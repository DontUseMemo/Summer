package com.example.oppu.magazine;

import com.example.oppu.base_entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Magazine extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //매거진 제목
    @Column(length = 40, nullable = false)
    private String title;

    //매거진 내용
    @Column(columnDefinition = "CHAR(10) default 'no content'")
    private String content;

    //매거진 작성자
    @Column(length = 40, nullable = false, updatable = false)
    private String writer;

}