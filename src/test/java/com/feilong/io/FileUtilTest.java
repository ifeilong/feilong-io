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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.core.lang.ClassLoaderUtil;
import com.feilong.core.net.URLUtil;
import com.feilong.json.jsonlib.JsonUtil;

/**
 * The Class FileUtilTest.
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 */
public class FileUtilTest{

    /** The Constant LOGGER. */
    private static final Logger LOGGER  = LoggerFactory.getLogger(FileUtilTest.class);

    /** The string. */
    private final String        fString = "/home/webuser/nike_int/johnData/${date}/nikeid_pix_${typeName}.csv";

    /**
     * Test get content length.
     * 
     * @throws IOException
     */
    @Test
    public void testGetContentLength() throws IOException{
        URL url = URLUtil.toURL("http://www.jinbaowang.cn/images//20110722/096718c3d1c9b4a1.jpg");
        URLConnection urlConnection = url.openConnection();
        int contentLength = urlConnection.getContentLength();
        LOGGER.debug(FileUtil.formatSize(contentLength));
    }

    /**
     * TestFileUtilTest.
     * 
     * @throws IOException
     */
    @Test
    public void testFileUtilTest() throws IOException{
        URL url = URLUtil.toURL("http://localhost:8080/TestHttpURLConnectionPro/index.jsp");
        url.openConnection();
    }

    /**
     * Test get p.
     */
    @Test
    public void testGetP(){
        File file = new File(fString);
        LOGGER.debug(file.getAbsolutePath());
        LOGGER.debug(file.getParent());
    }

    /**
     * Test get p1.
     */
    @Test
    public void testGetP1(){
        URL resource = ClassLoaderUtil.getResource("org/apache/commons/collections4/map");
        URI uri = URLUtil.toURI(resource);
        File esapiDirectory = new File(uri);
        LOGGER.debug(esapiDirectory.getAbsolutePath());
    }

    /**
     * List files.
     * 
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void listFiles() throws IOException{
        String localPath = "E:\\DataCommon\\test";
        // 读取localPath目录下的全部properties文件
        File file = new File(localPath);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++){
            LOGGER.debug("File:" + files[i].getCanonicalPath());
        }
    }

    /**
     * Checks if is empty directory.
     */
    @Test
    public void isEmptyDirectory(){
        // 不存在的文件
        try{
            FileUtil.isEmptyDirectory("E:\\test\\1\\2011-07-07\\test\\1\\2011-07-07");
            fail();
        }catch (IllegalArgumentException e){
            assertTrue(true);
        }

        // 文件
        try{
            FileUtil.isEmptyDirectory("E:\\1.txt");
            fail();
        }catch (IllegalArgumentException e){
            assertTrue(true);
        }

        // 非空目录
        assertEquals(false, FileUtil.isEmptyDirectory("E:\\Workspaces"));

        // 正确的 空目录
        assertEquals(true, FileUtil.isEmptyDirectory("E:\\empty"));
    }

    /**
     * Test is empty directory1.
     */
    @Test
    public void testIsEmptyDirectory1(){
        // 正确的 空目录
        assertEquals(true, FileUtil.isEmptyDirectory("E:\\empty"));
    }

    /**
     * Test to ur ls.
     */
    @Test
    public void testToURLs(){
        LOGGER.debug(JsonUtil.format(FileUtil.toURLs("D:\\Program Files", "D:\\新建文件夹")));
    }

    /**
     * Creates the directory.
     */
    @Test
    public void testCreateDirectory(){
        String folderPath = "E:\\test\\1\\2011-07-07\\test\\1\\2011-07-07";
        FileUtil.createDirectory(folderPath);
    }

    /**
     * Test create directory by file path.
     */
    @Test
    public void testCreateDirectoryByFilePath(){
        FileUtil.createDirectoryByFilePath("E:\\test\\1\\2011-07-07\\test\\1\\2011-07-07");
        FileUtil.createDirectoryByFilePath("E:\\test\\1\\2011-07-07\\test\\2\\2011-07-07");
    }

    /**
     * Gets the file sizes.
     * 
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testGetFileSizes() throws IOException{
        String testFile = "E:\\DataCommon\\test\\1.png";
        testFile = "E:\\DataCommon\\JDK API 1.6.0 中文版.CHM";

        File file = new File(testFile);

        long fileSizes = FileUtil.getFileSize(file);
        LOGGER.debug(fileSizes + "");
        LOGGER.debug(FileUtil.formatSize(fileSizes) + "");
        LOGGER.debug(FileUtil.formatSize(file.length()) + "");
        LOGGER.debug("比如文件 {} 字节, 格式化大小 : {}", fileSizes, FileUtil.getFileFormatSize(file));
    }

    @Test
    public void testGetFileSizes1() throws IOException{
        String testFile = "E:\\DataCommon\\test\\1.png";
        testFile = "E:/test1";

        File file = new File(testFile);

        long fileSizes = FileUtil.getFileSize(file);
        LOGGER.debug(fileSizes + "");
        LOGGER.debug(FileUtil.formatSize(fileSizes) + "");
        LOGGER.debug(FileUtil.formatSize(file.length()) + "");
        LOGGER.debug("比如文件 {} 字节, 格式化大小 : {}", fileSizes, FileUtil.getFileFormatSize(file));
    }

    @Test
    public void formatFileSize(){
        LOGGER.debug(FileUtil.formatSize(1134));
        LOGGER.debug(FileUtil.formatSize(800000001));
        LOGGER.debug(FileUtil.formatSize(8000003333001L));
        LOGGER.debug(FileUtil.formatSize(800000333222223001L));
        LOGGER.debug(FileUtil.formatSize(8000222200333223001L));
        LOGGER.debug(FileUtil.formatSize(898152));
        LOGGER.debug(FileUtil.formatSize(8981528));
        LOGGER.debug(org.apache.commons.io.FileUtils.byteCountToDisplaySize(8981528));
    }

    @Test
    public void formatFileSize1(){
        assertEquals("0Bytes", FileUtil.formatSize(0));
        assertEquals("0Bytes", FileUtil.formatSize(-0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void formatFileSize2(){
        FileUtil.formatSize(-255);
    }

    /**
     * Test delete file or directory.
     */
    @Test
    @Ignore
    public void testDeleteFileOrDirectory(){
        FileUtil.deleteFileOrDirectory("D:\\test");
    }

    /**
     * Test delete file or directory2.
     */
    @Test
    public void testDeleteFileOrDirectory2(){
        FileUtil.deleteFileOrDirectory("E:\\test");
    }
}
