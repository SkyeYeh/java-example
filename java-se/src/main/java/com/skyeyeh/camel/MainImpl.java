package com.skyeyeh.camel;

import com.skyeyeh.camel.builder.FileRouteBuilderImpl;
import com.skyeyeh.camel.builder.RouteBuilderImpl;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by TV6015 on 2016/3/22.
 */
public class MainImpl extends org.apache.camel.main.Main {
    final Logger logger = LoggerFactory.getLogger(MainImpl.class);

    static public RouteBuilder createRouteBuilder() {
        return new FileRouteBuilderImpl();
    }
}
