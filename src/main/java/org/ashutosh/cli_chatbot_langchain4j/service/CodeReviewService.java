package org.ashutosh.cli_chatbot_langchain4j.service;

import org.ashutosh.cli_chatbot_langchain4j.assistant.CodeReviewAssistant;
import org.ashutosh.cli_chatbot_langchain4j.model.CodeReview;
import org.ashutosh.cli_chatbot_langchain4j.tool.CodeReaderTool;
import org.ashutosh.cli_chatbot_langchain4j.tool.ProjectExplorerTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CodeReviewService {

    private static final Logger log =
            LoggerFactory.getLogger(CodeReviewService.class);

    private final CodeReviewAssistant codeReviewAssistant;
    private final ProjectExplorerTool projectExplorerTool;
    private final CodeReaderTool codeReaderTool;

    public CodeReviewService(CodeReviewAssistant codeReviewAssistant, ProjectExplorerTool projectExplorerTool, CodeReaderTool codeReaderTool) {
        this.codeReviewAssistant = codeReviewAssistant;
        this.projectExplorerTool = projectExplorerTool;
        this.codeReaderTool = codeReaderTool;
    }

    public CodeReview reviewCode(String fileName) throws IOException {

        List<String> files =
                projectExplorerTool.findJavaFiles(fileName);


        if(files.isEmpty()) {
            throw new RuntimeException(
                    "File not found: " + fileName
            );
        }


        String sourceCode =
                codeReaderTool.readFile(files.get(0));


        return codeReviewAssistant.reviewCode(
                sourceCode
        );
    }
}