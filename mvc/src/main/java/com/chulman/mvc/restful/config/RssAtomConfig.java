package com.chulman.mvc.restful.config;

import com.chulman.mvc.restful.vo.Member;
import com.chulman.mvc.restful.vo.Members;
import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Item;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class RssAtomConfig {
    @Bean
    public AtomFeedView atomFeedTemplate(){
        return new AtomFeedView();
    }

    @Bean
    public RssFeedView rssFeedTemplate(){
        return new RssFeedView();
    }
}



class AtomFeedView extends AbstractAtomFeedView{

    // 메타데이터에 대한 할당
    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Feed feed, HttpServletRequest request) {
        feed.setId("tag:member.org");
        feed.setTitle("Member Test");

//        Members memberList = (Members)model.get("memberList");
        feed.setUpdated(new Date());
    }

    // 메타데이터에 대한 처리
    @Override
    protected List<Entry> buildFeedEntries(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Member> memberList = ((Members)model.get("memberList")).getMebers();
        return memberList.stream().map(this::toEntry).collect(Collectors.toList());
    }

    private Entry toEntry(Member member){
        Entry memberEntry = new Entry();
        memberEntry.setTitle("member name:"  +member.getName());
        memberEntry.setCreated(new Date());

        Content summary = new Content();
        summary.setValue(String.format("%s : %s", member.getName(),member.getPhone()));
        memberEntry.setSummary(summary);
        return memberEntry;
    }
}



class RssFeedView extends AbstractRssFeedView {

    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Channel feed, HttpServletRequest request) {
        feed.setTitle("RSS Member Test");
        feed.setDescription("this is test RSS FEED with Member");
        feed.setLink("member.org");
        feed.setUri("http://github.com/chulman");
        super.buildFeedMetadata(model, feed, request);
    }

    @Override
    protected List<Item> buildFeedItems(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Member> memberList = ((Members)model.get("memberList")).getMebers();
        return memberList.stream().map(this::toItem).collect(Collectors.toList());

    }

    private Item toItem(Member member){
        Item item = new Item();
        item.setAuthor(member.getName());
        item.setTitle("members RSS..");
        item.setComments("ps: contact to "  + member.getPhone() );
        return item;
    }


}