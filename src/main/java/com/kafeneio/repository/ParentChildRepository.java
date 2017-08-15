package com.kafeneio.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kafeneio.model.ParentChildModel;

public interface ParentChildRepository extends JpaRepository<ParentChildModel, Long>{
	ParentChildModel findByparentIdAndPosition(@Param("parentId") Long parentId,@Param("position") String position);
	  @Query("SELECT r FROM ParentChildModel r WHERE r.registration.loginModel.email=:email")
	ParentChildModel findOneByEmail(@Param("email") String email);
	  @Query("SELECT p FROM ParentChildModel p WHERE p.registration.id=:id")
	  ParentChildModel findByRegistration(@Param("id") Long id);
	  
	  //@Query("SELECT a FROM ParentChildModel a INNER JOIN a.registration b ON a.parentId = b.id WHERE (b.id = :parentId) OR b.parent_id= :parentId")
	  
	  
	  @Query(value = "SELECT GROUP_CONCAT(lv SEPARATOR ',') FROM (SELECT @pv \\:=(SELECT GROUP_CONCAT(registration_id SEPARATOR ',') FROM parent_child WHERE parent_id IN (@pv)) AS lv FROM parent_child JOIN(SELECT @pv \\:=?1)tmp WHERE parent_id IN (@pv)) a ;", nativeQuery = true)
	 String findByParentId(@Param("parentId") Long parentId);
	  
	// ParentChildModel findByRegistration(Long id);
	  @Query("SELECT p FROM ParentChildModel p WHERE p.registration.id=:id")
	  ParentChildModel findByRegistrationId(@Param("id") Long id);
	  
}
