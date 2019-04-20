package com.chulman.mvc.restful.data;

import com.chulman.mvc.restful.vo.Member;
import com.chulman.mvc.restful.vo.Members;

import java.util.ArrayList;
import java.util.List;

public class MemberData {
    private final static List<Member> memberList = new ArrayList<>();

    public static Members getMembers() {
        Members members = new Members();
        members.setMebers(getMemberLists());
        return members;
    }


    public static Member getMembers(int id) throws Exception {
        return getMemberLists().get(id);
    }

    public static List<Member> getMemberLists() {
        if (memberList.size() == 0) {
            Member member = new Member("choi", "010-222-22", "choi@naver.com");
            Member member2 = new Member("kim", "010-3333-22", "kim@naver.com");
            Member member3 = new Member("park", "010-2444-22", "lee@naver.com");
            memberList.add(member);
            memberList.add(member2);
            memberList.add(member3);
        }
        return memberList;
    }


}
