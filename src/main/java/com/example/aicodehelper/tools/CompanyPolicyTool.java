package com.example.aicodehelper.tools; // 改成你自己的包名

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class CompanyPolicyTool {

    // 用于调用主项目（8080）的规章查询接口
    private final RestTemplate restTemplate = new RestTemplate();

    // 企业规章系统的内部接口地址（假设主项目跑在8080）
    private static final String BASE_URL = "http://localhost:8080/inner/policy";

    /**
     * 根据关键词检索企业规章制度的具体条文
     *
     * @param keyword 搜索关键词（如 "年假"、"报销"、"考勤"）
     * @return 相关规章原文，若失败则返回错误信息
     */
    @Tool(name = "searchCompanyPolicy", value = """
            Retrieves relevant company policy and rules from the internal knowledge base based on a keyword.
            Use this tool when the user asks about company regulations, leave policies, reimbursement processes,
            attendance rules, or other internal company rules.
            The input should be a clear search term, such as "annual leave" or "reimbursement".
            """
    )
    public String searchCompanyPolicy(@P(value = "the keyword to search policy, e.g. 'annual leave', 'reimbursement'") String keyword) {
        try {
            // 1. 调用主项目（8080）的规章检索接口
            String url = BASE_URL + "/search?keyword=" + keyword;
            String result = restTemplate.getForObject(url, String.class);

            if (result == null || result.isEmpty()) {
                return "未找到与“" + keyword + "”相关的规章制度。";
            }
            return result;
        } catch (Exception e) {
            log.error("检索企业规章失败", e);
            return "检索企业规章失败，请稍后重试。";
        }
    }

    /**
     * 获取某项规章的详细原文内容
     *
     * @param policyId 规章的ID或名称（如 "年假制度"、"报销流程"）
     * @return 该规章的完整原文
     */
    @Tool(name = "getPolicyDetail", value = """
            Retrieves the full content of a specific company policy by its ID or name.
            Use this tool when the user needs detailed, full-text information about a specific policy.
            The input should be the policy's name or ID.
            """
    )
    public String getPolicyDetail(@P(value = "the policy name or ID, e.g. 'annual leave policy'") String policyId) {
        try {
            String url = BASE_URL + "/detail?policyId=" + policyId;
            String result = restTemplate.getForObject(url, String.class);
            return result != null ? result : "未找到该规章的详细内容。";
        } catch (Exception e) {
            log.error("获取规章详情失败", e);
            return "获取规章详情失败，请稍后重试。";
        }
    }
}