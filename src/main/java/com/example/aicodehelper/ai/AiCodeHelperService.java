package com.example.aicodehelper.ai;


import com.example.aicodehelper.guardrail.SafeInputGuardrail;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.guardrail.InputGuardrails;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

import java.util.List;

// 企业规章制度问答服务接口
@InputGuardrails({SafeInputGuardrail.class})
public interface AiCodeHelperService {
    // 基础问答功能
    @SystemMessage("system-prompt.txt")
    public String chat(String message);

    // 流式对话
    @SystemMessage("system-prompt.txt")
    Flux<String> chatStream(@MemoryId int memoryId, @UserMessage String message);

    // 使用RAG增强的问答功能
    @SystemMessage("system-prompt.txt")
    public Result<String> chatwithrag(String message);

    // 查询请假流程条款
    @SystemMessage("system-prompt.txt")
    public Result<String> queryLeaveProcess(String message);

    // 查询报销要求
    @SystemMessage("system-prompt.txt")
    public Result<String> queryReimbursementRules(String message);

    // 查询考勤奖惩说明
    @SystemMessage("system-prompt.txt")
    public Result<String> queryAttendanceRewardsAndPunishments(String message);

    // 结构化报告功能
    @SystemMessage(fromResource ="system-prompt-report.txt" )
     public Report chatWithReport(String message);

    //java特性record来通过构建方法的方式创建出Report对象
    record Report(String name, List<String>suggestionList) { }


}
