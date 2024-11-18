import requests

def get_weather(city):
    api_key = 'YOUR_API_KEY'
    url = f'http://api.openweathermap.org/data/2.5/weather?q={city}&appid={api_key}'
    response = requests.get(url)
    data = response.json()
    
    if response.status_code == 200:
        main_data = data['main']
        weather_data = data['weather'][0]
        
        temperature = main_data['temp']
        description = weather_data['description']
        
        return f"Temperature: {temperature}°C\nWeather: {description}"
    else:
        return "Error: Unable to fetch data"

city = 'London'
print(get_weather(city))