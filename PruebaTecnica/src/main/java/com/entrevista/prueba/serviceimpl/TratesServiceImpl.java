package com.entrevista.prueba.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.entrevista.prueba.model.Trates;
import com.entrevista.prueba.repository.TratesRepository;
import com.entrevista.prueba.service.TratesService;

@Service
public class TratesServiceImpl implements TratesService {
	
	@Autowired
	private TratesRepository tratesRepository;
	private Trates trates;
	private EntityManager em;
	
	
	public Trates createOrUpdate (Trates trates) {
		return tratesRepository.save(trates);
	}
	
	public List<Trates> getAllTrates() {
		return tratesRepository.findAll();		
	}
	
	public void deleteById (Integer id) {
		tratesRepository.deleteById(id);
	}
	
	public Optional<Trates> findById (Integer id) {
		return tratesRepository.findById(id);
	}
	
	public List<Trates> findByTratesDateIdProductIdBrand (Date date,  Integer product_id, Integer brand_id) {
		return tratesRepository.findByTratesDateIdProductIdBrand(date, product_id, brand_id);
	}

}
