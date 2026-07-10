package org.ashutosh.cli_chatbot_langchain4j.config;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.service.AiServices;
import org.ashutosh.cli_chatbot_langchain4j.assistant.ChatAssistant;
import org.ashutosh.cli_chatbot_langchain4j.assistant.CodeReviewAssistant;
import org.ashutosh.cli_chatbot_langchain4j.tool.CalculatorTool;
import org.ashutosh.cli_chatbot_langchain4j.tool.CodeReaderTool;
import org.ashutosh.cli_chatbot_langchain4j.tool.ProjectExplorerTool;
import org.ashutosh.cli_chatbot_langchain4j.tool.TimeTool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class AiConfig {

    @Bean
    public StreamingChatModel chatLanguageModel(OllamaProperties ollamaProperties) {

        return OllamaStreamingChatModel.builder()
                .baseUrl(ollamaProperties.getBaseUrl())
                .modelName(ollamaProperties.getModelName())
                .build();
    }

    @Bean
    public ChatModel chatModel(OllamaProperties ollamaProperties) {
        return OllamaChatModel.builder()
                .baseUrl(ollamaProperties.getBaseUrl())
                .modelName(ollamaProperties.getModelName())
                .timeout(Duration.ofMinutes(5))
                .build();
    }

    @Bean
    public ChatAssistant chatAssistant(StreamingChatModel streamingChatModel,
                                   CalculatorTool calculatorTool,
                                   TimeTool timeTool,
                                   CodeReaderTool codeReaderTool,
                                   ProjectExplorerTool projectExplorer) {
        return AiServices.builder(ChatAssistant.class)
                .streamingChatModel(streamingChatModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(100))
                .tools(calculatorTool, timeTool, codeReaderTool,  projectExplorer)
                .build();
    }

    @Bean
    public CodeReviewAssistant codeReviewAssistant(ChatModel chatModel) {

        return AiServices.builder(CodeReviewAssistant.class)
                .chatModel(chatModel)
                .build();
    }
}
