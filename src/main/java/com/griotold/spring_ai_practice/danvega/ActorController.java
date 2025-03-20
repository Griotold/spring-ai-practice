package com.griotold.spring_ai_practice.danvega;

import com.griotold.spring_ai_practice.danvega.output.ActorFilms;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActorController {

    private final ChatClient chatClient;

    public ActorController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    /**
     * 그냥 문자열로 응답 받는 것
     * */
    @GetMapping("/films-string")
    public String getActorFilmsString() {
        return chatClient.prompt()
                .user("Generate a filmography for a Anthony Hopkins for the year 2010.")
                .call()
                .content();
    }

    /**
     * 자바 객체로 응답받기
     * */
    @GetMapping("/films")
    public ActorFilms getActorFilms() {
        return chatClient.prompt()
                .user("Generate a filmography for the actor Robert De Niro.")
                .call()
                .entity(ActorFilms.class);
    }

    /**
     * List<>로 받기
     * */
    @GetMapping("/films-list")
    public List<ActorFilms> listActorFilms() {
        return chatClient.prompt()
                .user("Generate a filmography for the actors Denzel Washington, Leonardo Dicaprio and Tom Hanks.")
                .call()
                .entity(new ParameterizedTypeReference<>() {});
    }
}
