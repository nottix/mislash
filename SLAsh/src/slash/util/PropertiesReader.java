package slash.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	private static Properties properties = null;
	private static File file = null;
	private static FileInputStream fis = null;
	private static String ret = null;
	
	public static String getProperty(String id) {
		try {
			if(id=="" || id==null)
				return null;
			if(file==null) {
				System.out.println(System.getProperty("user.dir"));
				file = new File("conf/config.properties");
				if(!file.exists())
					file = new File(System.getProperty("user.dir")+"/conf/conf.properties");
				
				fis = new FileInputStream(file);
			}
			if(properties==null) {
				properties = new Properties();
				properties.load(fis);
				fis.close();
			}

			if((ret=properties.getProperty(id))!=null)
				return ret;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Properties getProperties() {
		if(properties == null) {
			getProperty("indexPath");
		}
		return properties;
	}
}
