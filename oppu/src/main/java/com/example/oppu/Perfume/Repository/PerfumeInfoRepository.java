package com.example.oppu.Perfume.Repository;

import com.example.oppu.Perfume.Entity.PerfumeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PerfumeInfoRepository extends JpaRepository<PerfumeInfo, String> {

    @Query(value = "SELECT info.perfumeName, info.brand FROM PerfumeInfo info" )
    List<PerfumeInfo> findByNameAndBrand();

    PerfumeInfo findByPerfumeName(String name);




}
