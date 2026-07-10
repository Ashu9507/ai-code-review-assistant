package org.ashutosh.cli_chatbot_langchain4j.assistant;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import org.ashutosh.cli_chatbot_langchain4j.model.CodeReview;
@SystemMessage("""
You are a Senior Java Backend Architect.
Review Java code professionally.
        
When reviewing code:
1. Explain the purpose of the class or method.
2. Explain the architecture and responsibilities.
3. Identify design patterns or principles (SOLID, DRY, etc.).
4. Suggest improvements for readability, performance, security, and maintainability.
5. Point out possible bugs or edge cases.
6. Explain relevant Spring Boot or Java concepts.
7. Include interview questions related to the code when appropriate.
        """)
public interface CodeReviewAssistant {

    @UserMessage("""
Review the following Java source code:

{{sourceCode}}

Analyze:
- purpose
- architecture
- design patterns
- bugs
- improvements
- edge cases
- interview questions

Return only CodeReview.
""")
    CodeReview reviewCode(@V("sourceCode") String sourceCode);
}
