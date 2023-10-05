package org.prem.WebSerches;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.prem.connection.Credentials;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Weather {

    private static final String API_KEY = Credentials.getWeatherApiKey();
    //final static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static String simpleGet() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String uri = String.format("https://api.openweathermap.org/data/2.5/weather?lat=26.2243&lon=84.36&appid=%s&units=metric",API_KEY);
        HttpGet httpGet = new HttpGet(uri);
        HttpEntity entity =null;
        String jsonString=null;
        double lon =0;
        double lat =0;
        String weatherDescription= null ;
        double temperature = 0;
        int pressure = 0;
        int humidity= 0;
        double minTemp= 0;
        double maxTemp =0;
        String cityName =null;
        String sunRise = null;
        String sunSet = null;
        int visibility =-1;

        try{
            CloseableHttpResponse response = httpClient.execute(httpGet);
            entity = response.getEntity();
            if(response.getStatusLine().getStatusCode() != 200){
                return "Connection is bad ! " + response.getStatusLine().getStatusCode();
            }
        }catch (IOException e){
            return "Something went wrong... retry!";
        }
        try {
            jsonString = EntityUtils.toString(entity);
        }catch (IOException e){
            return "parsing fail ... stay tune !";
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonString);

             cityName = rootNode.path("name").asText();
             lon = rootNode.path("coord").path("lon").asDouble();
             lat = rootNode.path("coord").path("lat").asDouble();
             sunRise = rootNode.path("sys").path("sunrise").asText();
             sunSet = rootNode.path("sys").path("sunset").asText();
             temperature = rootNode.path("main").path("temp").asDouble();
             minTemp = rootNode.path("main").path("temp_min").asDouble();
             maxTemp = rootNode.path("main").path("temp_max").asDouble();
             pressure = rootNode.path("main").path("pressure").asInt();
             humidity = rootNode.path("main").path("humidity").asInt();
             visibility = rootNode.path("visibility").asInt();
             weatherDescription = rootNode.path("weather").get(0).path("description").asText();

        } catch (Exception e) {
            return "something went wrong ! stay tune !";
        }
        //closing HttpClient
        try {
            if (httpClient != null){
            httpClient.close();
            }
        }catch (IOException e){
             e.printStackTrace();
        }
        String formattedInfo = String.format("In %s, located at a longitude of %.4f and latitude of %.4f, the weather conditions are as follows:\n" +
                        "The sun rises at %s and sets at %s.\n" +
                        "The temperature is currently %.2f°C with a minimum of %.2f°C and maximum of %.2f°C.\n" +
                        "The atmospheric pressure is %d hPa, and the humidity level stands at %d%%.\n" +
                        "Visibility extends up to %d meters, and weather Description : %s.",
                cityName, lon, lat, sunRise, sunSet, temperature, minTemp, maxTemp, pressure, humidity, visibility, weatherDescription);
        return formattedInfo;
    }

    public static String simpleGetParm(String location) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String uri = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", location,API_KEY);
        HttpGet httpGet = new HttpGet(uri);
        HttpEntity entity =null;
        String jsonString=null;
        double lon =0;
        double lat =0;
        String weatherDescription= null ;
        double temperature = 0;
        int pressure = 0;
        int humidity= 0;
        double minTemp= 0;
        double maxTemp =0;
        String cityName =null;
        String sunRise = null;
        String sunSet = null;
        int visibility =-1;

        try{
            CloseableHttpResponse response = httpClient.execute(httpGet);
            entity = response.getEntity();
            if(response.getStatusLine().getStatusCode() != 200){
                return "Connection is bad ! " + response.getStatusLine().getStatusCode();
            }
        }catch (IOException e){
            return "Something went wrong... retry!";
        }
        try {
            jsonString = EntityUtils.toString(entity);
        }catch (IOException e){
            return "parsing fail ... stay tune !";
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonString);

            cityName = rootNode.path("name").asText();
            lon = rootNode.path("coord").path("lon").asDouble();
            lat = rootNode.path("coord").path("lat").asDouble();
            sunRise=epochToDate(Long.parseLong(String.valueOf(rootNode.path("sys").path("sunrise").asText())));
            //sunRise = rootNode.path("sys").path("sunrise").asText();
            sunSet=epochToDate(Long.parseLong(String.valueOf(rootNode.path("sys").path("sunset").asText())));
            //sunSet = rootNode.path("sys").path("sunset").asText();
            temperature = rootNode.path("main").path("temp").asDouble();
            minTemp = rootNode.path("main").path("temp_min").asDouble();
            maxTemp = rootNode.path("main").path("temp_max").asDouble();
            pressure = rootNode.path("main").path("pressure").asInt();
            humidity = rootNode.path("main").path("humidity").asInt();
            visibility = rootNode.path("visibility").asInt();
            weatherDescription = rootNode.path("weather").get(0).path("description").asText();
        } catch (Exception e) {
            return "something went wrong ! stay tune !";
        }
        //closing HttpClient
        try {
            if (httpClient != null){
                httpClient.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        String formattedInfo = String.format("In %s, located at a lat. of %.4f and lon. of %.4f, the weather conditions are as follows:\n" +
                        "The sun rises at %s and sets at %s.\n" +
                        "The temperature is currently %.2f°C with a minimum of %.2f°C and maximum of %.2f°C.\n" +
                        "The atmospheric pressure is %d hPa, and the humidity level stands at %d%%.\n" +
                        "Visibility extends up to %d meters, and weather Description : %s.",
                cityName,  lat,lon, sunRise, sunSet, temperature, minTemp, maxTemp, pressure, humidity, visibility, weatherDescription);
        return formattedInfo;
    }

    public static String epochToDate(long epochTime){
        Date date = new Date(epochTime * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

}
