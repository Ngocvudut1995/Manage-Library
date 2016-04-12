/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/**
 *
 * @author Nguyen
 */
public class Encode {

    private PrivateKey priKey;
    private PublicKey pubKey;

    public Encode() {
        getKeys();
    }

    private void getKeys() {
        try {
            File pubKeyFile = new File("src/xml/pub.key");
            File privKeyFile = new File("src/xml/pri.key");;
            // đọc dữ liệu từ file và khởi tọa lại khóa công khai
            DataInputStream dis = new DataInputStream(new FileInputStream(pubKeyFile));
            byte[] pubKeyBytes = new byte[(int) pubKeyFile.length()];
            dis.readFully(pubKeyBytes);
            dis.close();

            // đọc dữ liệu từ file và khởi tọa lại khóa mật
            dis = new DataInputStream(new FileInputStream(privKeyFile));
            byte[] privKeyBytes = new byte[(int) privKeyFile.length()];
            dis.read(privKeyBytes);
            dis.close();
            //

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // Tạo khóa công khai

            X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(pubKeyBytes);
            pubKey = (RSAPublicKey) keyFactory.generatePublic(pubSpec);
            // tạo khóa mật

            PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(privKeyBytes);
            priKey = (RSAPrivateKey) keyFactory.generatePrivate(privSpec);
        } catch (Exception ex) {
            System.out.println("ERR: " + ex.toString());
        }
    }

    // hàm mã hóa
    public byte[] TwowayEncrypt(byte[] data) {
        try {
            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.ENCRYPT_MODE, priKey);
            byte[] rs = c.doFinal(data);
            return rs;
        } catch (Exception ex) {
            System.out.println("Err: " + ex.toString());
        }
        return null;
    }

    // hàm giải mã
    public byte[] TwowayDecrypt(byte[] data) {
        try {
            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.DECRYPT_MODE, pubKey);
            byte[] rs = c.doFinal(data);
            return rs;
        } catch (Exception ex) {
            System.out.println("Err: " + ex.toString());
        }
        return null;
    }

    // dùng cho mã hóa mật khẩu
    public byte[] OnewayEncrypt(byte[] data) {
        try {
            MessageDigest dig = MessageDigest.getInstance("MD5");
            return dig.digest(data);
        } catch (Exception ex) {
            System.out.println("ERR: " + ex.toString());
        }
        return null;
    }
    
    public static String getSecurePassword(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    //Add salt
    public static String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt.toString();
    }
    
    public static byte[] computeHash(String x)
            throws Exception {
        java.security.MessageDigest d = null;
        d = java.security.MessageDigest.getInstance("SHA-1");
        d.reset();
        d.update(x.getBytes());
        return d.digest();
    }

    public static String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }
}
