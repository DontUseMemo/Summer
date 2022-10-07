package com.example.oppu.Perfume.Repository;

import com.example.oppu.Perfume.Entity.AllNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllNoteRepository extends JpaRepository<AllNote,String> {

    AllNote findByNoteName(String name);

}
