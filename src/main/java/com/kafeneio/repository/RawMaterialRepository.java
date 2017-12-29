package com.kafeneio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafeneio.model.Expenses;
import com.kafeneio.model.RawMaterials;

public interface RawMaterialRepository  extends JpaRepository<RawMaterials, Long> {

}
