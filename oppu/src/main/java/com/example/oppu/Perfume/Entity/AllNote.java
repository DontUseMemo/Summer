package com.example.oppu.Perfume.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "ALL_NOTE")
@Entity
public class AllNote {

    @Id
    @Column(name = "NOTE_NAME" , length=100, nullable = false)
    private String noteName;

    @Column(name = "ALL_GROUP" , length=100, nullable = false)
    private String allGroup;

    @Column(name = "EXPLANATION" , length=700)
    private String explanation;

    @Column(name = "IMG" , length=100,nullable = false)
    private String img;

}
