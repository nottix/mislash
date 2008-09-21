package slash.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

public class DataWriter {

	private static String path = "./tmp/";
	private static String ext = "";
	
	private static Hashtable<String, Integer> counterTable = new Hashtable<String, Integer>();
	
	public static void writeData(String filename, float data) {
		File f=new File(path+filename+ext);

		if(f.exists()){
			try {
				FileWriter fstream = new FileWriter(path+filename+ext, true);
				BufferedWriter out = new BufferedWriter(fstream);
				Integer counter = counterTable.get(filename);
				counter++;
				counterTable.put(filename, counter);
				out.append(String.valueOf(counter+"\t"+data+"\n"));
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				FileWriter fstream = new FileWriter(path+filename+ext);
				BufferedWriter out = new BufferedWriter(fstream);
				Integer counter = 0;
				counterTable.put(filename, Integer.valueOf(counter));
				out.write(String.valueOf(counter+"\t"+data+"\n"));
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		for(int i=0; i<50; i++) {
			writeData("test", 2.02f);
		}
	}
}
