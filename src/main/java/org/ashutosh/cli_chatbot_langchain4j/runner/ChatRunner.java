package org.ashutosh.cli_chatbot_langchain4j.runner;

import org.ashutosh.cli_chatbot_langchain4j.model.CodeReview;
import org.ashutosh.cli_chatbot_langchain4j.service.ChatService;
import org.ashutosh.cli_chatbot_langchain4j.service.CodeReviewService;
import org.ashutosh.cli_chatbot_langchain4j.tool.ProjectExplorerTool;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ChatRunner implements CommandLineRunner {

    private final ChatService chatService;
    private final CodeReviewService codeReviewService;
    private final ProjectExplorerTool projectExplorerTool;

    public ChatRunner(ChatService chatService, CodeReviewService codeReviewService, ProjectExplorerTool projectExplorerTool) {
        this.chatService = chatService;
        this.codeReviewService = codeReviewService;
        this.projectExplorerTool = projectExplorerTool;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
//            System.out.print("You: ");
//            String line = scanner.nextLine();
//            if ((line.equals("exit"))) {
//                break;
//            }
//            System.out.print("Chatbot: ");
//            chatService.streamChat(line);

            System.out.print("File: ");

            String file = scanner.nextLine();

            System.out.println(
                    projectExplorerTool.findJavaFiles("ChatService.java")
            );

            if ((file.equals("exit"))) {
              break;
            }

            System.out.print("Chatbot: ");
           chatService.streamChat(file);

            CodeReview review = codeReviewService.reviewCode(file);

            System.out.println(review);
        }
    }
}
