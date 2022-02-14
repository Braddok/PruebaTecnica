package com.entrevista.prueba.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.entrevista.prueba.model.Trates;


@Service
public interface TratesService {
	
	
	public Trates createOrUpdate (Trates trates);
	
	public List<Trates> getAllTrates();
	
	public void deleteById (Integer id);
	
	public Optional<Trates> findById (Integer id);
	
	public List<Trates> findByTratesDateIdProductIdBrand (Date date,  Integer product_id, Integer brand_id);
}
