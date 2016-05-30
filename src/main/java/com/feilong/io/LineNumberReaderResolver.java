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

/**
 * 定义对 {@link java.io.LineNumberReader} 的解析.
 *
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 1.4.1
 */
public interface LineNumberReaderResolver{

    /**
     * 每行读取的时候的操作.
     *
     * @param lineNumber
     *            行号,默认从0开始,但是此处由于在循环内部调用,即上面已经开始读取了,那么此处值最小值是1( see {@link java.io.LineNumberReader#read()}), see
     *            {@link java.io.LineNumberReader#getLineNumber()}
     * @param line
     *            the line {@link java.io.LineNumberReader#readLine()}
     * @return 如果 return ture ,will go on loop; else will break loop;
     * @see java.io.LineNumberReader#getLineNumber()
     * @see java.io.LineNumberReader#readLine()
     * @see java.io.LineNumberReader#read()
     */
    boolean excute(int lineNumber,String line);
}
