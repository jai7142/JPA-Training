package com.jpa.shipment.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//@ApplicationScoped
//@Named
public class PropertyLoader {

    public Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("Property file not found: " + fileName);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file: " + fileName, e);
        }
        return properties;
    }   
    	public static void main(String[] args) {
            
    		/*try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
               PropertyLoader loader = container.select(PropertyLoader.class).get();*/
			PropertyLoader loader = new PropertyLoader();
               System.out.println(loader!=null);
               System.out.println(loader.loadProperties("application.properties"));
				// }
    }
}