package com.example.aicodehelper.rag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
// 企业规章制度RAG配置
public class RagConfig {
    //注入qwen向量模型text-embedding-v4
    @Resource
    private EmbeddingModel qwenEmbeddingModel;
    @Resource
    private EmbeddingStore<TextSegment> ingestor;

    @Bean
    public ContentRetriever contentRetriever(){
        //1加载文档，加载resource下的本地文件src/main/resources/docs
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("src/main/resources/docs");

        // 过滤只加载企业规章制度文档
        documents = documents.stream()
            .filter(doc -> doc.text().contains("考勤制度") || 
                          doc.text().contains("请假流程") || 
                          doc.text().contains("报销规则"))
            .toList();

        //2文档切割器，通过文档的段落进行切分，并且设置最大是1000字符，重叠字符为200
        DocumentByParagraphSplitter documentByParagraphSplitter = new DocumentByParagraphSplitter(1000, 200);

        //3自定义文档加载器
        EmbeddingStoreIngestor embeddingStoreIngestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(documentByParagraphSplitter) //加载定义好的文档切割器
                .embeddingModel(qwenEmbeddingModel)//加载向量模型
                .embeddingStore(ingestor)
                .build();

        //文档加载
        embeddingStoreIngestor.ingest(documents);

        //4自定义内容查询器
        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingModel(qwenEmbeddingModel)
                .embeddingStore(ingestor)
                .maxResults(5)//文档查询返回5篇文档
                .minScore(0.75)//过滤掉小于0.75的比分的文档
                .build();
        return contentRetriever;
    }
}
