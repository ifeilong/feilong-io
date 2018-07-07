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
import static com.feilong.core.date.DateExtensionUtil.formatDuration;
import static java.lang.System.lineSeparator;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class IOReaderUtilTest.
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 */
public class IOReaderParseCancelTest{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(IOReaderParseCancelTest.class);

    @Test
    public void parseAlipay() throws IOException{
        Date beginDate = new Date();
        String fileName = "/Users/feilong/Downloads/no-canceled-1.sql";

        List<String> list = FileUtils.readLines(new File(fileName), UTF8);

        write(list);

        if (LOGGER.isInfoEnabled()){
            LOGGER.info("use time: [{}]", formatDuration(beginDate));
        }

    }

    public void write(List<String> list) throws IOException{
        StringBuilder sb = new StringBuilder();
        sb.append("update t_store_old_so_salesorder ");
        sb.append(lineSeparator());
        sb.append("set logistics_status=10 ");
        sb.append(lineSeparator());
        sb.append("where code in( ");
        sb.append(lineSeparator());

        int i = 0;
        int size = list.size();
        for (String code : list){
            sb.append("'");
            sb.append(code);
            sb.append("'");

            if (i != size - 1){
                sb.append(",");
                sb.append(lineSeparator());
            }

            i++;
        }

        sb.append(");");

        String fileNameOut = "/Users/feilong/Downloads/no-canceled-out.sql";
        File file = new File(fileNameOut);

        FileUtils.write(file, sb, UTF8);
    }
}
