package com.haryo.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_services")
public class Service {
	
	@Id
	private int Id;
	
	@Column("SERVICE_CODE")
	private String serviceCode;
	
	@Column("SERVICE_NAME")
    private String serviceName;
	
	@Column("SERVICE_ICON")
    private String serviceIcon;
	
	@Column("SERVIICE_TARIF")
    private int serviceTariff;
}
