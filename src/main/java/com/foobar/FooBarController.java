package com.foobar;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foobar.bar.domain.Bar;
import com.foobar.bar.repo.BarRepository;
import com.foobar.foo.domain.ChartPK;
import com.foobar.foo.domain.OHLCDaily;
import com.foobar.foo.repo.OHLCDailyRepo;
import com.foobar.foo.service.ChartService;

@RestController
public class FooBarController {

	private final OHLCDailyRepo bookRepo;
	private final BarRepository barRepo;

	@Autowired
	private ChartService chatservice;

	@Autowired
	FooBarController(OHLCDailyRepo bookRepo, BarRepository barRepo) {
		this.bookRepo = bookRepo;
		this.barRepo = barRepo;
	}

	@RequestMapping(value = "/addMenuItem", method = RequestMethod.GET)
	public void addMenuItem() {

		OHLCDaily o = new OHLCDaily();

		o.setClose(new BigDecimal(1));
		o.setHigh(new BigDecimal(1));
		o.setLow(new BigDecimal(1));
		o.setOpen(new BigDecimal(1));
		o.setVolume(new BigDecimal(1));

		ChartPK c = new ChartPK();
		c.setBaseCurrency("b");
		c.setCurrencySymbol("$");
		c.setExchange("ex");
		o.settId(c);

		Timestamp t = Timestamp.valueOf(LocalDateTime.now());
		o.setTimeStamp(t);
		// o.setTimeStamp(t);
		ObjectMapper mapper = new ObjectMapper();
		try {
			File f = new File("dump.json");

			List<OHLCDaily> OHLCDailyList = mapper.readValue(f, new TypeReference<List<OHLCDaily>>() {
			});
			for (OHLCDaily d : OHLCDailyList) {
				long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
				long end = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
				long diff = end - offset + 1;

				d.settId(c);
				d.setTimeStamp(new Timestamp(offset + (long) (Math.random() * diff)));
				this.bookRepo.save(d);
				System.out.println("hey " + this.bookRepo.count());

			}
			// this.bookRepo.save(OHLCDailyList);

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/getMenuItem/{currencySymbol}/{base}/{exchange}", method = RequestMethod.GET)
	public List<OHLCDaily> agetMenuItem(@PathVariable String currencySymbol, @PathVariable String base,
			@PathVariable String exchange) {
		
		List<OHLCDaily> v = this.chatservice.fetchDailyUpticks(currencySymbol, base, exchange);

		System.out.println(v.size());

		return v;

	}

	@RequestMapping("/addbar/{name}")
	public void AddBar(@PathVariable("name") String name) {
		this.barRepo.save(new Bar(name));
		System.out.println("done");

	}

}
