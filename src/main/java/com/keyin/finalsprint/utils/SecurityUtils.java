package com.keyin.finalsprint.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

public class SecurityUtils {

    public static byte[] createSalt(int length) {
        byte[] salt = new byte[length];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    /**
     * Returns a BASE64 encoded string of the hashed input with the inputs
     * @param input The input text
     * @param salt The salt to has the input with
     * @param length The length of the hash
     * @return The hash
     */
    public static String hash(String input, byte[] salt, int length) {
        try {
            KeySpec spec = new PBEKeySpec(input.toCharArray(), salt, Short.MAX_VALUE, length);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return Base64.getEncoder().encodeToString(factory.generateSecret(spec).getEncoded());
        }catch (Exception e) {
            System.out.println("Error encoding password, " + e.getMessage());
            return null;
        }
    }
}
