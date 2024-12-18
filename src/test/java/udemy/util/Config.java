package udemy.util;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Logger logger= LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PROPERTIES = "config/default.properties";
    private static Properties properties;

    public static void initialize(){
        properties = loadProperties();

        for (String key: properties.stringPropertyNames()){
            if(System.getProperties().containsKey(key)){
                properties.setProperty(key, System.getProperty(key));
            }
        }

        logger.info("Test properties");
        for (String key: properties.stringPropertyNames()){
            logger.info("{}={}", key, properties.getProperty(key));
        }
    }

    public static String get(String key){
        return properties.getProperty(key);
    }

    @SneakyThrows
    private static Properties loadProperties(){
        Properties properties = new Properties();
        try(InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTIES)){
            properties.load(stream);
        }
        return properties;
    }

}
