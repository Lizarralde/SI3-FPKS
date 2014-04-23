package jeu.config;

import jeu.KeysKeeper;

import java.io.*;
import java.util.Properties;

public class Config {

    private static Properties properties;

    static {

        if (properties == null) {

            properties = new Properties();

            InputStream in = null;

            try {
                in = new FileInputStream(KeysKeeper.PATH_RESSOURCES
                        + KeysKeeper.PATH_CONFIG + "config.properties");

                properties.load(in);
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            } finally {

                if (in != null) {

                    try {

                        in.close();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static String getProperty(String key) {

        return properties.getProperty(key);
    }

    public static void setProperty(String key, String value) {

        properties.setProperty(key, value);
    }

    public static void store() {

        OutputStream out = null;

        try {

            out = new FileOutputStream(KeysKeeper.PATH_RESSOURCES
                    + KeysKeeper.PATH_CONFIG + "config.properties");

            properties.store(out, null);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } finally {

            if (out != null) {

                try {

                    out.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }
}
