package com.processamentotweets.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetsRepository extends JpaRepository<Tweets, Long> {
	List<Tweets> findByKeyword(String keyword);
	List<Tweets> findByPlace(String place);
	List<Tweets> findByKeywordAndPlace(String keyword, String place);
}
