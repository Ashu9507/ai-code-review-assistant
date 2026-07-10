package org.ashutosh.cli_chatbot_langchain4j.model;

import java.util.List;

public record CodeReview(
        String summary,
        List<String> improvements,
        List<String> bugs,
        List<String> interviewQuestions,
        List<String> edgeTestCases
) {
}
