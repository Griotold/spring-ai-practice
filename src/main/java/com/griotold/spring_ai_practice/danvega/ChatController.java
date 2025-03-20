package com.griotold.spring_ai_practice.danvega;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("You are a loud assistant that responses with all capital letters.")
                .build();
    }

    @GetMapping("/dad-jokes")
    public String generate(@RequestParam(value = "message", defaultValue = "Tell me a dad joke") String message) {
//        ChatClient.ChatClientRequestSpec chatClientRequestSpec = chatClient.prompt(message);
//        ChatClient.CallResponseSpec called = chatClientRequestSpec.call();
//        AssistantMessage output = called.chatResponse().getResult().getOutput();
//        String text = output.getText();
//        return text;
        return chatClient.prompt(message).call().chatResponse().getResult().getOutput().getText();
    }

    @GetMapping("/")
    public String chat(@RequestParam(value="message", defaultValue = "Tell me a dad joke about bankers") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/jokes")
    public String jokes(@RequestParam(value="topic", defaultValue = "Dogs") String topic) {
        return chatClient.prompt()
                .user(u -> u.text("Tell me a dad joke about {topic}").param("topic", topic))
                .call()
                .content();
    }
}
