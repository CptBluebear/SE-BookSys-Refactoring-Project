package org.corodiak.booksys.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
 * 로그인 관련 JWT Token 생성 클래스
 */
public class JWTUtil
{
	//Token Key
	public static final String key="TESTKEY";
	
	/*
	 * 토큰 생성, payload에 든 데이터를 토큰에 기입
	 */
	public static String createToken(Map<String, Object> payload)
	{
		Map<String, Object> header = new HashMap<String, Object>();
		header.put("typ", "JWT");
		header.put("alg", "HS256");
		
		Long expiredTime = 1000 * 60l * 60l * 24l * 365l;
	    Date now = new Date();
	    now.setTime(now.getTime() + expiredTime);    
	    //payload.put("exp", now);
	    
	    String jwt = Jwts.builder()
	    		.setHeader(header)
	    		.setClaims(payload)
	    		.setExpiration(now)
	    		.signWith(SignatureAlgorithm.HS256, key.getBytes())
	    		.compact();
		
		return jwt;
	}
	
	/*
	 * 토큰 검증
	 */
	public static Map<String, Object> validateToken(String token)
	{
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(key.getBytes())
					.parseClaimsJws(token)
					.getBody();
			
			return claims;
		} catch(Exception e) {
			return null;
		}
	}
	
}
