package com.chulman.mvc.restful.controller;

import com.chulman.mvc.restful.data.MemberData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RssAndAtomController {

    @RequestMapping("/atomfeed")
    public String getAtomFeed(Model model){
        model.addAttribute("memberList",MemberData.getMembers());
        return "atomFeedTemplate";
    }

    @RequestMapping("/rssfeed")
    public String getRssFeed(Model model){
        model.addAttribute("memberList",MemberData.getMembers());
        return "rssFeedTemplate";
    }
}
