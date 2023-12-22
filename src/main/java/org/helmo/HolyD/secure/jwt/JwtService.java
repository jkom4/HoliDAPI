package org.helmo.HolyD.secure.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.helmo.HolyD.secure.userDetails.UserDTODetails;
import org.helmo.HolyD.secure.userDetails.UserDTODetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final String KEY_ENCRYPTION = "fec513c64ae6796c961c76aa80abfdf4f90fea8a6061110a15c262c3547548e375d41c288e8348e11ccd0cf2ddbfb302f9a6de9a07b847eee91ebc2bafcae1b5";

    private final UserDTODetailsService userDTODetailsService;

    public JwtService(UserDTODetailsService userDTODetailsService) {
        this.userDTODetailsService = userDTODetailsService;
    }

    public String generate(String email){
        return this.generate((UserDTODetails) userDTODetailsService.loadUserByUsername(email));
    }
    public String extractUsername(String token) {
        try {
            return getClaim(token, Claims::getSubject);
        }catch (ExpiredJwtException ex){
            return null;
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            return getClaim(token, Claims::getExpiration).before(new Date());
        }catch (ExpiredJwtException ex){
            return true;
        }
    }
    private String generate(UserDTODetails userDTODetails){
        final long currentTime = System.currentTimeMillis();
        final long expirationTime = currentTime + 60 * 60 * 1000; // 1 Heure

        final Map<String, Object> claims = Map.of(
                Claims.ID, String.valueOf(userDTODetails.getId()),
                "nom", userDTODetails.getNom(),
                "prenom", userDTODetails.getPrenom(),
                Claims.SUBJECT, userDTODetails.getEmail(),
                Claims.EXPIRATION, new Date(expirationTime)
        );

        return Jwts.builder()
                .issuedAt(new Date(currentTime))
                .expiration(new Date(expirationTime))
                .subject(userDTODetails.getEmail())
                .claims(claims)
                .signWith(getKey())
                .compact();
    }

    private Key getKey() {
        final byte[] decoder = Decoders.BASE64.decode(KEY_ENCRYPTION);
        return Keys.hmacShaKeyFor(decoder);
    }

    private <T> T getClaim(String token, Function<Claims, T> function) {
        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
