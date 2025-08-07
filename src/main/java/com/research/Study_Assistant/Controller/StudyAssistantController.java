package com.research.Study_Assistant.Controller;

import com.research.Study_Assistant.Request.StudyAssistantRequest;
import com.research.Study_Assistant.Service.StudyAssistantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class StudyAssistantController {
    private final StudyAssistantService studyAssistantService;

    @PostMapping("/process")
    public ResponseEntity<String> processContent(@RequestBody StudyAssistantRequest studyAssistantRequest) {
        String reslut = studyAssistantService.processContent(studyAssistantRequest);
        return ResponseEntity.ok(reslut);
    }

}
