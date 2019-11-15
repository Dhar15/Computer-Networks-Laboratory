/*RSA (Rivest–Shamir–Adleman) is an algorithm used by modern computers to encrypt and decrypt messages. 
  It is an asymmetric cryptographic algorithm.
  RSA involves a public key and private key. The public key can be known to everyone; it is used to encrypt messages. 
  Messages encrypted using the public key can only be decrypted with the private key. */

import java.io.*;
import java.util.*;
import java.math.BigInteger;
public class RSA {
	private BigInteger p;
	private BigInteger q;
	private BigInteger N;
	private BigInteger e;
	private BigInteger d;
	private BigInteger phi;
	private int bitLength = 1024;
	private Random r;
	RSA() 
	{
		r = new Random();
		p = BigInteger.probablePrime(bitLength, r);
		q = BigInteger.probablePrime(bitLength, r);
		N = p.multiply(q);
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		e = BigInteger.probablePrime(bitLength/2, r);
		while(phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
			e.add(BigInteger.ONE);
		}
		d = e.modInverse(phi);
	}
	public RSA(BigInteger e, BigInteger d, BigInteger N) {
		this.e = e;
		this.d = d;
		this.N = N;
	}
	public static void main(String[] args) throws IOException
	{
		RSA rsa = new RSA();
		DataInputStream in = new DataInputStream(System.in);
		System.out.println("Enter the plain text:");
		String textString = in.readLine();
		System.out.println("String in bytes: " + bytesToString(textString.getBytes()));
		byte[] encrypted = rsa.encrypt(textString.getBytes());
		System.out.println("Encrypted String: " +  encrypted);
		byte[] decrypted = rsa.decrypt(encrypted);
		System.out.println("Decrypting bytes: " + bytesToString(decrypted));
		System.out.println("Decrypted String: " + new String(decrypted));
	}
	
	private static String bytesToString(byte[] encrypted) {
		String test="";
		for(byte b: encrypted) {
			test += Byte.toString(b);
		}
		return test;
	}
	
	private byte[] encrypt(byte[] message) {
		return (new BigInteger(message).modPow(e, N)).toByteArray();
	}
	
	private byte[] decrypt(byte[] message) {
		return (new BigInteger(message).modPow(d, N)).toByteArray();
	}
}
