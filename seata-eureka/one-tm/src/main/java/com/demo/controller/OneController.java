package com.demo.controller;

import com.demo.service.OneService;
import com.demo.service.OneTccInterface;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class OneController {

    @Autowired
    OneService rmOneService;

    @GetMapping("/one")
    @GlobalTransactional(rollbackFor = Exception.class)
    public String one() throws InterruptedException {
        rmOneService.rmOne();
//        TimeUnit.MINUTES.sleep(1);
//        System.out.println(1/0);
        return "success";
    }


    @Autowired
    private OneTccInterface oneTccInterface;

    @GetMapping("/one-tcc")
    @GlobalTransactional(rollbackFor = Exception.class)
    public String oneTcc() throws InterruptedException {
        oneTccInterface.oneTcc(null);
        return "success";
    }
}
