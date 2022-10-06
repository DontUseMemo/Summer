package com.example.oppu.Perfume.Repository;

import com.example.oppu.Perfume.Entity.PerfumerList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface PerfumerRepository extends JpaRepository<PerfumerList,String> {

List<PerfumerList> findByPerfumeName(String name);


}
