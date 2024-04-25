package com.bci.security;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseHandler {
    
	private LocalDate timeStamp;
    private String code;
    private String detailMessage;
    
}
