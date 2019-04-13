package com.chulman.mvc.sequence.config;

import com.chulman.mvc.sequence.pojo.SequenceGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeqGenConfig {

    @Bean
    public SequenceGenerator sequenceGenerator() {
        SequenceGenerator seq = new SequenceGenerator();
        seq.setPrefix("s");
        seq.setInitial(100);
        seq.setSuffix("e");
        return seq;
    }
}
