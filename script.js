document.addEventListener("DOMContentLoaded", function() {
    const weatherInfoDiv = document.getElementById('weather-info');

    function getWeatherData() {
        fetch('https://api.openweathermap.org/data/2.5/weather?q=London&appid=YOUR_API_KEY')
            .then(response => response.json())
            .then(data => {
                weatherInfoDiv.innerHTML = `
                    <h2>Weather in ${data.name}</h2>
                    <p>Temperature: ${data.main.temp}°C</p>
                    <p>Weather: ${data.weather[0].description}</p>
                `;
            })
            .catch(error => {
                weatherInfoDiv.innerHTML = `<p>Error fetching weather data</p>`;
            });
    }

    getWeatherData();
});