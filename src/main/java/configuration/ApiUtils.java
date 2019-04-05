package configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ApiUtils {
    private final String ENDPOINT_URL = "baseApiUrl";
    private Properties properties = new Properties();
    private String appConfigPath;

    public ApiUtils() {

//        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        String appConfigPath = rootPath + "default-api.properties";
        this.appConfigPath = "C:\\Users\\iaroslav.liabogov\\IdeaProjects\\apiTest\\src\\test\\resources\\conf\\default-api.properties";
        try {
            File file = new File(appConfigPath);
            FileInputStream fileInput = new FileInputStream(file);
            properties.load(fileInput);
            fileInput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getApiUrl() {
        String apiUrl = properties.getProperty(ENDPOINT_URL);
//        log.info("Endpoint is set for " + type + " on url " + apiUrl);
        return apiUrl;
    }

}
