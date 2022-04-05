package vttp2022.day22.giphy.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class GiphyService {

    private final String GIPHY_URL = "https://api.giphy.com/v1/gifs/search";
    
    //set GIPHY_API_KEY=<key>
    @Value("${giphy.api.key}")
    private String API_KEY;
    
    public List<String> retrieveGifLinks(String searchval, String limit, String rating) {
        String url = UriComponentsBuilder
            .fromUriString(GIPHY_URL)
            .queryParam("api_key", API_KEY)
            .queryParam("q", searchval)
            .queryParam("limit", limit)
            .queryParam("rating", rating)
            .toUriString();
        
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.getForEntity(url, String.class);
        InputStream is = new ByteArrayInputStream(resp.getBody().getBytes());
        JsonReader reader = Json.createReader(is);
        JsonObject object = reader.readObject();
        JsonArray array = object.getJsonArray("data");
        
        List<String> gifUrl = new ArrayList<>();
        array.stream().forEach(v -> {
            JsonObject o = v.asJsonObject();
            gifUrl.add(o.getJsonObject("images").getJsonObject("fixed_width").getString("url"));
        });
        return gifUrl;
    }
}
