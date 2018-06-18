package resources.utils;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesValue{
    
    static Properties prop = new Properties();
    private String propertiespath;
    private String key;
    static String value = ""; 
    
	// Constructor
    public PropertiesValue() throws Exception {
    }
    
    public String getPropertiesValue() throws Throwable{
    	FileInputStream inputStream = null;
    	try {
    		inputStream = new FileInputStream(this.propertiespath);
	    	prop.load(inputStream);
	    	String value = prop.getProperty(this.key);
	    	return value;
    	}catch (Exception e) {
    		System.out.println("Exception: " + e);
    	} finally {
    		inputStream.close();
		}
    	return value;
    }
    
    public void setKey(String propfile) {
		this.key = propfile;
	}
   
    public void setPropertiesFilePath(String propfilepath) {
		this.propertiespath = propfilepath;
	}
	
    public String getKey() {
		return this.key;
	}
   
    public String getPropertiesFilePath() {
		return this.propertiespath;
	}
}
