package com.haryo.model.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse implements Serializable{
	private static final long serialVersionUID = -568819743879551278L;
	private String token;
}
