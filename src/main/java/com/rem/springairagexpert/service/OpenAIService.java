package com.rem.springairagexpert.service;

import com.rem.springairagexpert.model.Answer;
import com.rem.springairagexpert.model.Question;

import java.util.List;

public interface OpenAIService {

    Answer getAnswer(Question question);

    List<String> getContentList(Question question);
}
