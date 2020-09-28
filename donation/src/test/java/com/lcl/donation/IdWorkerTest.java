package com.lcl.donation;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IdWorkerTest {
    @Test
    public void Test(){
        long id;
        for(int i = 0;i<5;++i){
            id = IdWorker.getId();
            System.out.println(id);
        }
    }
}
