package prj.pingback.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PingbackConfig
{
    private static Properties _properties = new Properties();
    private static Logger _logger = LoggerFactory.getLogger(PingbackConfig.class.getSimpleName());
    private static String _configFilePath = "config/config.properties";

    public static void loadEnv() throws IOException
    {
        _properties = load(_configFilePath);
    }

    private static Properties load(String configFilePath) throws IOException
    {
        Properties props = new Properties();
        if (configFilePath != null && configFilePath.length() > 0)
        {
            File f = new File(configFilePath);
            FileInputStream inputStream = new FileInputStream(f);
            props.load(inputStream);
        }
        else
        {
            _logger.info("loading default config file. could not find the mentioned config file");
            InputStream inputStream = PingbackConfig.class.getClassLoader().getResourceAsStream("config._properties");
            props.load(inputStream);
        }
        return props;
    }

    public static int getPort()
    {
        return Integer.parseInt(_properties.getProperty("port", "80"));
    }
}
