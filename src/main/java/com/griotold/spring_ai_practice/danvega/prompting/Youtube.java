package com.griotold.spring_ai_practice.danvega.prompting;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/youtube")
@RestController
public class Youtube {

    private final ChatClient chatClient;
    @Value("classpath:/prompts/youtube.st")
    private Resource ytPromptResource;

    public Youtube(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/popular")
    public String findPopularYouTubersByGenre(@RequestParam(value = "genre", defaultValue = "tect") String genre) {
        String message = """
                List 10 of the most popular YouTubers in {genre} along with their current subscriber counts. If you don't
                know answer, just say "I don't know".
                """;
        String message2 = """
                List 10 of the most popular YouTubers in {genre} along with their current subscriber counts. 
                If you don't know the exact subscriber count, provide an estimated range or say "I don't know".
                Provide the output in a table format with columns for 'Name' and 'Subscribers'.
                """;
        PromptTemplate promptTemplate = new PromptTemplate(message2);
        Prompt prompt = promptTemplate.create(Map.of("genre", genre));

        return chatClient.prompt(prompt).call().content();
    }

    /**
     * 프롬프트 템플릿을 파일로 관리하기
     * */
    @GetMapping("/popular-template")
    public String findPopularYouTuberWithTemplate(@RequestParam(value = "genre", defaultValue = "tech") String genre) {
        PromptTemplate promptTemplate = new PromptTemplate(ytPromptResource);
        Prompt prompt = promptTemplate.create(Map.of("genre", genre));
        return chatClient.prompt(prompt).call().content();
    }
}
