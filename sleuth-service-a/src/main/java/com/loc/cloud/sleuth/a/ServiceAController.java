package com.loc.cloud.sleuth.a;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018/4/24.
 */
@Slf4j
@RestController
public class ServiceAController {

  @Autowired
  private ServiceBClient serviceBClient;

  @RequestMapping("/call")
  public String traced() {
    String traceId = MDC.get("X-B3-TraceId");
    log.info("trace id is {}", traceId);
    String result = serviceBClient.call();
    return "call/" + traceId + ", result/" + result ;
  }
}


@FeignClient("service-b")
interface ServiceBClient {
  @RequestMapping(value = "/call", method = RequestMethod.GET)
  String call();
}

