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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.feilong.io.filenameutil.FeiLongFilenameUtilSuiteTests;
import com.feilong.io.ioreaderutil.GetContentFileInputStreamTest;
import com.feilong.io.ioreaderutil.GetContentInputStreamTest;
import com.feilong.io.ioreaderutil.ReadFileToStringFileAndCharsetNameTest;
import com.feilong.io.ioreaderutil.ReadFileToStringFilePathAndCharsetNameTest;
import com.feilong.io.ioreaderutil.ResolverFileAndLineNumberReaderTest;
import com.feilong.io.ioreaderutil.ResolverFilePathAndLineNumberReaderTest;
import com.feilong.io.ioreaderutil.ResolverReaderAndLineNumberReaderTest;
import com.feilong.io.iowriteutil.WriteDirectoryAndFileTest;
import com.feilong.io.readerutil.NewStringReaderTest;
import com.feilong.io.readerutil.ReadLineTest;
import com.feilong.io.readerutil.ToStringTest;

/**
 * The Class FeiLongIoSuiteTests.
 *
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 1.11.0
 */
@RunWith(Suite.class)
@SuiteClasses({ //
                FeiLongFilenameUtilSuiteTests.class,

                NewStringReaderTest.class,
                WriteDirectoryAndFileTest.class,

                GetContentFileInputStreamTest.class,
                GetContentInputStreamTest.class,
                ReadFileToStringFileAndCharsetNameTest.class,
                ReadFileToStringFilePathAndCharsetNameTest.class,

                ResolverFileAndLineNumberReaderTest.class,
                ResolverFilePathAndLineNumberReaderTest.class,
                ResolverReaderAndLineNumberReaderTest.class,

                NewStringReaderTest.class,
                ReadLineTest.class,
                ToStringTest.class

})
public class FeiLongIoSuiteTests{

}
