package com.nivelle.guide;

import com.nivelle.guide.java2e.core.Man;
import com.nivelle.guide.springboot.configbean.LearnConfig;
import com.nivelle.guide.springboot.mapper.ActivityPvMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    LearnConfig learnConfig;
    @Autowired
    ActivityPvMapper activityPvMapper;

    @RequestMapping("/config")
    public String config() {

        String desc = learnConfig.getDesc();

        Man man = new Man(1, "nivelle", 100);

        System.out.println(desc);

        System.out.println(man.getScore() + man.getName() + man.getAge());

        return "hello world my name is " + desc;
    }

    @RequestMapping("/extends")
    @ResponseBody
    public String hello() {

        Man man = new Man(1, "nivelle", 100);
        System.out.println(man.getScore() + man.getName() + man.getAge());
        return "class extends name is:" + man.getName() +" "+"score is: "+ man.getScore();
    }

}
