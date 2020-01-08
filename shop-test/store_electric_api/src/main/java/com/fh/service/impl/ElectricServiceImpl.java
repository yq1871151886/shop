package com.fh.service.impl;

import com.fh.beans.vo.ElectricVo;
import com.fh.dao.ElectricDao;
import com.fh.service.ElectricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ElectricServiceImpl implements ElectricService {
    @Autowired
    private ElectricDao electricDao;

    public List<Map<String,Object>> queryElectric(){
        List<ElectricVo> electricVos = electricDao.queryElectricAll();
            return getEletrics("0",electricVos);
    }

    private List<Map<String,Object>> getEletrics(String pid,List<ElectricVo> electricVos){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        electricVos.forEach(ele->{
            Map<String,Object> map = null;
            if (pid.equals(ele.getPid())){
                map=new HashMap<String, Object>();
                map.put("id",ele.getId());
                map.put("pid",ele.getPid());
                map.put("name",ele.getName());
                map.put("children",getEletrics(ele.getId(),electricVos));
            }
            if (map!=null){
                list.add(map);
            }
        });
            return list;
    }




}
