//package com.example.Matches.config.ai.controller;
//
//import com.agiles.UniChain.config.ai.service.InterestAiService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("ai")
//public class interestAiController {
//    public InterestAiService interestAi;
//
//    public interestAiController(InterestAiService InterestAiService) {
//        this.interestAi = InterestAiService;
//    }
//    @GetMapping("interest/question/{id}/{text}")
//    public ResponseEntity<String> interests(@PathVariable Long id,@PathVariable String text) {
//        return ResponseEntity.ok(interestAi.interest(id, text));
//    }
//}