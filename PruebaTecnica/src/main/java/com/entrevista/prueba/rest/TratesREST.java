package com.entrevista.prueba.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.entrevista.prueba.model.FormatedTrates;
import com.entrevista.prueba.model.Trates;
import com.entrevista.prueba.repository.TratesRepository;
import com.entrevista.prueba.service.TratesService;

@RestController
@RequestMapping("/api/trates/")
public class TratesREST {

	@Autowired
	private TratesService tratesService;
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String showHello() {

		return "hello";
	}

	@PostMapping
	public ResponseEntity<Trates> create(@RequestBody Trates trates) {

		try {
			Trates newTrates = tratesService.createOrUpdate(trates);
			return ResponseEntity.ok(newTrates);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping
	public ResponseEntity<List<Trates>> listAll() {
		return ResponseEntity.ok(tratesService.getAllTrates());

	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> deleteTratesById(@PathVariable("id") Integer id) {
		try {
			tratesService.deleteById(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<FormatedTrates> tratesForId(@PathVariable("id") Integer id) {

		FormatedTrates formatedTrates = new FormatedTrates();
		Optional<Trates> optionalTrate = tratesService.findById(id);

		if (optionalTrate.isPresent()) {
			try {
				Trates updateTrates = optionalTrate.get();
				String dPrecio = restTemplate.getForObject(
						"http://localhost:8081/api/micro/number/" + updateTrates.getPrice(), String.class);
				String currency = restTemplate.getForObject(
						"http://localhost:8081/api/micro/currency/" + updateTrates.getCurrency_code(), String.class);
				formatedTrates.setId(updateTrates.getId());
				formatedTrates.setBrand_id(updateTrates.getBrand_id());
				formatedTrates.setStart_date(updateTrates.getStart_date());
				formatedTrates.setEnd_date(updateTrates.getEnd_date());
				formatedTrates.setProduct_id(updateTrates.getProduct_id());
				formatedTrates.setPrice(dPrecio);
				formatedTrates.setCurrency_code(currency);

				return ResponseEntity.ok(formatedTrates);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PutMapping(value = "{id}/{price}")
	public ResponseEntity<Trates> updateTrates(@PathVariable Integer id, @PathVariable Integer price) {
		Optional<Trates> optionalTrate = tratesService.findById(id);

		if (optionalTrate.isPresent()) {
			Trates updateTrates = optionalTrate.get();
			updateTrates.setPrice(price);

			try {
				tratesService.createOrUpdate(updateTrates);
				return ResponseEntity.ok(updateTrates);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "{date}/{product_id}/{brand_id}")
	public ResponseEntity<List<FormatedTrates>> findTratesDateIdProductIdBrand(@PathVariable("date") String sDate,
			@PathVariable("product_id") Integer product_id, @PathVariable("brand_id") Integer brand_id) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		FormatedTrates formatedTrates = new FormatedTrates();
		List<FormatedTrates> listFormated = new ArrayList();
		try {
			Date date = format.parse(sDate);
			List<Trates> listTrates = tratesService.findByTratesDateIdProductIdBrand(date, product_id, brand_id);
			for (Trates trates : listTrates) {
				String dPrecio = restTemplate
						.getForObject("http://localhost:8081/api/micro/number/" + trates.getPrice(), String.class);
				String currency = restTemplate.getForObject(
						"http://localhost:8081/api/micro/currency/" + trates.getCurrency_code(), String.class);
				formatedTrates.setId(trates.getId());
				formatedTrates.setBrand_id(trates.getBrand_id());
				formatedTrates.setStart_date(trates.getStart_date());
				formatedTrates.setEnd_date(trates.getEnd_date());
				formatedTrates.setProduct_id(trates.getProduct_id());
				formatedTrates.setPrice(dPrecio);
				formatedTrates.setCurrency_code(currency);

				listFormated.add(formatedTrates);
			}

			return ResponseEntity.ok(listFormated);
		} catch (ParseException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

}
