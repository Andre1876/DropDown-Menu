import Foundation

func getWeather(city: String) -> String {
    let apiKey = "YOUR_API_KEY"
    let urlString = "http://api.openweathermap.org/data/2.5/weather?q=\(city)&appid=\(apiKey)"
    
    guard let url = URL(string: urlString) else {
        return "Error: Invalid URL"
    }
    
    let data = try? Data(contentsOf: url)
    if let jsonData = data {
        if let json = try? JSONSerialization.jsonObject(with: jsonData, options: []) as? [String: Any],
           let main = json["main"] as? [String: Any],
           let weather = (json["weather"] as? [[String: Any]])?.first {
            
            let temperature = main["temp"] as? Double ?? 0.0
            let description = weather["description"] as? String ?? "No description available"
            return "Temperature: \(temperature)°C\nWeather: \(description)"
        }
    }
    return "Error: Unable to fetch data"
}

print(getWeather(city: "London"))