package com.example.oppu.base_entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@DynamicInsert
public abstract class BaseTimeEntity {

    //생성 날짜
    @CreatedDate
    private LocalDateTime createDate;

    //수정 날짜
    @LastModifiedDate
    private LocalDateTime updateDate;

    //데이터 삭제 상태
    @Column(columnDefinition = "CHAR(3) default'N'")
    //    @CreatedDate이랑  LocalDateTime이랑 같이 사용해도 되는지 확인
    private String deleteYN;

}
