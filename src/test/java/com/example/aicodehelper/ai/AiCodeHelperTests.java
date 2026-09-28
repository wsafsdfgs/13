package com.example.aicodehelper.ai;

import com.example.aicodehelper.ai.AiCodeHelper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
//必须加上@SpringBootTest注解才能开启测试
@SpringBootTest
public class AiCodeHelperTests {
    //注入我们定义好的AiCodeHelper类
    @Resource
    AiCodeHelper aiCodeHelper;
    @Test
    void contextLoads() {
    }

    @Test
    void chat() {
        //传入String参数给qwen模型的chat方法
        aiCodeHelper.chat("你好我是张三");

    }
}
