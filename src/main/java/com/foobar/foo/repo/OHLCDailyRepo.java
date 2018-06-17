package com.foobar.foo.repo;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foobar.foo.domain.OHLCDaily;

@Repository
public interface OHLCDailyRepo extends CassandraRepository<OHLCDaily> {
	  @Query("select * from OHLCDaily  where  currencySymbol=?0  and baseCurrency=?1 and exchange=?2 ALLOW FILTERING")
	    List<OHLCDaily> fetchDailyUpticks(@Param("currencySymbol") String currencySymbol,@Param("base") String base,@Param("exchange") String exchange);

	
}