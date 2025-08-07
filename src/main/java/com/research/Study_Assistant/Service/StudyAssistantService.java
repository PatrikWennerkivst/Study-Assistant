package com.research.Study_Assistant.Service;


import com.research.Study_Assistant.Request.StudyAssistantRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class StudyAssistantService {

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final WebClient webClient;

    public StudyAssistantService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String processContent(StudyAssistantRequest studyAssistantRequest) {
        //Build the prompt
        String prompt = buildPrompt(studyAssistantRequest);

        //Query the AI Model API
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
       );

        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();


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
