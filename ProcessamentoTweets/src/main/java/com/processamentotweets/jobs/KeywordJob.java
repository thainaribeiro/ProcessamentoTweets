package com.processamentotweets.jobs;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.processamentotweets.domain.keyword.Keyword;
import com.processamentotweets.domain.keyword.KeywordRepository;
import com.processamentotweets.domain.tweets.Tweets;
import com.processamentotweets.domain.tweets.TweetsRepository;
import com.processamentotweets.domain.enums.StatesEnum;
import com.processamentotweets.util.FileManager;

@Component
public class KeywordJob implements InterfaceJob {
	
	private Logger logger = Logger.getLogger(KeywordJob.class);	
	@Autowired
	private TweetsRepository tweetsRepository;
	@Autowired
	private KeywordRepository keywordRepository;
	
	private static final String path = "/keywords.txt";
	private final String keywords[] = new FileManager().getKeywords(path);
	
	@Override
	public void run() {
		try {
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
	} catch (Exception e) {
	}
	}

	@Override
	public String getJobName() {
		return "[JOB] - CompensacaoBoletoJob ";
	}

}