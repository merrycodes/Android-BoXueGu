package com.merrycodes;

import com.merrycodes.util.MD5Util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void md5(){
        String springMd5Password = MD5Util.springMd5("123");
        System.out.println(springMd5Password);
        assertEquals("77005938398c952c95f8de72a412a4d3", springMd5Password);
    }
}