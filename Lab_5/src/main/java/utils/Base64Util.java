package utils;


import java.util.Base64;

public class Base64Util {
	// mã hóa dạng base64
	public static String encode(String value) {
		byte[] bytes= value.getBytes();
		String encodedText= Base64.getEncoder().encodeToString(bytes);
		return encodedText;
	}
	
	public static String decode(String value) {
		byte[] bytes= Base64.getDecoder().decode(value);
		String decodedText= new String(bytes);
		return decodedText;
	}

}