package com.demo.service;

import com.alibaba.fastjson.JSON;
import com.demo.dao.TblOneDao;
import com.demo.entity.TblOne;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


@Service
public class OneTccInterfaceImpl implements OneTccInterface{

    @Autowired
    private TblOneDao oneDao;


    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    @Override
    public void oneTcc(BusinessActionContext businessActionContext) {
        System.out.println(String.format("oneTcc 【%s】",JSON.toJSONString(businessActionContext)));
        TblOne o = new TblOne();
        o.setName("oneTcc1");
        oneDao.insertSelective(o);

        rm2Tcc();
    }

    @Transactional
    @Override
    public boolean oneTccCommit(BusinessActionContext businessActionContext) {

        System.out.println(String.format("oneTccCommit 【%s】",JSON.toJSONString(businessActionContext)));
        return true;
    }

    @Transactional
    @Override
    public boolean oneTccRollback(BusinessActionContext businessActionContext) {
        System.out.println(String.format("oneTccRollback 【%s】",JSON.toJSONString(businessActionContext)));
        return true;
    }


    private void rm2Tcc() {
        restTemplate.getForEntity("http://two/rm2-tcc", null);
    }
}
