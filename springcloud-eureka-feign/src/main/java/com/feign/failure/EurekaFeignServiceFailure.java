package com.feign.failure;

import com.feign.service.EurekaFeignService;
import org.springframework.stereotype.Service;

/**
 * Created by 3nod on 2019/6/28.
 */
@Service
public class EurekaFeignServiceFailure implements EurekaFeignService {

    @Override
    public String getInfo() {
        String message = "网络繁忙，请稍后再试-_-";
        return message;
    }

    @Override
    public String getTestInfo() {
        String message = "网络繁忙，请稍后再试-_-";
        return message;
    }

}
