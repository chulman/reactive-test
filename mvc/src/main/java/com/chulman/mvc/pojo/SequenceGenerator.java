package com.chulman.mvc.pojo;

import java.util.concurrent.atomic.AtomicInteger;

public class SequenceGenerator {
    private String prefix;
    private String suffix;
    private int initial;
    private final AtomicInteger atomicInteger = new AtomicInteger();

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public String getSequence() {
        StringBuilder builder = new StringBuilder();
        return builder.append(prefix)
                .append(initial)
                .append(atomicInteger.getAndIncrement())
                .append(suffix).toString();
    }
}
