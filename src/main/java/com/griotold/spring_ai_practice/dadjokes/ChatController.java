package com.griotold.spring_ai_practice.dadjokes;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
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
}
