package com.skyeyeh.camel;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by TV6015 on 2016/3/22.
 */
public class MainImplTest {
    MainImpl mainImpl;

    @Before
    public void setUp() throws Exception {
        mainImpl = new MainImpl();
    }

    @Test
    public void testCreateRouteBuilder() throws Exception {
        mainImpl.enableHangupSupport();
        mainImpl.addRouteBuilder(MainImpl.createRouteBuilder());
        mainImpl.run();
    }
}