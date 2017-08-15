package com.kafeneio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kafeneio.model.IncomeModel;

public interface IncomeRepository  extends JpaRepository<IncomeModel, Long> {
	List<IncomeModel>findByReferralIdAndStatusAndIncomeType(Long refferalId,Boolean status,String incomeType);
	@Query( value ="select * from Income i where i.status= ?1 and i.income_type=?2 and i.referral_id=?3 group by i.referral_id " ,nativeQuery=true)
	List<IncomeModel>findByStatusAndIncomeTypeAndReferralId(@Param("status")Boolean status,@Param("incomeType") String incomeType,@Param("referralId")Long referralId);
	
	@Query(value="select *  from Income i where i.status= ?1 and i.income_type= ?2 And  i.referral_id =?3 And i.node=?4 group by i.referral_id ,i.node",nativeQuery=true)
	List<IncomeModel>findByStatusAndIncomeTypeAndNode(@Param("status")Boolean status,@Param("incomeType") String incomeType,@Param("referralId")Long referralId,@Param("node")String node);
}
