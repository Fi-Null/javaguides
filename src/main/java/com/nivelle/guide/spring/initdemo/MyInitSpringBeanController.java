package com.nivelle.guide.spring.initdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/springBean")
public class MyInitSpringBeanController {

    @Autowired
    InitSpringBean initSpringBean;

    @RequestMapping("/init")
    @ResponseBody
    public Object myInitSpringBean() {
        return initSpringBean.getActivities();
    }
}
