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
package com.feilong.io;

import static com.feilong.core.CharsetType.UTF8;
import static com.feilong.core.util.CollectionsUtil.removeDuplicate;
import static com.feilong.core.util.SortUtil.sortList;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.json.jsonlib.JsonUtil;

/**
 * The Class IOReaderUtilTest.
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 */
public class IOReaderParseAlipayWhiteIpTest2{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(IOReaderParseAlipayWhiteIpTest2.class);

    @Test
    public void parseAlipay() throws IOException{
        String fileName = "/Users/feilong/Downloads/alipay-ip.txt";
        String fileNameOut = "/Users/feilong/Downloads/alipay-ip-out.txt";
        LOGGER.debug(IOReaderUtil.readFileToString(fileName, UTF8));
        List<String> readLines = FileUtils.readLines(new File(fileName), UTF8);
        LOGGER.debug("size:{},{}", readLines.size(), JsonUtil.format(readLines));

        List<String> list = removeDuplicate(readLines);
        LOGGER.debug("size:{},{}", list.size(), JsonUtil.format(list));

        List<String> list2 = sortList(list);
        LOGGER.debug("size:{},{}", list2.size(), JsonUtil.format(list2));

        FileUtils.writeLines(new File(fileNameOut), list2);
    }
}
