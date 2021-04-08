package blockchain;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.KeyGenerator;

import encryption.KeysGenerator;

public class Author {
	private String name;
	private PrivateKey privatekey;
	private PublicKey publicKey;
	private KeyPair pair;
	
	public Author(String name) {
		this.name = name;
		try {
			pair = KeysGenerator.createKeys(512);
			privatekey = pair.getPrivate();
			publicKey = pair.getPublic();
		} catch(NoSuchAlgorithmException | NoSuchProviderException e) {
			e.printStackTrace();
		}
	}
	
	
	public synchronized Message sendMessage(String body) {
		Message mes = new Message(this, body);
		try {
			mes.sign(privatekey);
		} catch(Exception e) {
			
		}
		return mes;
	}
	
	public PublicKey getPublicKey() {
		return publicKey;
	}
	
	public String getName() {
		return name;
	}
	
}
