package helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public final class ConfigHelper {
    private static volatile ConfigHelper instance;
    private Properties props;

    private ConfigHelper() {
        props = new Properties();
        readProps();
    }

    public static ConfigHelper getInstance() {
        if (instance == null) {
            synchronized (ConfigHelper.class) {
                if (instance == null) {
                    instance = new ConfigHelper();
                }
            }
        }
        return instance;
    }

    private void readProps() {
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Unable to find config.properties");
                return;
            }
            props.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Unable to read config.properties");
        }
    }

    public String getBaseUrl() {
        Set<Object> environments = props.keySet();
        String env = System.getProperty("env", "test");
        if (!environments.contains(env)) {
            throw new IllegalArgumentException(String.format("Invalid arg passed for env = %s \n" +
                "Available args are: %s", env.equals("") ? "<not set>" : env, environments));
        }
        String baseUrl = props.getProperty(env);
        System.out.println(String.format("Tests will run on %s (%s) environment", env, baseUrl));
        return baseUrl;
    }
}
