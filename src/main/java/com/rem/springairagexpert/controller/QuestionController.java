package com.rem.springairagexpert.controller;

import com.rem.springairagexpert.model.Answer;
import com.rem.springairagexpert.model.Question;
import com.rem.springairagexpert.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class QuestionController {

    private final OpenAIService openAIService;

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }

    @GetMapping("/searchFromDb")
    public List<String> searchFromDb(@RequestBody Question question) {
        return openAIService.getContentList(question);
    }

}
