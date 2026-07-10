package org.ashutosh.cli_chatbot_langchain4j.tool;

import dev.langchain4j.agent.tool.Tool;
import org.ashutosh.cli_chatbot_langchain4j.config.AssistantProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class CodeReaderTool {


    private final AssistantProperties assistantProperties;

    public CodeReaderTool(AssistantProperties assistantProperties) {
        this.assistantProperties = assistantProperties;
    }

    @Tool("Reads the contents of a Java source file from the configured project directory. Use this tool when you need to inspect or explain source code.")
    public String readFile(String fileName) {
        Path root = Paths.get(assistantProperties.getProjectRoot()).toAbsolutePath().normalize();

        Path requested = root.resolve(fileName).normalize();

        if (!requested.startsWith(root)) {
            throw new SecurityException("Access denied");
        }

        if (!Files.exists(requested)) {
            return "File not found: " + fileName;
        }

        try {
            return Files.readString(requested);
        } catch (IOException e) {
            return "Error reading file: " + fileName + ": " + e.getMessage();
        }
    }
}
