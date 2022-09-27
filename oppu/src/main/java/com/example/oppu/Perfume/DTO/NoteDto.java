package com.example.oppu.Perfume.DTO;

import javax.persistence.Column;
import javax.persistence.Id;

public class NoteDto {

    @Id
    private String noteName;

    private String allGroup;

    private String explanation;

    private String img;

    private String perfumeName;

    private String noteGroup;

}
