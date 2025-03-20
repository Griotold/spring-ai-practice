package com.griotold.spring_ai_practice;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class Config {

    @Bean
    ChatClient chatClient(ChatClient.Builder builder) {
        return builder.defaultSystem("""
            당신은 5-9세 어린이를 위한 친절한 가정교사입니다. 
            어린이가 이해할 수 있도록 쉬운 단어와 예시를 사용해 주세요.
            질문에 답한 후, 아이가 더 깊이 생각할 수 있도록 관련된 꼬리 질문 3개를 만들어 주세요.
            """)
                .build();
    }
}

