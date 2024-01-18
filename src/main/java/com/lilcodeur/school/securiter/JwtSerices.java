package com.lilcodeur.school.securiter;

import com.lilcodeur.school.modeles.Eleves;
import com.lilcodeur.school.services.ElevesServices;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

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
        final long expirationTime = currentTime + 30 * 60 * 1000;
        Map<String,String>claim = Map.of(
                "nom",eleves.getNom(),
                "email", eleves.getEmail()
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
}
