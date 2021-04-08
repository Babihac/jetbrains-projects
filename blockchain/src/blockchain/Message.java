package blockchain;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;


public class Message {
	private static int counter = 1;
	private byte[] body;
	private byte[] sign;
 	private Author author;
	private int id;
	
	public Message(Author author, String body) {
		this.author = author;
		byte[] mes = body.getBytes(StandardCharsets.UTF_8);
 		this.body = mes;
		this.id = counter++;
	}
	public void sign(PrivateKey pkey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException  {
		Signature rsa = Signature.getInstance("SHA1withRSA");
		rsa.initSign(pkey);
		rsa.update(body);
		 sign = rsa.sign();
		
	}
	
	public String getSign() {
		return new String(sign, StandardCharsets.UTF_8);
	}
	public Author getAuthor() {
		return author;
	}
	
	public boolean verify(PublicKey pkey) throws Exception {
		Signature sig = Signature.getInstance("SHA1withRSA");
		sig.initVerify(pkey);
		sig.update(body);
		return sig.verify(sign);
	}
	
	public String getBody() {
		return new String(body, StandardCharsets.UTF_8);
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Message ID: " + id + "\n Author: " + author.getName() +  "\n Body: " + body;
	}

}
