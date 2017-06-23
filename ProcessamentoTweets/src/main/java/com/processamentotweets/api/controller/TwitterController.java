package com.processamentotweets.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.processamentotweets.api.dto.ResponseDTO;
import com.processamentotweets.domain.Keyword;
import com.processamentotweets.domain.KeywordRepository;
import com.processamentotweets.domain.Tweets;
import com.processamentotweets.domain.TweetsRepository;
import com.processamentotweets.domain.enums.StatesEnum;
import com.processamentotweets.util.FileManager;

@RestController
@RequestMapping("/api")
public class TwitterController {

	@Autowired
	private TweetsRepository tweetsRepository;
	@Autowired
	private KeywordRepository keywordRepository;
	private static final String path = "/home/thaina/TCC/keywords.txt";
	private final String keywords[] = new FileManager().getKeywords(path);
	
	@RequestMapping(method = RequestMethod.GET, path = "/tweets")
	public ResponseEntity<?> listTweets() {
		try{
			List<ResponseDTO> responseList = new ArrayList<ResponseDTO>();
			ResponseDTO responseDTO = new ResponseDTO();
			List<Tweets> tweets = tweetsRepository.findAll();
			for (int i = 0; i < keywords.length; i++) {
				for (Tweets tweet : tweets) {
					if(tweet.getKeyword().toUpperCase().contains(keywords[i].toUpperCase())) {
						//TODO
						String place = StatesEnum.getState(tweet.getPlace());
						if(place != null){
							Keyword keyword = new Keyword();
							keyword.setKeyword(tweet.getKeyword());
							keyword.setUf(place);
							keywordRepository.save(keyword);
						}
					}
				}                
			}
			return new ResponseEntity<List<ResponseDTO>>(responseList, HttpStatus.OK);
		} catch (Exception e) {
			return null;
		}
	}
	
	 
}
