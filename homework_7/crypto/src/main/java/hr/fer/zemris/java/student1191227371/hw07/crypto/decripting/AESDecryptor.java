package hr.fer.zemris.java.student1191227371.hw07.crypto.decripting;

import hr.fer.zemris.java.student1191227371.hw07.crypto.Converter;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Razred implementira sucelje Decryptor
 * @author Jelena DRZAIC
 *
 */
public class AESDecryptor implements Decryptor{

	@Override
	public void decrypt(InputStream is, OutputStream os, String keyText,
			String ivText) throws Exception{
	
		SecretKeySpec keySpec = new SecretKeySpec(Converter.
				hextobyte(keyText), "AES"); 
		AlgorithmParameterSpec paramSpec = new IvParameterSpec(Converter.
				hextobyte(ivText));
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
		byte[] dataBytes = new byte[4096];
		byte[] decrypted;
		int nread = 0; 
        while ((nread = is.read(dataBytes)) != -1) {
        	byte[] result = cipher.update(dataBytes, 0, nread);
        	os.write(result);
        };
        decrypted = cipher.doFinal();
        os.write(decrypted);
	}
	
}
