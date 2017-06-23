package com.processamentotweets.jobs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.processamentotweets.api.dto.ResponseDTO;
import com.processamentotweets.domain.Keyword;
import com.processamentotweets.domain.KeywordRepository;
import com.processamentotweets.domain.Tweets;
import com.processamentotweets.domain.TweetsRepository;
import com.processamentotweets.domain.enums.StatesEnum;
import com.processamentotweets.util.FileManager;

import br.com.wexinc.boleto.app.services.BoletoService;
import br.com.wexinc.boleto.domain.lojista.Lojista;
import br.com.wexinc.boleto.domain.lojista.LojistaRepository;

@Component
public class KeywordJob implements InterfaceJob {
	
	@Autowired
	public BoletoService boletoService;
	
	@Autowired
	public LojistaRepository lojistaRepository;
	
	private Logger logger = Logger.getLogger(KeywordJob.class);	
	@Autowired
	private TweetsRepository tweetsRepository;
	@Autowired
	private KeywordRepository keywordRepository;
	
	private static final String path = "/home/thaina/TCC/keywords.txt";
	private final String keywords[] = new FileManager().getKeywords(path);
	
	@Override
	public void run() {
		try {
		List<Lojista> lojistas = lojistaRepository.findAll();
		Calendar dataInicial = Calendar.getInstance();
		Calendar dataFinal = Calendar.getInstance();
		dataInicial.add(Calendar.DAY_OF_YEAR, -1);
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
	} catch (Exception e) {
	}
	}

	@Override
	public String getJobName() {
		return "[JOB] - CompensacaoBoletoJob ";
	}

}