package com.example.oppu.base_entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@DynamicInsert
@DynamicUpdate
@ToString
public abstract class BaseTimeEntity {

    //생성 날짜
    @CreatedDate
    private LocalDateTime createDate;

    //수정 날짜
    @LastModifiedDate
    private LocalDateTime updateDate;

    //데이터 삭제 상태
    @Column(columnDefinition = "CHAR(1) default 'N'")
    private String deleteYN;
}