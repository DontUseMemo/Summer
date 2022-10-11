package com.example.oppu.Perfume.Repository;

import com.example.oppu.Perfume.Entity.PerfumeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.image.renderable.ParameterBlock;
import java.lang.annotation.Native;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PerfumeInfoRepository extends JpaRepository<PerfumeInfo, String> {


    PerfumeInfo findByPerfumeName(String name);


    Page<PerfumeInfo> findByPerfumeNameContainingIgnoreCase(Pageable pageable,String keyword);
    Page<PerfumeInfo> findByBrandContainingIgnoreCase(Pageable pageable , String keyword);






}
