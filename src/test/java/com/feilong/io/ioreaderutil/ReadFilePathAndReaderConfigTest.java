/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.io.ioreaderutil;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import com.feilong.io.IOReaderUtil;
import com.feilong.io.ReaderConfig;

/**
 * The Class ReadFilePathAndReaderConfigTest.
 */
public class ReadFilePathAndReaderConfigTest{

    /** The file path. */
    private final String filePath = "/Users/feilong/workspace/feilong/feilong-io/src/test/resources/readtest.txt";

    //---------------------------------------------------------------

    /**
     * Test read file path and reader config test null.
     */
    @Test(expected = NullPointerException.class)
    public void testReadFilePathAndReaderConfigTestNull(){
        IOReaderUtil.read((String) null, new ReaderConfig());
    }

    /**
     * Test read file path and reader config test empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReadFilePathAndReaderConfigTestEmpty(){
        IOReaderUtil.read("", new ReaderConfig());
    }

    /**
     * Test read file path and reader config test blank.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReadFilePathAndReaderConfigTestBlank(){
        IOReaderUtil.read(" ", new ReaderConfig());
    }

    /**
     * Test resolver file and reader config is null.
     */
    @Test(expected = NullPointerException.class)
    public void testResolverFileAndReaderConfigIsNull(){
        IOReaderUtil.read("/usr", (ReaderConfig) null);
    }

    //---------------------------------------------------------------

    /**
     * Test resolver reader and reader config test 1.
     */
    @Test
    public void testResolverReaderAndReaderConfigTest1(){
        Set<String> codes = IOReaderUtil.read(filePath, new ReaderConfig("[0-9a-zA-Z\\-]{6,20}"));
        assertThat(codes, allOf(hasItem("123456"), not(hasItem("23456"))));
    }

    /**
     * Test resolver reader and reader config test 2.
     */
    @Test
    public void testResolverReaderAndReaderConfigTest2(){
        Set<String> codes = IOReaderUtil.read(filePath, new ReaderConfig());
        assertThat(codes, allOf(hasItem("123456"), hasItem("23456")));
    }

    // \n\n123456 \nA\n23456

    /**
     * Test resolver reader and reader config test 3.
     */
    @Test
    public void testResolverReaderAndReaderConfigTest3(){
        Set<String> codes = IOReaderUtil.read(filePath, new ReaderConfig(true, false));
        assertThat(codes, allOf(hasItem("123456 "), hasItem("A"), hasItem("23456"), not(hasItem(" "))));
    }

    /**
     * Test resolver reader and reader config test 4.
     */
    @Test
    public void testResolverReaderAndReaderConfigTest4(){
        Set<String> codes = IOReaderUtil.read(filePath, new ReaderConfig(false, false));
        assertThat(codes, allOf(hasItem(" "), hasItem(""), hasItem("123456 "), hasItem("A"), hasItem("23456")));
    }

    /**
     * Test resolver reader and reader config test 5.
     */
    // \n\n123456 \nA\n23456
    @Test
    public void testResolverReaderAndReaderConfigTest5(){
        Set<String> codes = IOReaderUtil.read(filePath, new ReaderConfig(false, true));
        assertThat(codes, allOf(hasItem(""), hasItem(""), hasItem("123456"), hasItem("A"), hasItem("23456")));
    }
}
