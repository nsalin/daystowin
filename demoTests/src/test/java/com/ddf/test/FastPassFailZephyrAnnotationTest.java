package com.ddf.test;

import com.dff.listeners.TestListener;
import com.ind.dw.zephyr.annotation.ZephyrTestCase;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Listeners({TestListener.class})
public class FastPassFailZephyrAnnotationTest {
    @ZephyrTestCase("TES-T400")
    @Test(testName = "Fast Passing Test", description = "Fast pass with known testcaseid as annotation")
    public void fastPassKnownTestCaseIdTest() {
        assertTrue(true);
    }

    @ZephyrTestCase("TES-T401")
    @Test(testName = "Fast Passing Test", description = "Fast pass with not known testcaseid as annotation")
    public void fastPassNotKnownTestCaseIdTest() {
        assertTrue(true);
    }

}
