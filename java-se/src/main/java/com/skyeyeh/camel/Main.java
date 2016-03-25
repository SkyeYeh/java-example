package com.skyeyeh.camel;

import com.skyeyeh.camel.builder.RouteBuilderImpl;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用 CamelContext 控制 RouteBuilder。
 */
public class Main {
    final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * 執行 RouteBuilder。
     *
     * @throws Exception 執行例外
     */
    void run() throws Exception {
        final CamelContext camelContext = new DefaultCamelContext();
        // 建立實作之 RouteBuilder 類別。
        camelContext.addRoutes(createRouteBuilder());
        camelContext.setTracing(true);
        camelContext.start();

        // 增加了一個 Java 關閉回撥函數呼叫 context 的 stop()。
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    camelContext.stop();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // 執行緒阻塞，防止程式退出。
        waitForStop();
    }

    /**
     * 建立實作之 RouteBuilder 類別。
     *
     * @return 實作之 RouteBuilder 類別
     */
    private RouteBuilder createRouteBuilder() {
        return new RouteBuilderImpl();
    }

    /**
     * 執行緒阻塞，防止程式退出。
     */
    private void waitForStop() {
        while (true) {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
