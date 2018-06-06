package com.jlzb.storedownload.http;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MD5Util {

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	  public static String b(byte[] paramArrayOfByte)
	  {
	    try
	    {
	      byte[] arrayOfByte = MessageDigest.getInstance("MD5").digest(paramArrayOfByte);
	      StringBuffer localStringBuffer = new StringBuffer();
	      int i = 0;
	      for (;;)
	      {
	        if (i >= arrayOfByte.length) {
	          return localStringBuffer.toString();
	        }
	        int j = arrayOfByte[i] & 0xFF;
	        if (j < 16) {
	          localStringBuffer.append("0");
	        }
	        localStringBuffer.append(Integer.toHexString(j));
	        i += 1;
	      }
	    }
	    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
	    {
	      localNoSuchAlgorithmException.printStackTrace();
	    }
	    return String.valueOf(Arrays.hashCode(paramArrayOfByte));
	  }
	  
	  
	  public static String md5Hex(String paramString)
	  {
	    byte[] a = md5(paramString);
	    if (a == null) {
	      return "";
	    }
	    return toHex(a);
	  }
	  
	  public static final String toHex(byte[] paramArrayOfByte)
	  {
	    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 2);
	    int j = paramArrayOfByte.length;
	    int i = 0;
	    while (i < j)
	    {
	      int k = paramArrayOfByte[i];
	      localStringBuilder.append(hexDigits[(k >> 4 & 0xF)]).append(hexDigits[(k & 0xF)]);
	      i += 1;
	    }
	    return localStringBuilder.toString();
	  }
	  public static byte[] md5(String paramString)
	  {
	    try
	    {
	    	byte[] a = MessageDigest.getInstance("md5").digest(paramString.getBytes());
	      return a;
	    }
	    catch (NoSuchAlgorithmException e)
	    {
	      e.printStackTrace();
	    }
	    return null;
	  }
	  
}
