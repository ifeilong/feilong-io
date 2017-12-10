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

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NebulaReleaseNoteConvertTest{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(NebulaReleaseNoteConvertTest.class);

    @Test
    public void test(){
        String content = IOReaderUtil.readFileToString("/Users/feilong/Downloads/nebula release log.txt", UTF8);

        Map<String, String> map = new LinkedHashMap<>();
        map.put(
                        "<li>\\[<a href='http://jira.baozun.cn/browse/(NB-.*?)'>(NB-.*?)</a>\\] -       ",
                        ". http://jira.baozun.cn/browse/$1[$2] -");
        map.put("</li>\\n", "");
        map.put("<ul>\\n", "");
        map.put("</ul>\\n", "");

        //---------------------------------------------------------------

        String replaceAll = content;

        for (Map.Entry<String, String> entry : map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();

            replaceAll = replaceAll.replaceAll(key, value);
        }

        LOGGER.debug(replaceAll);
    }

}
