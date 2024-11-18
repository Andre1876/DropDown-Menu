import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONObject

fun getWeather(city: String): String {
    val apiKey = "YOUR_API_KEY"
    val urlString = "http://api.openweathermap.org/data/2.5/weather?q=$city&appid=$apiKey"
    val url = URL(urlString)
    val connection = url.openConnection() as HttpURLConnection
    connection.requestMethod = "GET"
    val response = connection.inputStream.bufferedReader().readText()
    val data = JSONObject(response)

    return if (data.getInt("cod") == 200) {
        val temperature = data.getJSONObject("main").getDouble("temp")
        val description = data.getJSONArray("weather").getJSONObject(0).getString("description")
        "Temperature: $temperature°C\nWeather: $description"
    } else {
        "Error: Unable to fetch data"
    }
}

fun main() {
    println(getWeather("London"))
}