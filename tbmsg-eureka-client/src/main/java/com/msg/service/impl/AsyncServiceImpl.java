package com.msg.service.impl;

import com.msg.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService{

    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);


    /**
     * @return
     * @throws
     * @function 执行异步任务
     * @author luoyhong
     * @date 2019/7/12 15:54
     */
    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() {
        logger.info("start executeAsync");
        try{
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }
        logger.info("end executeAsync");
    }
}
