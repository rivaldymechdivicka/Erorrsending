package com.tujuhsembilan.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogErrorRequest {
    private String error;
    private String stack;
    private String componentStack;
}
