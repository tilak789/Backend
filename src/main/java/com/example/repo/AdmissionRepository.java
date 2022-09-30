package com.example.repo;

import com.example.model.Admission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRepository extends JpaRepository<Admission, Integer> {
}
