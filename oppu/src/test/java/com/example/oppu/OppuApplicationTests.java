package com.example.oppu;

import com.example.oppu.Perfume.Entity.Accords;
import com.example.oppu.Perfume.Entity.AllNote;
import com.example.oppu.Perfume.Entity.PerfumerList;
import com.example.oppu.Perfume.PerfumeService;
import com.example.oppu.Perfume.Repository.AccordsRepository;
import com.example.oppu.Perfume.Repository.AllNoteRepository;
import com.example.oppu.Perfume.Repository.PerfumeInfoRepository;

import com.example.oppu.Perfume.Repository.PerfumerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OppuApplicationTests {
    @Autowired
    private PerfumeInfoRepository perfumeInfoRepository;
    @Autowired
    private PerfumeService perfumeService;
    @Autowired
    private PerfumerRepository perfumerRepository;

    @Autowired
    private AllNoteRepository allNoteRepository;

    @Autowired
    AccordsRepository accordsRepository;

    @Test
    public void test(){
        List<PerfumerList> a  = perfumeService.getPerfumerList("Acqua di Gio");
        System.out.println(a);

    }

    @Test
    public void testA(){
        List<PerfumerList> a = perfumerRepository.findAll();
        System.out.println(a);
    }

    @Test
    public void testAcc(){
        List<Accords> a = accordsRepository.findByPerfumeName("Baccarat Rouge 540");
        System.out.println("++++++++==================");
        System.out.println(a.toString());
        System.out.println("++++++++==================");

    }

    @Test
    void contextLoads() {
    }

}
