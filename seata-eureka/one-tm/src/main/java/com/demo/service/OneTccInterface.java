package com.demo.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface OneTccInterface {

    @TwoPhaseBusinessAction(name = "oneTccAction" ,commitMethod = "oneTccCommit",rollbackMethod = "oneTccRollback")
    void oneTcc(BusinessActionContext businessActionContext);
    boolean oneTccCommit(BusinessActionContext businessActionContext);
    boolean oneTccRollback(BusinessActionContext businessActionContext);
}
