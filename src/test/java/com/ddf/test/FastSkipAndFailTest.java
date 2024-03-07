package com.ddf.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FastSkipAndFailTest {

    @Test(testName = "Fast Pass", description = "Fast Suite")
    public void fastPassTest(){
        int a = 3;
        int b = 2;
        int resultExpected = 5;
        int actualResult = a + b;
        Assert.assertEquals(actualResult, resultExpected, "The result is incorrect");
    }
    @Test(testName = "This is FastPass 1", description = "Fast Suite")
    public void fastPassTest1 (){
        System.out.println("This is fast Skip");
    }

    //@Test(testName = "This is Fast Pass 2", description = "Fast suite")
    public void fastPassTest2(){
        String name = "Innovation ";
        String week = "Week";
        String expected = name + week;
        Assert.assertEquals("Innovation-Week", expected, "Result Matches");
    }
}
