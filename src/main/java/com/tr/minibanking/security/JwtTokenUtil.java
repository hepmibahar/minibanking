package com.tr.minibanking.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.tr.minibanking.entity.User;
import com.tr.minibanking.enums.Message;
import com.tr.minibanking.repository.UserRepository;
import com.tr.minibanking.service.UserService;

@Component
public class JwtTokenUtil {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private Long expiration;

  @Autowired
  private UserService userService;

  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("user", userDetails);
    return doGenerateToken(claims, userDetails);
  }

  private String doGenerateToken(Map<String, Object> claims, UserDetails subject) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + expiration);

    return Jwts.builder()
        .setSubject(subject.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(expiryDate)
        .addClaims(claims)
        .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS512))
        .compact();
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  public User getAuthenticatedUser() throws Exception {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      throw new Exception(Message.AUTH_FAILED);
    }

    String username;
    if (authentication.getPrincipal() instanceof UserDetails) {
      username = ((UserDetails) authentication.getPrincipal()).getUsername();
    } else {
      username = authentication.getPrincipal().toString();
    }

    UserRepository userRepository;
    return userService.findByUsername(username);
  }
}
