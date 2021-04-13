package com.demo.service;

import com.demo.dao.TblOneDao;
import com.demo.entity.TblOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class OneService {

    @Autowired
    private TblOneDao oneDao;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public void rmOne(){

        TblOne o = new TblOne();
        o.setId(1);
        o.setName("rm1");
        oneDao.insertSelective(o);

        rm2();
    }

    private void rm2() {
        restTemplate.getForEntity("http://two/rm2", null);
    }
}
