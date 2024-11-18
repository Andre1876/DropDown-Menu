require 'net/http'
require 'json'

def get_weather(city)
    api_key = 'YOUR_API_KEY'
    url = URI("http://api.openweathermap.org/data/2.5/weather?q=#{city}&appid=#{api_key}")
    response = Net::HTTP.get(url)
    data = JSON.parse(response)

    if data["cod"] == 200
        temperature = data["main"]["temp"]
        description = data["weather"][0]["description"]
        return "Temperature: #{temperature}°C\nWeather: #{description}"
    else
        return "Error: Unable to fetch data"
    end
end

puts get_weather('London')