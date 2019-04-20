package com.chulman.mvc.restful.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Members {

    @XmlElement(name="member")
    private List<Member> mebers = new ArrayList<>();

    public List<Member> getMebers() {
        return mebers;
    }

    public void setMebers(List<Member> mebers) {
        this.mebers = mebers;
    }
}
