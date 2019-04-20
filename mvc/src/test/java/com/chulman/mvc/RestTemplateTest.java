package com.chulman.mvc;

import com.chulman.mvc.restful.RestApplication;
import com.chulman.mvc.restful.vo.Member;
import com.chulman.mvc.restful.vo.Members;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestApplication.class)
public class RestTemplateTest {
    RestTemplate restTemplate;

    @Before
    public void setRestTemplate(){
        restTemplate = new RestTemplate();
    }

    @Test
    public void getMembers(){
        String uri = "http://localhost:8080/members";
        Members members = restTemplate.getForObject(uri, Members.class);

        Assert.assertEquals(members.getMebers().size(),3);
    }
    @Test
    public void getMember(){
        String uri = "http://localhost:8080/member/{memberId}";

        Map<String,String> params = new HashMap<>();
        params.put("memberId","0");

        // add placeHolder
        Member member = restTemplate.getForObject(uri, Member.class, params);

        Assert.assertEquals(member.getName(),"choi");
        Assert.assertEquals(member.getEmail(),"choi@naver.com");
    }
}
