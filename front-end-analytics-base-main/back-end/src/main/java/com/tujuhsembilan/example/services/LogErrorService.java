package com.tujuhsembilan.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tujuhsembilan.example.dto.LogErrorRequest;
import com.tujuhsembilan.example.models.LogErrorModel;
import com.tujuhsembilan.example.repositories.LogErrorRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
public class LogErrorService {

    @Autowired
    private LogErrorRepository logErrorRepository;

    public void saveError(LogErrorRequest errorRequest) {
        LogErrorModel logError = new LogErrorModel();
        logError.setError(errorRequest.getError());
        logError.setStack(errorRequest.getStack());
        logError.setComponentStack(errorRequest.getComponentStack());
        logError.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        
        logErrorRepository.save(logError);
    }

    public List<LogErrorModel> getAllErrors() {
        return logErrorRepository.findAll();
    }
}
