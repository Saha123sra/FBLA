package com.transactionmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transactionmgmt.model.SpendingPattern;
import com.transactionmgmt.service.SpendingPatternService;

@RestController
@RequestMapping("/api/spending-pattern")
public class SpendingPatternController {

 @Autowired
 private SpendingPatternService spendingPatternService;

 @GetMapping
 public ResponseEntity<SpendingPattern> getSpendingPattern() {
     SpendingPattern spendingPattern = spendingPatternService.getSpendingPattern();
     return ResponseEntity.ok(spendingPattern);
 }
}
