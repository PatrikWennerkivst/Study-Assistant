package com.research.Study_Assistant.Service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.research.Study_Assistant.Request.StudyAssistantRequest;
import com.research.Study_Assistant.Response.GeminiResponse;
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

    private final ObjectMapper objectMapper;

    public StudyAssistantService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.build();
        this.objectMapper =objectMapper;
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

        return  extractTextFromResponse(response);
    }

    // Parse the response
    // Return response
    private String extractTextFromResponse(String response){
        try {
            GeminiResponse geminiResponse = objectMapper.readValue(response, GeminiResponse.class);
            if (geminiResponse.getCandidates() != null && !geminiResponse.getCandidates().isEmpty()) {
                GeminiResponse.Candidate firstCandidate = geminiResponse.getCandidates().get(0);
                if (firstCandidate.getContent() != null &&
                    firstCandidate.getContent().getParts() != null &&
                    !firstCandidate.getContent().getParts().isEmpty()) {
                return firstCandidate.getContent().getParts().get(0).getText();
                }
            }
        } catch (Exception e) {
            return "Errosr Parsing: " + e.getMessage();
        }

        return "No content was found in the response";
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
