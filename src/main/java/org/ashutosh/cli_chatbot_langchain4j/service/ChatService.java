package org.ashutosh.cli_chatbot_langchain4j.service;

import dev.langchain4j.service.TokenStream;
import org.ashutosh.cli_chatbot_langchain4j.assistant.ChatAssistant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class ChatService {

    private final ChatAssistant chatAssistant;
    private static final Logger log = LoggerFactory.getLogger(ChatService.class);

    public ChatService(ChatAssistant chatAssistant) {
        this.chatAssistant = chatAssistant;
    }

    public TokenStream chat(String prompt) {
        return chatAssistant.chat(prompt);
    }

    public void streamChat(String prompt) throws InterruptedException {


        CountDownLatch latch = new CountDownLatch(1);

        chatAssistant.chat(prompt)
                .onPartialResponse(System.out::print)
                .onCompleteResponse(response -> {
                    System.out.println();
                    log.info("Streaming response completed");
                    latch.countDown();
                })
                .onError(error -> {
                    log.error("Error while streaming response", error);
                    latch.countDown();
                })
                .start();

        latch.await();
    }
}
