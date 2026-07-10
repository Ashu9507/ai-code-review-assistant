package org.ashutosh.cli_chatbot_langchain4j.tool;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class CalculatorTool {

    @Tool("Add two numbers")
    public int addTwoNumbers(int a, int b) {
        System.out.println(a + b);
        return a + b;
    }
}
