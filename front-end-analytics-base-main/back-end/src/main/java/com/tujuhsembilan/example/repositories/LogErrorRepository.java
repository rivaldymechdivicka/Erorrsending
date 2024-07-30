package com.tujuhsembilan.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tujuhsembilan.example.models.LogErrorModel;

@Repository
public interface LogErrorRepository extends JpaRepository<LogErrorModel, Long> {
}
