package com.example.oppu.Perfume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;

public interface PerfumeRepository {

    @Query(value = "SELECT * " +
            "FROM " +
            "PerfumeInfo info," +
            "PerfumerList Pmer" +
            "PerfumeNote note" +
            "Accords Ac " +
            "FULL OUTER JOIN PerfumerList" +
            "ON PerfumeInfo.perfumeName = PerfumerList.PerfumeName",
          nativeQuery = true)
    PerfumeDto findAllPerfumeInfo(String perfumeName);





}
