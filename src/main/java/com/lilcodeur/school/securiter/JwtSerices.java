package com.lilcodeur.school.securiter;

import com.lilcodeur.school.modeles.Eleves;
import com.lilcodeur.school.services.ElevesServices;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class JwtSerices {
    private ElevesServices elevesServices;
    final String KEY_CRYPTE = "4294b56170a57dda2435eb2e91f6dbf28ff999a128b972cbcdfe10564bc44a09";
    public Map<String, String> generate(String userName){
        Eleves eleves = elevesServices.loadUserByUsername(userName);
        return generateJwt(eleves);
    }

    private Map<String, String> generateJwt(Eleves eleves) {
        final long currentTime = System.currentTimeMillis();
        final long expirationTime = currentTime + 2 * 60 * 1000;

        Map<String,Object>claim = Map.of(
                "nom",eleves.getNom(),
                Claims.EXPIRATION,new Date(expirationTime),
                Claims.SUBJECT,eleves.getEmail()
        );
        String compact = Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .setSubject(eleves.getEmail())
                .setClaims(claim)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        return Map.of("cle", compact);
    }

    private Key getKey() {
        byte[] decode = Decoders.BASE64.decode(KEY_CRYPTE);
        return Keys.hmacShaKeyFor(decode);
    }
    public String getUserNameToken(String token){
        return this.getClaim(token, Claims::getSubject);
    }
    public boolean isTokenExpire(String token){
        Date expirationDateToken = this.getClaim(token, Claims::getExpiration);
        return expirationDateToken.before(new Date());
    }

    private <T> T getClaim(String token, Function<Claims,T>function){
        Claims claims = getAllClainms(token);
        return function.apply(claims);
    }

    private Claims getAllClainms(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
