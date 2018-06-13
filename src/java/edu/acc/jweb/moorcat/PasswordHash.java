package edu.acc.jweb.moorcat;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHash {

    private static byte[] hashPassword(final char[] password, final byte[] salt, final int iterations, final int keyLength) {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
            SecretKey key = skf.generateSecret(spec);
            byte[] res = key.getEncoded();
            return res;
            
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private static String hex(byte[] bytes) {
        String hex = "";
        for (byte b : bytes) {
            hex = hex + String.format("%02x", b);
        }
        return hex;
    }

    private static final String STATIC_SALT = "1234"; 
    
    public static String hashPassword(String password) { 
        return hex(hashPassword(password.toCharArray(), STATIC_SALT.getBytes(), 8192, 256));
    }
}