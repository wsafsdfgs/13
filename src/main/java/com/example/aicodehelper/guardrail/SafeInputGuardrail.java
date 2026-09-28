package com.example.aicodehelper.guardrail;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.guardrail.InputGuardrail;
import dev.langchain4j.guardrail.InputGuardrailResult;

import java.util.Set;

public class SafeInputGuardrail implements InputGuardrail {

    // 敏感词库：覆盖脏话、违法违规、暴力等
    private static final Set<String> sensitiveWords = Set.of(
            "杀", "死", "暴力", "赌博", "诈骗", "毒品",
            "傻子", "废物", "垃圾", "滚", "去死",
            "色情", "黄色", "裸", "性",
            "政治", "反动", "分裂",
            "ignore previous instructions", "system prompt", "jailbreak",
            "越狱", "忽略之前的指令", "忽略上面"
    );
    //用户输入时进行敏感词检验
    @Override
    public InputGuardrailResult validate(UserMessage userMessage) {
        //前置条件设置敏感词
        // 获取用户输入并转换为小写以确保大小写不敏感
        String inputText = userMessage.singleText().toLowerCase();
        // 使用正则表达式分割输入文本为单词
        String[] words = inputText.split("\\W+");
        // 遍历所有单词，检查是否存在敏感词
        for (String word : words) {
            if (sensitiveWords.contains(word)) {
                return fatal("检测到敏感词Sensitive word detected: " + word);
            }
        }
        return success();
    }
}