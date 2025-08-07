package com.research.Study_Assistant.Service;


import com.research.Study_Assistant.Request.StudyAssistantRequest;
import org.springframework.stereotype.Service;

@Service
public class StudyAssistantService {
    public String processContent(StudyAssistantRequest studyAssistantRequest) {
        //Build the prompt
        //Query the AI Model API
        // Parse the response
        // Return response
    }

    private String buildPrompt(StudyAssistantRequest studyAssistantRequest) {
        StringBuilder promt = new StringBuilder();
    }
}
