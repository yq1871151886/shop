package com.fh.controller;

import com.fh.beans.vo.BrandVo;
import com.fh.service.BrandService;
import com.fh.utils.enumbean.LoginCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brands")
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8080")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/{electricid}")
    public LoginCode queryBrandByElectricid(@PathVariable("electricid") String electricid){
        List<BrandVo> brandVos = brandService.queryBrandByElectricid(electricid);
        return LoginCode.success(brandVos);
    }



}
