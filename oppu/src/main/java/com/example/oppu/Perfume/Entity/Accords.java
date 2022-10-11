package com.example.oppu.Perfume.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@ToString
@Getter
@Setter
@Table(name = "ACCORDS")
@Entity()
public class Accords {

    @Id
    @Column(name = "SEQ", length = 38)
    private Integer seq;

    @Column(name = "PERFUME_NAME", length=70, nullable = false)
    private String perfumeName;

    @Column(name = "SCENT_NAME", length=30)
    private String scentName;

    @Column(name = "RATIO")
    private float ratio;
}
