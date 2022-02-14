package com.entrevista.prueba.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "t_rates")
public class Trates {

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private int id;
	
	private int brand_id;
	private int product_id;
	private Date start_date;
	private Date end_date;
	private int price;
	private String currency_code;
	
	
	
	public Trates() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Trates(int id, int brand_id, int product_id, Date start_date, Date end_date, int price,
			String currency_code) {
		super();
		this.id = id;
		this.brand_id = brand_id;
		this.product_id = product_id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.price = price;
		this.currency_code = currency_code;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	
	
	
}
