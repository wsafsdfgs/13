package com.example.aicodehelper;

import com.example.aicodehelper.ai.AiCodeHelper;
import com.example.aicodehelper.ai.AiCodeHelperService;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;

import dev.langchain4j.service.Result;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
//必须加上@SpringBootTest注解才能开启测试
@SpringBootTest
class AiCodeHelperApplicationTests {

    //注入我们定义好的AiCodeHelper类
    @Resource
    AiCodeHelper aiCodeHelper;

    @Resource
    AiCodeHelperService aiCodeHelperService;

    @Test
    void contextLoads() {
    }

    @Test
    void chat() {
        //传入String参数给qwen模型的chat方法
//        aiCodeHelper.chat("你好我是张三");
        //加人系统提示词的结果
        aiCodeHelper.chat("你介绍一下自己");

    }
    @Test
    void ChatWithMessage() {
       //多模态图片测试
        //定义用户输入的形参
        UserMessage userMessage = UserMessage.from(
                //输入文本内容
                TextContent.from("描述一下图片"),
                //输入图片内容url
                ImageContent.from("https://www.codefather.cn/logo.png")
        );
        //调用我们的多模态方法ChatWithMessage
        aiCodeHelper.ChatWithMessage(userMessage);
    }
//
//    @Test
//    void chat1() {
//    //测试AiService服务通过注解的方式
//        String chat = aiCodeHelperService.chat("你好我是张三，介绍一下自己");
//        System.out.println(chat);
//
//    }
//    @Test
//    void chat2() {
//        //测试未开启会话记忆
//        String chat = aiCodeHelperService.chat("你好我是张三，介绍一下自己");
//        System.out.println(chat);
//        String chat1 = aiCodeHelperService.chat("你是否还记得我");
//        System.out.println(chat1);
//
//    }
//
//    @Test
//    void chat3() {
//        //测试开启会话记忆
//        String chat = aiCodeHelperService.chat("你好我是张三");
//        System.out.println(chat);
//        String chat1 = aiCodeHelperService.chat("你是否还记得我");
//        System.out.println(chat1);
//
//    }
//
//    @Test
//    void chatwithreport() {
//        //测试结构化输出
//        AiCodeHelperService.Report report = aiCodeHelperService.chatWithReport("你好，我是程序员张三，学编程两年半，请帮我制定学习报告");
//        System.out.println(report);
//
//    }
//    @Test
//    void chatwithtools() {
//        //测试结构化输出
//        String chat = aiCodeHelperService.chat("有没有哪些计算机网络的面试题推荐给我");
//        System.out.println(chat);
//
//    }
//
//    @Test
//    void chatwithtomcp() {
//        //测试结构化输出
//        String chat = aiCodeHelperService.chat("给我推荐几个编程学习的网站？");
//        System.out.println(chat);
//
//    }
    @Test
    void chatwithtragResult() {
        //测试结构化输出
        Result<String> result = aiCodeHelperService.chatwithrag("怎么学习 Java？有哪些常见面试题？");
        System.out.println(result.sources());
        System.out.println(result.content());

    }
//    @Test
//    void chatwithtrag() {
//        //测试结构化输出
//        String chat = aiCodeHelperService.chat("怎么学习 Java？有哪些常见面试题？");
//        System.out.println(chat);
//
//
//    }

        @Test
    void chatwithtrag() {
        //测试结构化输出
        String chat = aiCodeHelperService.chat("kill the game");
        System.out.println(chat);


    }


}
