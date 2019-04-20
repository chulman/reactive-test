package com.chulman.mvc.restful.controller;

import com.chulman.mvc.restful.data.MemberData;
import com.chulman.mvc.restful.vo.Member;
import com.chulman.mvc.restful.vo.Members;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestMemberController {

    /**
     * Marshalling View Resolver를 이용하여 데이터를 보여주는 방법
     */
//    @GetMapping("/members")
//    public String getXmlMembers(Model model){
//        model.addAttribute("members",MemberData.getMembers());
//        return "memberTemplate";
//    }
    @GetMapping(value = "/members", produces= MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public Members getXmlMembers() {
        return MemberData.getMembers();
    }

    @GetMapping(value = "/members", produces= MediaType.APPLICATION_JSON_VALUE)
        public String getJsonMembers(Model model) {
        model.addAttribute("members",MemberData.getMembers());
        return "jsonMemberTemplate";
    }


    /**
     *
     * ResponseBody는 메서드 실행결과를 응답 본문으로 취급.
     * xml 형식으로 보이려면, Jaxb2RootElementHttpMessageConverter 클래스가 마샬링을 수행
     * {} , *, ...
     */
//    @GetMapping("/member/{memberId}")
//    @ResponseBody
//    public Member getMember(@PathVariable("memberId") int memberId){
//        return  MemberData.getMembers(memberId);
//    }

    /**
     * ResponseEntity를 통해 없으면 404로, 클라이언트에게 알려주기
     */
    @GetMapping("/member/{memberId}")
    @ResponseBody
    public ResponseEntity<Member> getMember(@PathVariable("memberId") int memberId) {
        Member member = null;
        try {
            member = MemberData.getMembers(memberId);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

}
