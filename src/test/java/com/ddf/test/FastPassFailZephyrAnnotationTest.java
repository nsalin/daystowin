package com.ddf.test;

import com.dff.listeners.TestListener;
import com.dff.listeners.ZephyrTestCase;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Listeners({TestListener.class})
public class FastPassFailZephyrAnnotationTest {
    @ZephyrTestCase("TES-T1")
    @Test(testName = "Fast Passing Test", description = "Fast pass with known testcaseid as annotation")
    public void fastPassKnownTestCaseIdTest() {
        assertTrue(true);
    }

    @ZephyrTestCase("TES-T500")
    @Test(testName = "Fast Passing Test", description = "Fast pass with not known testcaseid as annotation")
    public void fastPassNotKnownTestCaseIdTest() {
        assertTrue(true);
    }

}
