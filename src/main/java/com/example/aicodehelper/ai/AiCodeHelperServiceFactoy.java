package com.example.aicodehelper.ai;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 企业规章制度问答服务工厂类
@Configuration
public class AiCodeHelperServiceFactoy {
    //注入阿里千问大模型
    @Resource
    private ChatModel qwenChatModel;

    //注入我们定义好的rag
    @Resource
    private ContentRetriever contentRetriever;

    //注入流式模型
    @Resource
    private StreamingChatModel qwenStreamingChatModel;

    @Bean
    public AiCodeHelperService aiCodeHelperService(){
        //构造会话记忆对象
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        //使用AiServices.builder构造器来生成代理对象实现类，并加入会话记忆功能
        AiCodeHelperService aiCodeHelperService = AiServices.builder(AiCodeHelperService.class)
                .chatModel(qwenChatModel)
                .streamingChatModel(qwenStreamingChatModel)
                //开启会话记忆
                .chatMemory(chatMemory)
                .chatMemoryProvider(memoryId ->
                        MessageWindowChatMemory.withMaxMessages(10)) // 每个会话独立存储
                //开启rag检索
                .contentRetriever(contentRetriever)
                .build();
        return aiCodeHelperService;
    }
}
