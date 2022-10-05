package com.example.oppu.Perfume.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@ToString
@Setter
@Table(name = "PERFUMER_LIST")
@Entity
public class PerfumerList {

    @Id
    @Column(name = "SEQ", length = 22)
    private Integer seq;

    @Column(name = "PERFUME_NAME", length =70)
    private String perfumeName;

    @Column(name = "PERFUMER" , length = 40)
    private String perfumer;
}
