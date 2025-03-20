package com.griotold.spring_ai_practice.package250319;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class FewShotPromptingService {

    private final ChatClient chatClient;

    public String generateResponse(String question) {
        // Few-Shot 예제 데이터 포함한 프롬프트 템플릿
        String template = """
                아래는 영화 추천에 대한 예제입니다:
                Q: 아이들이 좋아할만한 영화를 추천해줘.
                A: '토이 스토리', '슈렉', '겨울왕국'을 추천합니다.
                
                Q: 감동적인 드라마 영화 추천해줘.
                A: '포레스트 검프', '타이타닉', '인생은 아름다워'를 추천합니다.

                이제 아래 질문에 대한 답을 주세요.
                Q: {question}
                A:
                """;

        // PromptTemplate을 사용하여 실제 질문 삽입
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Prompt prompt = promptTemplate.create(Map.of("question", question));

        return chatClient.prompt(prompt).call().content();
    }
}
