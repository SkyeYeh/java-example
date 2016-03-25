package com.skyeyeh.camel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 測試 CamelContext 操作。
 */
public class MainTest {
    Main main;

    @Before
    public void setUp() throws Exception {
        main = new Main();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRun() throws Exception {
        main.run();
    }
}