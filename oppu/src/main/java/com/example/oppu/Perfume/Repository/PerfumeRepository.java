//package com.example.oppu.Perfume.Repository;
//
//import com.example.oppu.Perfume.DTO.NoteDto;
//import com.example.oppu.Perfume.DTO.PerfumeDto;
//import com.example.oppu.Perfume.Entity.PerfumeInfo;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//public interface PerfumeRepository extends JpaRepository<PerfumeDto , String> {
//
//    @Query(value = "SELECT * " +
//            "FROM PERFUME_INFO info" +
//                "FULL OUTER JOIN PERFUME_NOTE note" +
//                "ON info.PERFUME_NAME = note.PERFUME_NAME" +
//                    "FULL OUTER JOIN PERFUMER_LIST list" +
//                    "ON list.PERFUME_NAME = info.PERFUME_NAME" +
//                        "FULL OUTER JOIN ACCORDS a" +
//                        "ON a.PERFUME_NAME = info.PERFUME_NAME",
//            nativeQuery = true)
//    List<PerfumeDto> perfumeInfoAll();
//
//    List<PerfumeDto> findPerfumeDtoByPerfumeName(String name);
//
//
//}
