package com.haryo.model;

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
@Table(name = "userrole")
public class UserRole {
	
	@Column("EMAIL")
	private String userId;
	
	@Column("ROLEID")
	private String roleId;
}
