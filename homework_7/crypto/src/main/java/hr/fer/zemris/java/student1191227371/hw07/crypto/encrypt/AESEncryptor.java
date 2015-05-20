package hr.fer.zemris.java.student1191227371.hw07.crypto.encrypt;

import hr.fer.zemris.java.student1191227371.hw07.crypto.Converter;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Razred implementira Sucelje Encryptor.
 * Kriptiranje se vrsi pomocu algoritma AES.
 * @author Jelena Drzaic
 *
 */
public class AESEncryptor implements Encryptor{

	@Override
	public void encrypt(InputStream is, OutputStream os, String keyText,
			String ivText) throws Exception{

		SecretKeySpec keySpec = new SecretKeySpec(Converter.
				hextobyte(keyText), "AES"); 
		AlgorithmParameterSpec paramSpec = new IvParameterSpec(Converter.
				hextobyte(ivText));
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE  , keySpec, paramSpec);
		byte[] dataBytes = new byte[4096];
		byte[] encrypted;
		int nread = 0; 
        while ((nread = is.read(dataBytes)) != -1) {
        	encrypted = cipher.update(dataBytes, 0, nread);
        	os.write(encrypted);
        };
        encrypted = cipher.doFinal();
        os.write(encrypted);
	}

	
}
