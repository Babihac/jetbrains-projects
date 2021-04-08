package blockchain;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtils {
	public static void serialize(Object o, String filename) throws IOException {
		FileOutputStream fio = new FileOutputStream(filename);
		BufferedOutputStream bos = new BufferedOutputStream(fio);
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(o);
		oos.close();
	}
	
	public static Object deserialize(String filename) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filename);
		BufferedInputStream bis = new BufferedInputStream(fis);
		ObjectInputStream ois = new ObjectInputStream(bis);
		Object o = ois.readObject();
		ois.close();
		return o;
		
		
	}

}
