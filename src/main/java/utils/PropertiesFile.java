package utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class PropertiesFile {
	
	private static final Logger LOG = LogManager.getLogger(PropertiesFile.class);
	private static FileInputStream fis;;
	private static Properties prop = null;

	public static String getPropertys ( String property ) {

		try {
			fis = new FileInputStream(new File(System.getProperty("user.dir")+"config.properties"));
			prop = new Properties();
			prop.load(fis);
		} catch(FileNotFoundException fnfe) {
			LOG.error("Properties File Not Found", fnfe);
		} catch(IOException ioe) {
			LOG.error("IO Exception while loading Properties File", ioe);
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				LOG.error("IO Exception while closing file input stream", e);
			}
		}
		return prop.getProperty(property).trim();
	}
}
