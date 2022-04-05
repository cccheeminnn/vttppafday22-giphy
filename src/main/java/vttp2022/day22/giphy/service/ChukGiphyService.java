package vttp2022.day22.giphy.service;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

//Chuk's GiphyService
@Service
public class ChukGiphyService {
    
    //set GIPHY_API_KEY=<key>
    @Value("${giphy.api.key}")
    private String giphyKey;

    public List<String> getGifs(String q) {
        return getGifs(q, 10, "pg");
    }

    public List<String> getGifs(String q, Integer limit) {
        return getGifs(q, limit, "pg");
    }

    public List<String> getGifs(String q, String rating) {
        return getGifs(q, 10, rating);
    }

    public List<String> getGifs(String q, Integer limit, String rating) {
        List<String> gifList = new LinkedList<>();

        //https://api.giphy.com/v1/gifs/search?api_key=gpjPSPZMUJXbqU2aGx3xFk7yzl8BgNv8&q=pokemon&limit=3&offset=0&rating=g&lang=en
        String url = UriComponentsBuilder
            .fromUriString("https://api.giphy.com/v1/gifs/search")
            .queryParam("api_key", giphyKey)
            .queryParam("q", q)
            .queryParam("limit", limit)
            .queryParam("rating", rating)
            .toUriString();

        RequestEntity<Void> req = RequestEntity.get(url).build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null; 
        
        try {
            resp = template.exchange(req, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return gifList;
        }

        JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
        JsonObject object = reader.readObject();
        JsonArray array = object.getJsonArray("data");

        for (JsonValue g : array) {
            JsonObject o = g.asJsonObject();
            String imageUrl = o.getJsonObject("images").getJsonObject("fixed_width").getString("url");
            gifList.add(imageUrl);
        }

        return gifList;
    }
}