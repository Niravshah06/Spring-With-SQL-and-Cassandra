package com.foobar.foo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foobar.foo.domain.OHLCDaily;
import com.foobar.foo.repo.OHLCDailyRepo;


@Service
public class ChartService {
	
	@Autowired
	private OHLCDailyRepo oHLCDailyRepo;
	
	
	 public List<OHLCDaily> fetchDailyUpticks(String currencySymbol,String base,String exchange) {
	        List<OHLCDaily> currencies = oHLCDailyRepo.fetchDailyUpticks(currencySymbol,base,exchange);
	        return currencies;
	    }
}
