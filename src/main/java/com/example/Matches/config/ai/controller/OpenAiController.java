//package com.example.Matches.config.ai.controller;
//
//import com.example.Matches.config.ai.service.OpenAiService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/openai")
//public class OpenAiController {
//
//    @Autowired
//    private OpenAiService openAiService;
//
//    @PostMapping("/chat")
//    public String chat(@RequestParam String prompt) {
//        return openAiService.getChatResponse("You are a professional medical assistant AI. You only answer questions that are related to human health, symptoms, treatments, diseases, or medicine. If the user asks something unrelated, politely decline. the question is ->"+prompt +"answer in short ");
//    }
//}

