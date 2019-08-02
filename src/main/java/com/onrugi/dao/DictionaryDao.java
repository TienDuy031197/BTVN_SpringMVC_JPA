package com.onrugi.dao;

import java.util.List;

import com.onrugi.entity.Dictionary;

public interface DictionaryDao {
	List<DictionaryDao> findWordByKeyword(String keyword, String transType);

	List<String> getAllTranslationTypes();

	List<DictionaryDao> findWordByKeywordWithPaging(String keyword, String transType, int pageLimit, int pageIndex);

	boolean deleteWordById(int id);

	void addWord(Dictionary dictionary);

	boolean updateWordMeaning(int id, String meaning,int check);

	List<DictionaryDao> findWordById(int id);
	
	Dictionary dictionary(String word,int num);
	
	List<Dictionary> searchAdmin(String word,int number);
	
	Dictionary edit(int key);
}
