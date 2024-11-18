<?php
function getWeather($city) {
    $apiKey = 'YOUR_API_KEY';
    $url = "http://api.openweathermap.org/data/2.5/weather?q={$city}&appid={$apiKey}";
    $response = file_get_contents($url);
    $data = json_decode($response, true);

    if ($data["cod"] == 200) {
        $temperature = $data["main"]["temp"];
        $description = $data["weather"][0]["description"];
        return "Temperature: {$temperature}°C\nWeather: {$description}";
    } else {
        return "Error: Unable to fetch data";
    }
}

echo getWeather("London");
?>