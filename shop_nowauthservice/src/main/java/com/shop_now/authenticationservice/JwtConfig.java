package com.shop_now.authenticationservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {
//    @Value("${security.jwt.uri:/users/token}")
    private String uri = "/users/token";

//    @Value("${security.jwt.header:Authorization}")
    private String header = "Authorization";

//    @Value("${security.jwt.prefix:Bearer}")
    private String prefix = "Bearer";

//    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration = 24*60*60;

//    @Value("${security.jwt.secret:JwtSecretKey}")
    private String secret = "JwtSecretKey";

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int getExpiration() {
		return expiration;
	}

	public void setExpiration(int expiration) {
		this.expiration = expiration;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
}