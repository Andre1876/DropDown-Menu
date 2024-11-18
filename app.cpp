#include <iostream>
#include <string>
#include <curl/curl.h>

using namespace std;

size_t WriteCallback(void* contents, size_t size, size_t nmemb, string* output) {
    output->append((char*)contents, size * nmemb);
    return size * nmemb;
}

string getWeather(string city) {
    CURL* curl;
    CURLcode res;
    string readBuffer;
    string api_key = "YOUR_API_KEY";
    string url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + api_key;
    
    curl_global_init(CURL_GLOBAL_DEFAULT);
    curl = curl_easy_init();
    
    if(curl) {
        curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &readBuffer);
        
        res = curl_easy_perform(curl);
        
        if(res != CURLE_OK)
            return "Error: Unable to fetch data";
        
        curl_easy_cleanup(curl);
    }

    return readBuffer; // You'll need to parse JSON from `readBuffer` to get the weather details
}

int main() {
    string city = "London";
    cout << getWeather(city) << endl;
    return 0;
}