package com.griotold.spring_ai_practice.kids;

import com.griotold.spring_ai_practice.kids.output.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class KidsPromptTemplateController {

    private final ChatClient chatClient;

    @Value("classpath:/prompts/kids.st")
    private Resource kidsPromptResource;

    public KidsPromptTemplateController(ChatClient.Builder builder) {
        this.chatClient = builder.defaultSystem("""
            당신은 5-9세 어린이를 위한 친절한 가정교사입니다. 
            어린이가 이해할 수 있도록 쉬운 단어와 예시를 사용해 주세요.
            """)
                .build();
    }

    /**
     * 파일로 프롬프트 템플릿 관리하기
     * */
    @GetMapping("/kids-template")
    public Question kidsQuestionWithTemplate(@RequestParam(value = "genre", defaultValue = "animal") String genre) {
        PromptTemplate promptTemplate = new PromptTemplate(kidsPromptResource);
        Prompt prompt = promptTemplate.create(Map.of("genre", genre));
        return chatClient.prompt(prompt).call().entity(Question.class);
    }

    /**
     * 파일로 프롬프트 템플릿 관리하기 - 리스트
     * */
    @GetMapping("/kids-template-list")
    public List<Question> kidsQuestionListWithTemplate(@RequestParam(value = "genre", defaultValue = "animal") String genre) {
        PromptTemplate promptTemplate = new PromptTemplate(kidsPromptResource);
        Prompt prompt = promptTemplate.create(Map.of("genre", genre));
        return chatClient.prompt(prompt).call().entity(new ParameterizedTypeReference<>() {});
    }
}
