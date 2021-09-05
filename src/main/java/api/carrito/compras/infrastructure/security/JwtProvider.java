package api.carrito.compras.infrastructure.security;

import api.carrito.compras.domain.exception.ApiException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import static io.jsonwebtoken.Jwts.parser;

@Service
public class JwtProvider {

    @Value("${jsk.secret.password}")
    private String secret;

    @Value("${jwt.expiration.time}")
    private Long jwtExpirationTimeMillis;

    private KeyStore keyStore;

    /**
     * Init.
     */
    @PostConstruct
    public void init(){
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/carritocompras.jks");
            keyStore.load(resourceAsStream, secret.toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new ApiException("Ocurrió una excepción mientras se cargaba el almacen de claves", e);
        }
    }

    /**
     * Generación del Jwt token.
     *
     * @param authentication the authentication
     * @return the string
     */
    public String generateToken(Authentication authentication){
        User principal = (User)  authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationTimeMillis)))
                .compact();
    }

    /**
     * Obtener la llave privada
     */
    private Key getPrivateKey(){
        try {
            return keyStore.getKey("carritocompras", secret.toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e){
            throw new ApiException("Ocurrió una excepción mientras se obtenia la llave publica del baúl de claves", e);
        }
    }

    /**
     * Generar el token con el username.
     *
     * @param username the username
     * @return the string
     */
    public String generateTokenWithUsername(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationTimeMillis)))
                .compact();
    }

    /**
     * Obtener la expiración del token en milisegundos.
     *
     * @return the long
     */
    public Long getJwtExpirationTimeMillis(){
        return jwtExpirationTimeMillis;
    }

    /**
     * Validar el token.
     *
     * @param jwt the jwt
     * @return the boolean
     */
    public boolean validateToken(String jwt){
        parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
        return true;
    }

    /**
     * Obtener la llave publica.
     *
     * @return the public key
     */
    public PublicKey getPublicKey(){
        try {
            return keyStore.getCertificate("carritocompras").getPublicKey();
        } catch (KeyStoreException keyStoreException){
            throw new ApiException("Ocurrió una excepción mientras se obtenia la llave pública", keyStoreException);
        }
    }

    /**
     * Obtener el username a través del JWT.
     *
     * @param jwt the jwt
     * @return the string
     */
    public String getUsernameFromJwt(String jwt){
        Claims claims = parser()
                .setSigningKey(getPublicKey())
                .parseClaimsJws(jwt)
                .getBody();
        return claims.getSubject();
    }
}