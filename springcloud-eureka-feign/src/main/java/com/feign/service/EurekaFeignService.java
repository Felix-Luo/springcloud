package com.feign.service;

import com.feign.failure.EurekaFeignServiceFailure;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 3nod on 2019/6/28.
 */
//@FeignClient(value = "eureka-client") // 调用的服务的名称
//@FeignClient(value = "EUREKA-CLIENT", fallback = EurekaFeignServiceFailure.class) // 调用的服务的名称
@FeignClient(value = "TBMSG-EUREKA-CLIENT111", fallback = EurekaFeignServiceFailure.class) // 调用的服务的名称
public interface EurekaFeignService {

    @RequestMapping(value = "/info")
    String getInfo();
    @RequestMapping(value = "/testInfo")
    String getTestInfo();

}
