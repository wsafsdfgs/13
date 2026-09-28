package com.example.aicodehelper.controller;

import com.example.aicodehelper.ai.AiCodeHelperService;
import dev.langchain4j.service.Result;
import jakarta.annotation.Resource;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class AiController {

    @Resource
    private AiCodeHelperService aiCodeHelperService;

    /**
     * 基础聊天接口
     */
    @GetMapping("/chat")
    public Flux<ServerSentEvent<String>> chat(int memoryId, String message) {
        return aiCodeHelperService.chatStream(memoryId, message)
                .map(chunk -> ServerSentEvent.<String>builder()
                        .data(chunk)
                        .build());
    }

    /**
     * RAG增强问答接口
     */
    @GetMapping("/chat-with-rag")
    public Result<String> chatWithRag(@RequestParam String message) {
        return aiCodeHelperService.chatwithrag(message);
    }

    /**
     * 查询请假流程条款接口
     */
    @GetMapping("/query-leave-process")
    public Result<String> queryLeaveProcess(@RequestParam String message) {
        return aiCodeHelperService.queryLeaveProcess(message);
    }

    /**
     * 查询报销要求接口
     */
    @GetMapping("/query-reimbursement-rules")
    public Result<String> queryReimbursementRules(@RequestParam String message) {
        return aiCodeHelperService.queryReimbursementRules(message);
    }

    /**
     * 查询考勤奖惩说明接口
     */
    @GetMapping("/query-attendance-rules")
    public Result<String> queryAttendanceRewardsAndPunishments(@RequestParam String message) {
        return aiCodeHelperService.queryAttendanceRewardsAndPunishments(message);
    }
}
