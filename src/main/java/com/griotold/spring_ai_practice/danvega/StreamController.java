package com.griotold.spring_ai_practice.danvega;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StreamController {

    private final ChatClient chatClient;

    public StreamController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }
    /**
     * 그냥 문자열로 받기
     * */
    @GetMapping("/stream-string")
    public String stream() {
        return chatClient.prompt()
                .user("I am traveling to Kansas City next week what are 10 of the best BBQ joints in the city!")
                .call()
                .content();
    }

    /**
     * stream 으로 받기
     * */
    @GetMapping("/stream-real")
    public Flux<String> streamWithStream() {
        return chatClient.prompt()
                .user("I am traveling to Kansas City next week what are 10 of the best BBQ joints in the city!")
                .stream()
                .content();
    }
}
