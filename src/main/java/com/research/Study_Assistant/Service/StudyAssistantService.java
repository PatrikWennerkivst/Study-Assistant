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
        switch (studyAssistantRequest.getOperation()) {
            case "summarize":
                promt.append("Give a concise and clear summary of the following text in a few sentences:\n\n");
                break;
            case "suggest":
                promt.append("Based on the following content: suggest related topics and further reading. Format the response with clear heading and organized bullet points: \n\n");
            default:
                throw  new IllegalArgumentException("Unknown Operation: " + studyAssistantRequest.getOperation());
        }

        promt.append(studyAssistantRequest.getContent());
        return promt.toString();
    }
}
