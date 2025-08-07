package com.research.Study_Assistant.Controller;

import com.research.Study_Assistant.Service.StudyAssistantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class StudyAssistantController {
    private final StudyAssistantService studyAssistantService;

    @PostMapping("/process")
    public ResponseEntity<String> processContent() {
        return "";
    }

}
