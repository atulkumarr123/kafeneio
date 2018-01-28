package com.kafeneio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafeneio.model.ConfigParameters;

public interface ConfigParametersRepo  extends JpaRepository<ConfigParameters, Long> {
	
	
}
