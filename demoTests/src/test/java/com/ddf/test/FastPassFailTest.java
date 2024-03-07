package com.ddf.test;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class FastPassFailTest {

    @Test(testName = "Fast Passing Test", description = "Fast Suite change")
    public void fastPassTest() {
        assertTrue(true);
    }

    @Test(testName = "Fast Failing Test", description = "Fast Suite")
    public void fastFailTest() {
        assertFalse(true);
    }

    @Test
    public void fastPassTest2() {
        assertTrue(true);
    }
}
