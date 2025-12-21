package com.nofirst.zhihu;

import com.nofirst.zhihu.service.AnswerServiceImplTest;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

// 只有这个注解起作用
@SelectClasses(AnswerServiceImplTest.class)
// 下面这个注解单独使用不起作用
@IncludeTags("unit")
@Suite
public class UnitTestSuite {
}
