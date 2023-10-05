package org.prem.WebSerches;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.prem.connection.Credentials;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Picutre {
    private static final String API_KEY = Credentials.getPixabayApiKey();
//    final static CloseableHttpClient httpClient = HttpClients.createDefault();


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
    public static Map<String, String> simpleGetParm(String keyword)  {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD)
                        .build())
                .build();
        String uri = String.format("https://pixabay.com/api/?key=%s&q=%s&image_type=photo",API_KEY,keyword);
        HttpGet httpGet = new HttpGet(uri);
        HttpEntity entity =null;
        String jsonString=null;
        Map<String, String> tagUrlMap = new HashMap<>();
        try{

            CloseableHttpResponse response = httpClient.execute(httpGet);
            entity = response.getEntity();
            if(response.getStatusLine().getStatusCode() != 200){
                tagUrlMap.put(String.valueOf(response.getStatusLine().getStatusCode()),"something went wrong ! ");
                return tagUrlMap;
            }
        }catch (IOException e){
            tagUrlMap.put("error","something went wrong ! ");
            return tagUrlMap;
        }
        try {
            jsonString = EntityUtils.toString(entity);
        }catch (IOException e){
            tagUrlMap.put("error","something went wrong ! ");
            return tagUrlMap;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(jsonString);
            int total = jsonNode.get("total").asInt();
            int totalHits = jsonNode.get("totalHits").asInt();
            JsonNode hits = jsonNode.get("hits");
            for (JsonNode hit : hits) {
                int id = hit.get("id").asInt();
                String pageURL = hit.get("pageURL").asText();
                String type = hit.get("type").asText();
                String tags = hit.get("tags").asText();
                String previewURL = hit.get("previewURL").asText();
                int previewWidth = hit.get("previewWidth").asInt();
                int previewHeight = hit.get("previewHeight").asInt();
                String webformatURL = hit.get("webformatURL").asText();
                int webformatWidth = hit.get("webformatWidth").asInt();
                int webformatHeight = hit.get("webformatHeight").asInt();
                String largeImageURL = hit.get("largeImageURL").asText();
                int imageWidth = hit.get("imageWidth").asInt();
                int imageHeight = hit.get("imageHeight").asInt();
                int imageSize = hit.get("imageSize").asInt();
                int views = hit.get("views").asInt();
                int downloads = hit.get("downloads").asInt();
                int collections = hit.get("collections").asInt();
                int likes = hit.get("likes").asInt();
                int comments = hit.get("comments").asInt();
                int userId = hit.get("user_id").asInt();
                String user = hit.get("user").asText();
                String userImageURL = hit.get("userImageURL").asText();
                tagUrlMap.put(tags,largeImageURL);
            }
        } catch (JsonMappingException ex) {
            throw new RuntimeException(ex);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
        //closing HttpClient
        try {
            if (httpClient != null){
                httpClient.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return tagUrlMap;
    }


}
