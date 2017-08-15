package com.kafeneio.repository;

import java.sql.Blob;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kafeneio.model.WorkAssignmentModel;

public interface WorkAssignmentRepository extends JpaRepository<WorkAssignmentModel, Long> {
	//@Query("SELECT r FROM WorkAssignmentModel r WHERE r.userId=:userId")
List<WorkAssignmentModel> findByUserIdAndDate(Long userId,String date);
List<WorkAssignmentModel>findByStatus(Boolean status);
WorkAssignmentModel findBylinkIdAndUserIdAndDate(Long LinkId,Long userId,String date);

@Modifying 
@Query("UPDATE WorkAssignmentModel w SET w.status = :status WHERE w.userId = :userId AND w.date=:date AND w.linkId=:linkId")
int updateStatus(@Param("status") Boolean status,@Param("date") String date, @Param("userId") Long userID ,@Param("linkId") Long linkId);	
}
