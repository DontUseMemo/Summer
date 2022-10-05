package com.example.oppu.Perfume.Repository;

import com.example.oppu.Perfume.Entity.PerfumeNote;
import com.example.oppu.Perfume.Entity.PerfumerList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerfumeNoteRepository  extends JpaRepository<PerfumeNote,String> {

    @Query(value = "SELECT pn FROM PerfumeNote pn WHERE pn.noteGroup IN ('Top Notes') AND pn.perfumeName = :name" )
    List<PerfumeNote> findTop(String name);

    @Query(value = "SELECT pn FROM PerfumeNote pn WHERE pn.noteGroup IN ('Middle Notes') AND pn.perfumeName = :name" )
    List<PerfumeNote> findMiddle(String name);

    @Query(value = "SELECT pn FROM PerfumeNote pn WHERE pn.noteGroup IN ('Base Notes') AND pn.perfumeName = :name" )
    List<PerfumeNote> findBase(String name);


}
