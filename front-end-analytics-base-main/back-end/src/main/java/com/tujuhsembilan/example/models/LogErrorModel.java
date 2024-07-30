package com.tujuhsembilan.example.models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LogErrorModel {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100000)
    private String error;

    @Column(length = 100000)
    private String stack;

    @Column(length = 100000)
    private String componentStack;

    private Timestamp createdAt;
}
