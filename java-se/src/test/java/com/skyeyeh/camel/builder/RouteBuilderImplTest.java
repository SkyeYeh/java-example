package com.skyeyeh.camel.builder;

import org.apache.camel.main.Main;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by TV6015 on 2016/3/21.
 */
public class RouteBuilderImplTest {
    Main main;

    @Before
    public void setUp() throws Exception {
        main = new Main();
    }

    @Test
    public void testConfigure() throws Exception {
        main.run(new String[]{"-r com.skyeyeh.camel.builder.RouteBuilderImpl"});
    }
}