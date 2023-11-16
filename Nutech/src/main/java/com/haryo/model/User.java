package com.haryo.model;


import org.springframework.data.annotation.Id;

import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Lob;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_user")
public class User {

	@Id
	private Integer id;
	
	@Column(name  = "FIRSTNAME")
	private String firstName;
	
	@Column( name = "LASTNAME")
	private String lastName;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name ="PASSWORD")
	private String password;
	

	@Lob
	@Column( name = "PROFILEIMAGE")
	private String profileImage;
	
}
