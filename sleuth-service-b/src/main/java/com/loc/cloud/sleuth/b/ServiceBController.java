package com.loc.cloud.sleuth.b;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018/4/24.
 */
@Slf4j
@RestController
public class ServiceBController {

  @RequestMapping("/call")
  public String traced() throws Exception {
    Thread.sleep(600);
    String traceId = MDC.get("X-B3-TraceId");
    log.info("trace id is {}", traceId);
    return "call/" + traceId;
  }
}
