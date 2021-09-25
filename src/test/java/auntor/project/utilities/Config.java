package auntor.project.utilities;

import java.io.*;
import java.util.Properties;

public class Config {

	Properties pro;
	File src = new File("./Configuration/config.properties");
	
	public Config() {
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		}catch(Exception e) {
			System.out.println("Exception is "+e.getMessage());
		}
	}

	
	public String getApkName() {
		String apk  = pro.getProperty("ApkName");
		return apk;
	}

	public String getDeviceType() {
		String deviceType  = pro.getProperty("device");
		return deviceType;
	}

}
