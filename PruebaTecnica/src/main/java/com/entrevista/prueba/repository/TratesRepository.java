package com.entrevista.prueba.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entrevista.prueba.model.Trates;

@Repository
public interface TratesRepository extends JpaRepository<Trates,Integer>{

	@Query(nativeQuery =  true, value= "select * from t_rates t where :date between t.start_date and t.end_date and t.product_id = :product_id and t.brand_id = :brand_id")
	List<Trates> findByTratesDateIdProductIdBrand (Date date,  Integer product_id, Integer brand_id);
	
}
