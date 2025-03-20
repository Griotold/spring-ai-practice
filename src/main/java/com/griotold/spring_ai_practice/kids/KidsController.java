package com.griotold.spring_ai_practice.kids;

import com.griotold.spring_ai_practice.kids.output.Answer;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KidsController {

    private final ChatClient chatClient;

    public KidsController(ChatClient.Builder builder) {
        this.chatClient = builder.defaultSystem("""
            당신은 5-9세 어린이를 위한 친절한 가정교사입니다. 
            어린이가 이해할 수 있도록 쉬운 단어와 예시를 사용해 주세요.
            질문에 답한 후, 아이가 더 깊이 생각할 수 있도록 관련된 꼬리 질문 3개를 만들어 주세요.
            """)
                .build();
    }

    /**
     * 그냥 문자열로 응답 받는 것
     * */
    @GetMapping("/kids-string")
    public String kidsString() {
        return chatClient.prompt()
                .user("하늘은 왜 파란가요?")
                .call()
                .content();
    }

    /**
     * 자바 객체로 응답받기
     * */
    @GetMapping("/kids-java-object")
    public Answer kidsJavaObject() {
        return chatClient.prompt()
                .user("하늘은 왜 파란가요?")
                .call()
                .entity(Answer.class);
    }

    /**
     * List<>로 응답받기
     * */
    @GetMapping("/kids-list")
    public List<Answer> kidsList() {
        return chatClient.prompt()
                .user("하늘은 왜 파란가요?, 비는 어떻게 내리는 거에요?, 천둥과 번개는 어떻게 치는 거에요?")
                .call()
                .entity(new ParameterizedTypeReference<>() {});
    }
}
