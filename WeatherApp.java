import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WeatherApp {
    public static String getWeather(String city) {
        String apiKey = "YOUR_API_KEY";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStreamReader reader = new InputStreamReader(conn.getInputStream());
            JsonObject data = JsonParser.parseReader(reader).getAsJsonObject();
            
            double temperature = data.getAsJsonObject("main").get("temp").getAsDouble();
            String description = data.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();
            
            return "Temperature: " + temperature + "°C\nWeather: " + description;
        } catch (Exception e) {
            return "Error: Unable to fetch data";
        }
    }

    public static void main(String[] args) {
        System.out.println(getWeather("London"));
    }
}