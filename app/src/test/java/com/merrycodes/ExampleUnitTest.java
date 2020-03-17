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
        String md5Password = MD5Util.md5("123");
        System.out.println(md5Password);
        assertNotEquals(null, md5Password);
        String springMd5Password = MD5Util.springMd5("123");
        System.out.println(springMd5Password);
        assertNotEquals(null, springMd5Password);
    }
}