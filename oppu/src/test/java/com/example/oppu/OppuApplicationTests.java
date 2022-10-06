package com.example.oppu;

import com.example.oppu.Perfume.Entity.PerfumerList;
import com.example.oppu.Perfume.PerfumeService;
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
    private PerfumeService perfumeService;
    private PerfumerRepository perfumerRepository;

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
    void contextLoads() {
    }

}
