package com.chulman.mvc.config;

import com.chulman.mvc.pojo.SequenceGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeqGenConfig {

    //@Bean
    @Bean
    public SequenceGenerator sequenceGenerator() {
        SequenceGenerator seq = new SequenceGenerator();
        seq.setPrefix("s");
        seq.setInitial(100);
        seq.setSuffix("e");
        return seq;
    }
}
