package org.ashutosh.cli_chatbot_langchain4j;

import org.ashutosh.cli_chatbot_langchain4j.config.AssistantProperties;
import org.ashutosh.cli_chatbot_langchain4j.config.OllamaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		OllamaProperties.class,
		AssistantProperties.class
})
public class CliChatbotLangchain4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(CliChatbotLangchain4jApplication.class, args);
	}

}
