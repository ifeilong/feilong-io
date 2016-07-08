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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.core.CharsetType;
import com.feilong.core.UncheckedIOException;
import com.feilong.core.Validator;

/**
 * 读文件.
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 1.0.6
 */
public final class IOReaderUtil{

    /** The Constant log. */
    private static final Logger LOGGER               = LoggerFactory.getLogger(IOReaderUtil.class);

    /** 默认编码. */
    private static final String DEFAULT_CHARSET_NAME = CharsetType.UTF8;

    /** Don't let anyone instantiate this class. */
    private IOReaderUtil(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * 获得 file content.
     *
     * @param filePath
     *            the path
     * @param charsetName
     *            字符编码,如果是isNullOrEmpty,那么默认使用 {@link CharsetType#UTF8}
     * @return the file content
     * @see org.apache.commons.io.FileUtils#readFileToString(File, Charset)
     * @see #readFileToString(File, String)
     * @since 1.0.8
     */
    public static String readFileToString(String filePath,String charsetName){
        File file = new File(filePath);
        return readFileToString(file, charsetName);
    }

    /**
     * 读取文件内容.
     *
     * @param file
     *            文件
     * @param charsetName
     *            字符编码,如果是isNullOrEmpty,那么默认使用 {@link CharsetType#UTF8}
     * @return the file content
     * @see org.apache.commons.io.FileUtils#readFileToString(File, Charset)
     * @since 1.5.3
     */
    public static String readFileToString(File file,String charsetName){
        Validate.notNull(file, "file can't be null!");

        FileInputStream fileInputStream = null;
        try{
            fileInputStream = new FileInputStream(file);
            return getContent(fileInputStream, charsetName);
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }finally{
            // 用完关闭流 是个好习惯,^_^
            IOUtils.closeQuietly(fileInputStream);
        }
    }

    /**
     * 读取文件内容.
     *
     * @param fileInputStream
     *            the file input stream
     * @param charsetName
     *            字符编码,如果是isNullOrEmpty,那么默认使用 {@link CharsetType#UTF8}
     * @return the file content
     * @see org.apache.commons.io.FileUtils#readFileToString(File, Charset)
     * @since 1.5.3
     */
    public static String getContent(FileInputStream fileInputStream,String charsetName){
        Validate.notNull(fileInputStream, "inputStream can't be null!");

        // 分配新的直接字节缓冲区
        final int capacity = 186140;
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(capacity);
        StringBuilder sb = new StringBuilder(capacity);

        try{
            // 用于读取、写入、映射和操作文件的通道.
            FileChannel fileChannel = fileInputStream.getChannel();
            String useCharsetName = Validator.isNullOrEmpty(charsetName) ? DEFAULT_CHARSET_NAME : charsetName;
            Charset charset = Charset.forName(useCharsetName);
            while (fileChannel.read(byteBuffer) != org.apache.commons.io.IOUtils.EOF){
                // 反转此缓冲区
                byteBuffer.flip();
                CharBuffer charBuffer = charset.decode(byteBuffer);
                sb.append(charBuffer.toString());
                byteBuffer.clear();
            }
            return sb.toString();
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }finally{
            // 用完关闭流 是个好习惯,^_^
            IOUtils.closeQuietly(fileInputStream);
        }
    }

    /**
     * 读取文件内容.
     *
     * @param inputStream
     *            the input stream
     * @param charsetName
     *            字符编码,如果是isNullOrEmpty,那么默认使用 {@link CharsetType#UTF8}
     * @return the file content
     * @see org.apache.commons.io.IOUtils#toString(InputStream, String)
     * @see InputStreamUtil#toString(InputStream, String)
     * @since 1.5.3
     */
    public static String getContent(InputStream inputStream,String charsetName){
        Validate.notNull(inputStream, "inputStream can't be null!");
        try{
            return IOUtils.toString(inputStream, charsetName);
        }catch (IOException e){
            LOGGER.error("", e);
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 使用 {@link LineNumberReaderResolver}解析文件.
     *
     * @param filePath
     *            the file path
     * @param lineNumberReaderResolver
     *            the line number reader resolver
     * @since 1.4.1
     */
    public static void resolverFile(String filePath,LineNumberReaderResolver lineNumberReaderResolver){
        try{
            Reader reader = new FileReader(filePath);
            resolverFile(reader, lineNumberReaderResolver);
        }catch (FileNotFoundException e){
            LOGGER.error("", e);
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 使用 {@link LineNumberReaderResolver}解析文件.
     *
     * @param file
     *            the file
     * @param lineNumberReaderResolver
     *            the line number reader resolver
     * @since 1.4.1
     */
    public static void resolverFile(File file,LineNumberReaderResolver lineNumberReaderResolver){
        try{
            Reader reader = new FileReader(file);
            resolverFile(reader, lineNumberReaderResolver);
        }catch (FileNotFoundException e){
            LOGGER.error("", e);
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 使用 {@link LineNumberReaderResolver}解析 {@link Reader}.
     * 
     * <h3>如果你以前这么写代码:</h3>
     * 
     * <blockquote>
     * 
     * <pre class="code">
     * 
     * InputStreamReader read = new InputStreamReader(resourceAsStream, ENCODING);
     * try{
     *     Set{@code <String>} set = new HashSet{@code <String>}();
     *     log.info("io流开启了");
     *     BufferedReader bufferedReader = new BufferedReader(read);
     *     String txt = null;
     *     while ((txt = bufferedReader.readLine()) != null){ // 读取文件,将文件内容放入到set中
     *         txt = txt.trim();// 忽略前面前后空格
     *         txt = txt.replace(" ", "");// 文中过滤空格
     *         set.add(txt);
     *     }
     * }catch (Exception e){
     *     log.error(e.getMessage());
     * }finally{
     *     log.info("io流关闭了");
     *     read.close(); // 关闭文件流
     * }
     * return set;
     * 
     * </pre>
     * 
     * 现在可以重构为:
     * 
     * <pre class="code">
     * InputStreamReader read = new InputStreamReader(resourceAsStream, ENCODING);
     * 
     * final Set{@code <String>} set = new HashSet{@code <String>}();
     * 
     * log.info("io流开启了");
     * IOReaderUtil.resolverFile(read, new LineNumberReaderResolver(){
     * 
     *     {@code @Override}
     *     public boolean excute(int lineNumber,String line){
     *         line = line.trim();// 忽略前面前后空格
     *         line = line.replace(" ", "");// 文中过滤空格
     *         set.add(line);// 读取文件,将文件内容放入到set中
     *         return true;
     *     }
     * });
     * return set;
     * </pre>
     * 
     * </blockquote>
     *
     * @param reader
     *            the reader
     * @param lineNumberReaderResolver
     *            the line number reader resolver
     * @since 1.4.1
     */
    public static void resolverFile(Reader reader,LineNumberReaderResolver lineNumberReaderResolver){
        LineNumberReader lineNumberReader = new LineNumberReader(reader);
        try{
            String line = null;
            while ((line = lineNumberReader.readLine()) != null){
                int lineNumber = lineNumberReader.getLineNumber();
                boolean result = lineNumberReaderResolver.excute(lineNumber, line);

                if (!result){
                    break;
                }
            }
        }catch (IOException e){
            LOGGER.error("", e);
            throw new UncheckedIOException(e);
        }finally{
            IOUtils.closeQuietly(lineNumberReader);
            IOUtils.closeQuietly(reader);
        }
    }
}