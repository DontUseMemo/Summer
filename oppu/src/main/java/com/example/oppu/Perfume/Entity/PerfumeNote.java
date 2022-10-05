package com.example.oppu.Perfume.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "PERFUME_NOTE")
@Entity()
public class PerfumeNote {
    @Id
    @Column(name = "SEQ", length = 22)
    private Integer seq;

    @Column(name = "PERFUME_NAME", length = 70,nullable = false)
    private String perfumeName;

    @Column(name="NOTE_GROUP", length = 30)
    private String noteGroup;

    @Column(name = "NOTE", length = 50)
    private String note;
}
