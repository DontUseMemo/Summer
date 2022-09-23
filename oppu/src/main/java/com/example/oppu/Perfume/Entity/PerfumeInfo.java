package com.example.oppu.Perfume.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "PERFUME_INFO")
@Entity()
public class PerfumeInfo {

    @Id
    @Column(name = "PERFUME_NAME" , length=70)
    private String perfumeName;

    @Column(name = "GENDER", length=18)
    private String gender;

    @Column(name="BRAND" ,length=40)
    private String brand;

    @Column(name = "HAVE",length=9)
    private String have;

    @Column(name = "HAD" , length=9)
    private String had;

    @Column(name = "WANT" , length=9)
    private  String

}
