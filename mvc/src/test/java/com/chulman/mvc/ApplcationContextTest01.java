package com.chulman.mvc;

import com.chulman.mvc.sequence.config.SeqGenConfig;
import com.chulman.mvc.sequence.pojo.SequenceGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SeqGenConfig.class})
public class ApplcationContextTest01 {

    @Autowired
    SequenceGenerator sequenceGenerator;
    @Test
    public void declareContext(){
        System.err.println(sequenceGenerator.getSequence());
    }
}
