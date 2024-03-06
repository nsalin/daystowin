package com.ddf.test;

import com.dff.listeners.TestListener;
import com.dff.listeners.ZephyrTestCase;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class FastPassFailTest2{

    @ZephyrTestCase("TES-T1")
    @Test(testName = "Fast Passing Test", description = "Fast Suite")
    @Description("Checking and marking passing a test")
    public void TES_T1_fastPassTest(){
        assertTrue( true);
    }

    @Test(testName = "Fast Failing Test", description = "Fast Suite")
    @Description("Checking and marking failing a test")
    public void fastFailTest(){
        assertFalse(true);
    }
}
