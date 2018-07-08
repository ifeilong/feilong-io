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
package com.feilong.io.iowriteutil;

import static com.feilong.core.CharsetType.UTF8;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.feilong.core.UncheckedIOException;
import com.feilong.io.IOWriteUtil;

/**
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 1.11.4
 */
public class WriteDirectoryAndFileTest{

    @Test
    public void test(){
        IOWriteUtil.write(getInputStream(), "/Users/feilong/feilong/logs/", "a.txt");
    }

    @Test
    public void test1(){
        IOWriteUtil.write(getInputStream(), "/Users/feilong/feilong/logs/", "a/a.txt");
    }

    //---------------------------------------------------------------

    @Test(expected = NullPointerException.class)
    public void testInputStreamNull(){
        IOWriteUtil.write(null, "/Users/feilong/feilong/logs/", "a.txt");
    }

    //---------------------------------------------------------------

    @Test(expected = NullPointerException.class)
    public void testWriteDirectoryAndFileTestNull1() throws IOException{
        IOWriteUtil.write(getInputStream(), null, "a.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWriteDirectoryAndFileTestEmpty(){
        IOWriteUtil.write(getInputStream(), "", "a.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWriteDirectoryAndFileTestBlank(){
        IOWriteUtil.write(getInputStream(), " ", "a.txt");
    }
    //---------------------------------------------------------------

    @Test(expected = NullPointerException.class)
    public void testWriteDirectoryAndFileTestNull11(){
        IOWriteUtil.write(getInputStream(), "/Users/feilong/feilong/logs/", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWriteDirectoryAndFileTestEmpty1(){
        IOWriteUtil.write(getInputStream(), "/Users/feilong/feilong/logs/", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWriteDirectoryAndFileTestBlank1(){
        IOWriteUtil.write(getInputStream(), "/Users/feilong/feilong/logs/", " ");
    }

    //---------------------------------------------------------------

    /**
     * @return
     * @throws IOException
     * @since 1.11.4
     */
    private static InputStream getInputStream(){
        try{
            return IOUtils.toInputStream("feilong 我爱你", UTF8);
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

}
