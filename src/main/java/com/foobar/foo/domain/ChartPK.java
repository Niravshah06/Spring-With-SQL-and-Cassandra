package com.foobar.foo.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
@PrimaryKeyClass
public class ChartPK implements Serializable {
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKeyColumn(name = "exchange", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String exchange;
	
	  @PrimaryKeyColumn(name = "currencySymbol", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String currencySymbol;
   
	  @PrimaryKeyColumn(name = "baseCurrency", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)  
	private String baseCurrency;
   
	  @PrimaryKeyColumn(name = "timeStamp", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)  
	  private Date timeStamp;

	/**
	 * @return the exchange
	 */
	public String getExchange() {
		return exchange;
	}
	/**
	 * @param exchange the exchange to set
	 */
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

    
    @Override
    public boolean equals(Object obj) {
		return this.currencySymbol.equalsIgnoreCase(((ChartPK)obj).getCurrencySymbol()) && 
			this.baseCurrency.equalsIgnoreCase(((ChartPK)obj).getBaseCurrency()) && 
            this.timeStamp.equals(((ChartPK)obj).getTimeStamp()) &&
            this.exchange.equalsIgnoreCase(((ChartPK)obj).getExchange())  ;
    }

    @Override
    public int hashCode() {
        return exchange.hashCode() + timeStamp.hashCode()+currencySymbol.hashCode()+baseCurrency.hashCode();
    }
	/**
	 * @return the timeStamp
	 */
	public Timestamp getTimeStamp() {
		return new Timestamp(timeStamp.getTime());
	}
	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	/**
	 * @return the currencySymbol
	 */
	public String getCurrencySymbol() {
		return currencySymbol;
	}
	/**
	 * @param currencySymbol the currencySymbol to set
	 */
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
	/**
	 * @return the baseCurrency
	 */
	public String getBaseCurrency() {
		return baseCurrency;
	}
	/**
	 * @param baseCurrency the baseCurrency to set
	 */
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
}