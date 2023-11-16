package com.haryo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceDto {
	
	private String serviceCode;
    private String serviceName;
    private String serviceIcon;
    private int serviceTariff;
    
}
