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

import static com.feilong.core.CharsetType.UTF8;

import java.io.InputStream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.io.FileUtil;
import com.feilong.io.IOReaderUtil;

/**
 * The Class IOReaderUtilTest.
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 */
public class IOReaderUtilTest{

    /** The Constant LOGGER. */
    private static final Logger LOGGER         = LoggerFactory.getLogger(IOReaderUtilTest.class);

    private static final String propertiesPath = "/Users/feilong/work/build.xml";

    /**
     * Gets the file content.
     */
    @Test
    public void testGetFileContent(){
        LOGGER.debug(IOReaderUtil.readFileToString(propertiesPath, UTF8));
    }

    @Test
    public void testGetFileContent2(){
        InputStream inputStream = FileUtil.getFileInputStream(propertiesPath);
        LOGGER.debug(IOReaderUtil.getContent(inputStream, UTF8));
    }
}
