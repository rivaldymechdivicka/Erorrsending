package com.tujuhsembilan.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tujuhsembilan.example.dto.LogErrorRequest;
import com.tujuhsembilan.example.exception.MultipleException;
import com.tujuhsembilan.example.models.LogErrorModel;
import com.tujuhsembilan.example.services.LogErrorService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/sample")
public class SampleController {

  @Autowired
  private LogErrorService logErrorService;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private static class SampleRequestBody {
    @Size(max = 10)
    private String input;

    @NotBlank
    private String mustHaveSomething;
  }

  @PostMapping("/post")
  public ResponseEntity<?> postSomething(@Valid @RequestBody SampleRequestBody body) {
    throw new EntityNotFoundException("Test");
  }

  @GetMapping("/multiple-exception")
  public ResponseEntity<?> multipleException() {
    throw new MultipleException(new EntityNotFoundException("Test1"), new IndexOutOfBoundsException("Test2"));
  }

  @PostMapping("/logError")
  public ResponseEntity<Map<String, String>> logError(@RequestBody LogErrorRequest errorReport) {
    
    logErrorService.saveError(errorReport);

    Map<String, String> response = new HashMap<>();
    response.put("status", "success");
    response.put("message", "Error logged successfully");
    return ResponseEntity.ok(response);
  }

  @GetMapping("/logErrors")
  public ResponseEntity<List<LogErrorModel>> getAllErrors() {
        List<LogErrorModel> errors = logErrorService.getAllErrors();
        return ResponseEntity.ok(errors);
    }
    
}
