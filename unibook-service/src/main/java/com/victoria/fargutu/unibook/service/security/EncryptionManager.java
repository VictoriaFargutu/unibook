package com.victoria.fargutu.unibook.service.security;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class EncryptionManager {

    public String decrypt(String encryptedData) {
        Base64 decoder = new Base64();
        byte[] decodedBytes = decoder.decode(encryptedData);
        if (decodedBytes == null) {
            return null;
        }
        String decryptedData;
        try {
            decryptedData = new String(decodedBytes, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            return null;
        }

        return decryptedData;
    }

    public String encrypt(String string) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(string.getBytes(StandardCharsets.UTF_8));
            return new HexBinaryAdapter().marshal(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
