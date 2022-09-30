package com.example.repo;

import com.example.model.Institute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface InstituteRepository extends JpaRepository<Institute, Integer> {

    List<Institute> findByInstitutenameContaining(String title);
}
