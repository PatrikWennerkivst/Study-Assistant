package com.research.Study_Assistant.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeminiResponse {

    private List<Candidate> candidates;

    private static class Candidate {
        private Content content;
    }

    private static class Content {
        private List<Part> parts;
    }

    private static class Part {
        private String text;
    }
}
