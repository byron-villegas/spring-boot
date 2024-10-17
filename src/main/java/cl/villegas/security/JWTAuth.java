package cl.villegas.security;

import java.text.ParseException;

import cl.villegas.constants.Constants;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cl.villegas.enums.TokenState;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.ReadOnlyJWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

public class JWTAuth {
    private final static Logger logger = LoggerFactory.getLogger(JWTAuth.class);

    public static String create(String subject) {
        JWTClaimsSet claimsSet = new JWTClaimsSet();
        claimsSet.setSubject(subject);
        claimsSet.setIssueTime(DateTime.now().toDate());
        claimsSet.setExpirationTime(DateTime.now().plusMinutes(Constants.Token.EXPIRATION).toDate());
        JWSSigner signer = new MACSigner(Constants.Token.SECRET);
        SignedJWT signed = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        try {
            signed.sign(signer);
        } catch (JOSEException ex) {
            logger.error(ex.getMessage());
        }
        return signed.serialize();
    }

    public static ReadOnlyJWTClaimsSet decode(String header) {
        ReadOnlyJWTClaimsSet readOnlyClaimsSet = null;
        try {
            SignedJWT signed = SignedJWT.parse(header);
            if (signed.verify(new MACVerifier(Constants.Token.SECRET)))
                readOnlyClaimsSet = signed.getJWTClaimsSet();
            else
                return null;
        } catch (ParseException | JOSEException ex) {
            logger.error(ex.getMessage());
        }
        return readOnlyClaimsSet;
    }

    public static TokenState validate(String token) {
        ReadOnlyJWTClaimsSet readOnlyClaimsSet = decode(token);

        if (readOnlyClaimsSet == null)
            return TokenState.INVALIDO;

        DateTime date = new DateTime();
        int diference = Minutes.minutesBetween(new DateTime(readOnlyClaimsSet.getExpirationTime()), date).getMinutes();

        if (diference > Constants.Token.BEFORE && diference < Constants.Token.AFTER)
            return TokenState.REFRESH;
        else if (diference > Constants.Token.EXPIRATION)
            return TokenState.INVALIDO;
        else
            return TokenState.OK;
    }

    public static String get(String token) {
        ReadOnlyJWTClaimsSet readOnlyClaimsSet = decode(token);
        return readOnlyClaimsSet.getSubject();
    }

    public static String refreshToken(String token) {
        ReadOnlyJWTClaimsSet readOnlyClaimsSet = decode(token);
        return create(readOnlyClaimsSet.getSubject());
    }
}