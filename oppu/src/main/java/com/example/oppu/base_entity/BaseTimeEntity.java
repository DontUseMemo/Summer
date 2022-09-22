package com.example.oppu.base_entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseTimeEntity {

    //생성 날짜
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date createDate;

    //수정 날짜
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date updateDate;

    //데이터 삭제 상태
//    @Column(columnDefinition = "CHAR(3) default'N'")

    //삭제 날짜
    @Column
    private String deleteYN;

}
