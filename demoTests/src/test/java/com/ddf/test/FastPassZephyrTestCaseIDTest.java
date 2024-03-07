package com.ddf.test;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class FastPassZephyrTestCaseIDTest {
    @Test(testName = "Fast Passing Test", description = "The prefix is a test case known id")
    public void TES_T1_fastPassKnownTestCaseIdTest() {
        assertTrue(true);
    }
}
