package core;
import java.io.*;
import java.util.Properties;

/**
 * Created by Виталий on 01.04.2017.
 */
public class Prop {


    public static String get(String property) {
        String out = "";
        InputStream file = null;
        Properties prop = new Properties();
        try {
            String propFileName = "src/main/resources/config.properties";
            file = new FileInputStream(new File(propFileName).getAbsolutePath());

            prop.load(file);
            out = prop.getProperty(property);
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return out;

    }
}
