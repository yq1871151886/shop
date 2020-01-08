package com.fh.scheduling;

import com.fh.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduleeee {

    @Autowired
    private PayService payService;


    @Scheduled(fixedRate = 60000*3)
    public void payLoseTimers(){
        payService.queryPassTimeAndNoPay();
        System.out.println("执行了");
    }


}
