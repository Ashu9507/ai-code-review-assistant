package org.ashutosh.cli_chatbot_langchain4j.tool;

import dev.langchain4j.agent.tool.Tool;
import org.ashutosh.cli_chatbot_langchain4j.config.AssistantProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "assistant")
public class ProjectExplorerTool {

    private final AssistantProperties assistantProperties;

    public ProjectExplorerTool(AssistantProperties assistantProperties) {
        this.assistantProperties = assistantProperties;
    }

    private static final int MAX_DEPTH = 10;

    @Tool("Find java source files by filename")
    public List<String> findJavaFiles(String fileName) throws IOException {

        Path root = Paths.get(assistantProperties.getProjectRoot()).toAbsolutePath().normalize();
        return Files.walk(root,MAX_DEPTH)
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".java"))
                .filter(path -> path.getFileName().toString().replace(".java","").equalsIgnoreCase(fileName.replace(".java","")))
                .map(path -> root.relativize(path).toString())
                .toList();
    }
}
