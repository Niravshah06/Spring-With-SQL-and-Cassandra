package com.foobar.foo.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(value = "OHLCDaily")
public class OHLCDaily {

	@PrimaryKey
	private ChartPK id;

	@Column(value = "open")

	private BigDecimal open;
	@Column(value = "high")
	private BigDecimal high;
	@Column(value = "low")
	private BigDecimal low;
	@Column(value = "close")
	private BigDecimal close;
	@Column(value = "volume")
	private BigDecimal volume;

	public void settId(ChartPK id) {
		this.id = id;
	}

	/**
	 * @return the open
	 */
	public BigDecimal getOpen() {
		return open;
	}

	/**
	 * @param open
	 *            the open to set
	 */
	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	/**
	 * @return the high
	 */
	public BigDecimal getHigh() {
		return high;
	}

	/**
	 * @param high
	 *            the high to set
	 */
	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	/**
	 * @return the low
	 */
	public BigDecimal getLow() {
		return low;
	}

	/**
	 * @param low
	 *            the low to set
	 */
	public void setLow(BigDecimal low) {
		this.low = low;
	}

	/**
	 * @return the close
	 */
	public BigDecimal getClose() {
		return close;
	}

	/**
	 * @param close
	 *            the close to set
	 */
	public void setClose(BigDecimal close) {
		this.close = close;
	}

	/**
	 * @return the timeStamp
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	public Timestamp getTimeStamp() {
		return id.getTimeStamp();
	}

	/**
	 * @param timeStamp
	 *            the timeStamp to set
	 */
	public void setTimeStamp(Timestamp timeStamp) {
		this.id.setTimeStamp(timeStamp);
	}

	/**
	 * @return the volume
	 */
	public BigDecimal getVolume() {
		return volume;
	}

	/**
	 * @param volume
	 *            the volume to set
	 */
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

}