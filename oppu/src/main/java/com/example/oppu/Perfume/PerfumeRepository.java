package com.example.oppu.Perfume;

import com.example.oppu.Perfume.DTO.NoteDto;
import com.example.oppu.Perfume.DTO.PerfumeDto;
import com.example.oppu.Perfume.Entity.PerfumeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface PerfumeRepository extends JpaRepository {

    @Query(value = "SELECT * " +
            "FROM PERFUME_INFO info" +
                "FULL OUTER JOIN PERFUME_NOTE note" +
                "ON info.PERFUME_NAME = note.PERFUME_NAME" +
                    "FULL OUTER JOIN PERFUMER_LIST list" +
                    "ON list.PERFUME_NAME = info.PERFUME_NAME" +
                        "FULL OUTER JOIN ACCORDS a" +
                        "ON a.PERFUME_NAME = info.PERFUME_NAME",
            nativeQuery = true)
    ArrayList<PerfumeDto> findAllInfo(String perfumeName);





    NoteDto findAllNote(String noteName);









}
