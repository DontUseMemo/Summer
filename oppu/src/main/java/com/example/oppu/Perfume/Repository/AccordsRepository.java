package com.example.oppu.Perfume.Repository;

import com.example.oppu.Perfume.Entity.Accords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccordsRepository extends JpaRepository<Accords, String> {
    List<Accords> findByPerfumeName(String name);
}
