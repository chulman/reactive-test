package com.chulman.mvc.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("SequenceDao")
public class SequenceDao {

    @Autowired
    SequenceGenerator sequenceGenerator;

    public String getSequnce(){
        return sequenceGenerator.getSequence();
    }
}
