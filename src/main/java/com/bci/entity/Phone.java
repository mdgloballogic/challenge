package com.bci.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PHONES")
public class Phone {

    @Id
    @Column(name = "ID")
    private UUID id = UUID.randomUUID();

    @Column(name = "NUMBER")
    private long number;

    @Column(name = "CITY_CODE")
    private int cityCode;

    @Column(name = "COUNTRY_CODE")
    private String countryCode;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
    
}
