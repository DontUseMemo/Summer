package com.example.oppu;

import com.example.oppu.Perfume.PerfumeService;
import com.example.oppu.Perfume.Repository.PerfumeInfoRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OppuApplicationTests {
    @Autowired
    private PerfumeInfoRepository perfumeInfoRepository;
    private PerfumeService perfumeService;

    @Test
    public void test(){


    }

    @Test
    void contextLoads() {
    }

}
