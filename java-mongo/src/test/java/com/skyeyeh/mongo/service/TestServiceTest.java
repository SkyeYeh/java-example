package com.skyeyeh.mongo.service;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Skye on 2016/3/28.
 */
public class TestServiceTest {
    TestService testService;

    @Before
    public void setUp() throws Exception {
        testService = new TestService();
    }

    @Test
    public void testInsert() throws Exception {
        testService.insert();
    }
}