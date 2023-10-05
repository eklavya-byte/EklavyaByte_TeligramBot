package org.prem.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Credentials {

    public static String botName() {
        String botName;
        Properties prop = new Properties();
        FileInputStream fInput = null;
        try{
            fInput = new FileInputStream("src/main/resources/config.properties");
            prop.load(fInput);

        }catch (FileNotFoundException e){
            e.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            //reading bot name from config.property
            botName = prop.getProperty("BOT_NAME");
            try{
                if(fInput != null){
                    fInput.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(prop != null){
                prop.clear();
            }
        }
        return botName;
    }

    public static String token() {
        String token;
        Properties prop = new Properties();
        FileInputStream fInput = null;
        try{
            fInput = new FileInputStream("src/main/resources/config.properties");
            prop.load(fInput);

        }catch (FileNotFoundException e){
            e.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            //reading bot name from config.property
            token = prop.getProperty("TOKEN");
            try{
                if(fInput != null){
                    fInput.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(prop != null){
                prop.clear();
            }
        }
        return token;
    }

    public static String getWeatherApiKey() {
        String weatherApi;
        Properties prop = new Properties();
        FileInputStream fInput = null;
        try{
            fInput = new FileInputStream("src/main/resources/config.properties");
            prop.load(fInput);

        }catch (FileNotFoundException e){
            e.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            //reading bot name from config.property
            weatherApi = prop.getProperty("WEATHER_API");
            try{
                if(fInput != null){
                    fInput.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(prop != null){
                prop.clear();
            }
        }
        return weatherApi;
    }

    public static String getPixabayApiKey() {
        String pixabayApi;
        Properties prop = new Properties();
        FileInputStream fInput = null;
        try{
            fInput = new FileInputStream("src/main/resources/config.properties");
            prop.load(fInput);

        }catch (FileNotFoundException e){
            e.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            //reading bot name from config.property
            pixabayApi = prop.getProperty("PIXABAY_API");
            try{
                if(fInput != null){
                    fInput.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(prop != null){
                prop.clear();
            }
        }
        return pixabayApi;
    }

}
