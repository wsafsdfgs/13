package com.example.aicodehelper.ai;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AiCodeHelper {
    //系统提示词
    private  static  final  String SYSTEM_MESSAGE = """
            你是企业规章制度问答机器人，帮助公司内部员工解答企业制度相关疑问，高效解决员工日常办公制度问题。重点关注 3 个方向：
                                 1. 解答考勤制度相关疑问（考勤规则、奖惩标准、出勤要求等）
                                 2. 讲解请假流程规范（各类假期申请、审批步骤、手续要求等）
                                 3. 说明报销规则条款（报销范围、材料要求、报销流程、规范禁忌等）
                                 请根据用户提问，结合企业规章制度,联系上下文，给出简洁明了的答案。
            """;
    //依赖注入我们的qwen大模型
    @Resource
    private ChatModel qwenChatModel;

    public  String chat(String message){
        //用户消息类型UserMessage
        UserMessage userMessage = UserMessage.from(message);
        //系统提示词消息
        SystemMessage systemMessage = SystemMessage.from(SYSTEM_MESSAGE);
        //使用注入的qwen模型的chat方法传入用户消息
        ChatResponse response = qwenChatModel.chat(systemMessage,userMessage);
        //qwen模型返回的结果打印
        AiMessage aiMessage = response.aiMessage();
        log.info("AI输出为:"+aiMessage.toString());
        //返回ai输出的结果
        return aiMessage.text();

    }
    //多模态
    //传入的参数为UserMessage
    public  String ChatWithMessage(UserMessage userMessage){
        ChatResponse response = qwenChatModel.chat(userMessage);
        AiMessage aiMessage = response.aiMessage();
        log.info("AI输出为:"+aiMessage.toString());
        return aiMessage.text();
    }

}
