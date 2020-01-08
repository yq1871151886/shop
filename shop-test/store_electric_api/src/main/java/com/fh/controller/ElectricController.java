package com.fh.controller;

import com.fh.service.ElectricService;
import com.fh.utils.enumbean.LoginCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("electrics")
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8080")
public class ElectricController {

    @Autowired
    private ElectricService electricService;



    @GetMapping
    public LoginCode queryEletricAll(){
        List<Map<String, Object>> electrics = electricService.queryElectric();
        return LoginCode.success(electrics);
    }



}
