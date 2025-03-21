package com.bci.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDTO {
    private long number;
    private int cityCode;
    private String countryCode;

    public PhoneDTO() {}

    public PhoneDTO(long number, int cityCode, String countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }
}