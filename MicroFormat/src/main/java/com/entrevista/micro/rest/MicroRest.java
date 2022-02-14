package com.entrevista.micro.rest;


import java.text.DecimalFormat;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping ("/api/micro/")
public class MicroRest {
	
	@RequestMapping (value = "/number/{number}", method = RequestMethod.GET)
	public String decimalFormat (@PathVariable ("number") Integer number) {

		double dNumber = number;
	    double roundDbl = Math.round(dNumber*100.0)/10000.0;
	    DecimalFormat df = new DecimalFormat("###.00"); 
	    String sNumber =  df.format(roundDbl);
		return sNumber;
		
	}
	
	@RequestMapping (value = "/currency/{currency}", method = RequestMethod.GET)
	public String symbology (@PathVariable ("currency") String currency) {
		String updatedCurr = "";
		if (("EUR").equals(currency)) {
			updatedCurr = "€";
		} else {
			updatedCurr = currency; 
		}
		return updatedCurr;
		
	}
}
