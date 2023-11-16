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
@Table(name ="t_banner")
public class Banner {
	
	@Id
	private Integer Id;
	
	@Column("BANNERNAME")
	private String bannerName;
	
	@Column("BANNERIMAGE")
    private String bannerImage;

    @Column("DESCRIPTION")
    private String description;

}
