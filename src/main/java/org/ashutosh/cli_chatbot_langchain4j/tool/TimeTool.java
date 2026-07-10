package org.ashutosh.cli_chatbot_langchain4j.tool;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeTool {

    @Tool("Returns current time")
    public String currentTime() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm:ss");
        return LocalDateTime.now().toString();
    }
}
