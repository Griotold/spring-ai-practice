package com.griotold.spring_ai_practice.danvega.prompting;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DadJokeController {

    private final ChatClient chatClient;

    public DadJokeController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }
    /**
     * 프롬프팅 - userMessage
     * */
    @GetMapping("/dad-jokes-prompting")
    public String jokes() {
        var user = new UserMessage("Tell me a joke about cats");
        Prompt prompt = new Prompt(user);
        return chatClient.prompt(prompt).call().content();
    }

    /**
     * 프롬프팅 - userMessage + systemMessage
     * */
    @GetMapping("/dad-jokes-system")
    public String jokesWithSystem() {
        var system = new SystemMessage(
                """
                        Your primary functions is to tell Dad Jokes.
                        If someone asks you for any other type of joke please tell them you only
                        know Dad jokes
                        """
        );
        var user = new UserMessage("Tell me a serious joke about the universe");
        Prompt prompt = new Prompt(List.of(system, user));
        return chatClient.prompt(prompt).call().content();
    }
}
