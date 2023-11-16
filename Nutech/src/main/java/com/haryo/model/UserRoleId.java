package com.haryo.model;



import org.springframework.data.relational.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoleId {
	@Column("Email")
	private String userId;

	@Column("ROLEID")
	private String roleId;
}
