package vttp2022.day22.giphy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import vttp2022.day22.giphy.service.ChukGiphyService;
import vttp2022.day22.giphy.service.GiphyService;

@SpringBootTest
class GiphyApplicationTests {

	//set GIPHY_API_KEY=<key>
	@Value("${giphy.api.key}")
	private String giphyKey;

	@Autowired
	private ChukGiphyService chukGiphySvc;

	@Autowired
	private GiphyService giphySvc;

	// @Test
	// void testRetrieval() {
	// 	List<String> gifList = giphySvc.retrieveGifLinks("fjhddhshskdfgjh", "10", "pg");
	// 	//expecting search value of gibberish to return no gifs
	// 	assertTrue(gifList.isEmpty());

	// 	gifList = giphySvc.retrieveGifLinks("pokemon", "10", "pg");
	// 	//expecting 10 results
	// 	assertTrue(!gifList.isEmpty());
	// }

	// @Test
	// void testChukRetrieval() {
	// 	List<String> gifList = chukGiphySvc.getGifs("pokemon");
	// 	//basically im expecting 10 which is the default
	// 	assertEquals(10, gifList.size());

	// 	gifList = chukGiphySvc.getGifs("pokemon", 50);
	// 	//basically im expecting 50
	// 	assertEquals(50, gifList.size());

	// 	gifList = chukGiphySvc.getGifs("pokemon", "asdjfhadhfakdfs");
	// 	//basically im expecting a non empty list even though rating is giffberish
	// 	assertTrue(!gifList.isEmpty());
	// }
}