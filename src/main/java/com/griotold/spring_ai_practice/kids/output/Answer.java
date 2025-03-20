package com.griotold.spring_ai_practice.kids.output;

import java.util.List;

public record Answer(String answer, List<String> followUpQuestions) {
}
