package cl.villegas.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import cl.villegas.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Crypto {
    private final static Logger logger = LoggerFactory.getLogger(Crypto.class);

    public static String encrypt(String value) {
        String encrypted = "";
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(Constants.Crypto.INIT_VECTOR.getBytes(Constants.Crypto.CHARSET));
            SecretKeySpec secretKeySpec = new SecretKeySpec(Constants.Crypto.KEY.getBytes(Constants.Crypto.CHARSET), Constants.Crypto.ENCRYPT_TYPE);

            Cipher cipher = Cipher.getInstance(Constants.Crypto.ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encryptedBytes = cipher.doFinal(value.getBytes());

            encrypted = DatatypeConverter.printBase64Binary(encryptedBytes);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
            logger.error(ex.getMessage());
        }
        return encrypted;
    }

    public static String decrypt(String encrypted) {
        String decrypted = "";
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(Constants.Crypto.INIT_VECTOR.getBytes(Constants.Crypto.CHARSET));
            SecretKeySpec secretKeySpec = new SecretKeySpec(Constants.Crypto.KEY.getBytes(Constants.Crypto.CHARSET), Constants.Crypto.ENCRYPT_TYPE);

            Cipher cipher = Cipher.getInstance(Constants.Crypto.ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] original = cipher.doFinal(DatatypeConverter.parseBase64Binary(encrypted));

            decrypted = new String(original);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
            logger.error(ex.getMessage());
        }
        return decrypted;
    }
}