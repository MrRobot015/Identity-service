package com.learningspringboot.springbootapp003.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    
    private final static String SECRET_KEY = "7134743777397A24432646294A404E635266556A586E3272357538782F412544";
    
    // ----------------------------------------------------------
    public String generateToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails
    ){
        // generate JWT-token with extraClaims
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 7))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    // ---------------------------------------------------------=
    public String generateToken(
            UserDetails userDetails
    ){
        // generate JWT-token without extraClaims
        return generateToken(new HashMap<>(),userDetails);
    }
    // ----------------------------------------------------------
    public Boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    // ----------------------------------------------------------
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    // ----------------------------------------------------------
    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }
    
    // ----------------------------------------------------------
    public String extractUserName (String token){
        // extract the userName from the given token
        // the subject-claim is always the (userName/userEmail)
        return extractClaim(token,Claims::getSubject);
    }
    // ----------------------------------------------------------
    public <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        // extract a specific claim
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }
    // ----------------------------------------------------------
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    // ----------------------------------------------------------
    private Key getSigningKey() {
        /* the signingKey is used to check if the sender of the request is who is claim to be */
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
}
