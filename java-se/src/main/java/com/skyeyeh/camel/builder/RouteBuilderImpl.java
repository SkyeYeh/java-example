package com.skyeyeh.camel.builder;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 建立路由類別。使用 org.apache.camel.main.Main 執行。
 */
public class RouteBuilderImpl extends RouteBuilder {
    final Logger logger = LoggerFactory.getLogger(RouteBuilderImpl.class);

    /**
     * 每 1 秒執行一次，印出日誌。
     *
     * @throws Exception 執行錯誤
     */
    @Override
    public void configure() throws Exception {
        from("timer://timer1?period=1000").process((msg) -> {
            logger.info("Processing {}", msg);
        });
    }
}
