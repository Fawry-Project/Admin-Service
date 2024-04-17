package userAdmin.org.service.Config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY = "sDnidhlWxiu1d5ovGM8NYmSSJ1RaC//h2/baG6xpG3UOz1cuefkHoddH7cWwdN8CFf88tpPy01pa6/2NCHRQ5V0MTeSWbKHtMDmyqrTj2RYPkERrbmg/wkg0tv8UNAFy01W2ukHk0VTTMdjrQV/gj6vCBC6Ibu1wxpJrO4pW22sYvGTMxY45GScjMj8jfDV2HQB6uN4CQmlCnoZ173ryeA+6DHE1fXJnulgs1LGJnuwXMDp58JFl8XyHP4DJchOLAO6cgEnmfYLB6RwZrmQaSAZW+crWotLIDElcPtcEKNaK1Y0faeexSRiLhGZDO8yGq0eN/EdwbpHlTmgk3lU1oUvxIAwmC5L1Kx0aZT4bfBo="; 

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
      }

    private Claims extractAllClaims(String token){
        return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
      } 

    public String generateToken(
      Map<String, Object> extraClaims,
      UserDetails userDetails
  ) {
    return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
}

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
