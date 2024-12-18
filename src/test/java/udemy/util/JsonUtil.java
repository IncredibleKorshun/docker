package udemy.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udemy.tests.vendorportal.model.VendorPortalTestData;

import java.io.InputStream;

public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(ResourceLoader.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T getTestData(String path, Class<T> type){
        try(InputStream stream = ResourceLoader.getResource(path)){
            return mapper.readValue(stream, type);
        }catch (Exception e){
            logger.error("Unable to read test data {}", path, e);
        }
        return null;
    }

}
