package com.onrugi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
//import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//import javax.transaction.Transactional;

//import org.hibernate.Session;

//import org.hibernate.SessionFactory;

//import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.stereotype.Repository;
import com.onrugi.entity.Dictionary;

@Repository
@Transactional
public class DictionaryDaoImpl implements DictionaryDao {

	@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	public List<DictionaryDao> findWordByKeyword(String keyword, String transType) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getAllTranslationTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DictionaryDao> findWordByKeywordWithPaging(String keyword, String transType, int pageLimit,
			int pageIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public boolean deleteWordById(int id) {
		String sql;
		TypedQuery<Dictionary> query;
		sql = "DELETE FROM Dictionary WHERE id =:id1";
		try {
			query = (TypedQuery<Dictionary>) entityManager.createQuery(sql);
			query.setParameter("id1", id);
			query.executeUpdate();
			return true;

		} catch (NullPointerException e) {

		}
		return false;
	}

	@Transactional
	public void addWord(Dictionary dictionary) {
		entityManager.persist(dictionary);
	}

	@Transactional
	public boolean updateWordMeaning(int id, String meaning, int check) {
		String sql;
		TypedQuery<Dictionary> query;
		if (check == 1) {
			sql = "UPDATE Dictionary SET tiengviet = :meaning WHERE id = :id";
		} else {
			sql = "UPDATE Dictionary SET tienganh = :meaning WHERE id = :id";
		}
		try {
			query = (TypedQuery<Dictionary>) entityManager.createQuery(sql);
			query.setParameter("meaning", meaning);
			query.setParameter("id", id);
			query.executeUpdate();
			return true;
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		return false;
	}

	public List<DictionaryDao> findWordById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addWord(String word, String meaning, String type) {
		// TODO Auto-generated method stub

	}

	@Transactional
	public Dictionary dictionary(String word, int num) {
		TypedQuery<Dictionary> query = null;
		String queryStr = "";

		if (num == 1) {
			queryStr = "from Dictionary as w where w.tienganh = :word";
		} else if (num == 2) {
			queryStr = "from Dictionary as w where w.tiengviet = :word";
		}
		query = (TypedQuery<Dictionary>) entityManager.createQuery(queryStr).setParameter("word", word);
		Dictionary td = null;
		try {
			td = (Dictionary) query.getSingleResult();
		} catch (NoResultException nre) {

		}
		return td;
	}

	@Transactional
	public List<Dictionary> searchAdmin(String word, int number) {
		List<Dictionary> listDict = null;
		TypedQuery<Dictionary> query;
		if (number == 1) {
			query = (TypedQuery<Dictionary>) entityManager
					.createQuery("from Dictionary as w where w.tienganh like concat(:word,'%')");
			query.setParameter("word", word);
		} else {
			query = (TypedQuery<Dictionary>) entityManager
					.createQuery("from Dictionary as w where w.tiengviet like concat(:word,'%')");
			query.setParameter("word", word);
		}

		listDict = query.getResultList();
		return listDict;
	}

	@Transactional
	public Dictionary edit(int key) {
		String queryStr = "from Dictionary as w where w.id = :key";
		TypedQuery<Dictionary> query = (TypedQuery<Dictionary>) entityManager.createQuery(queryStr).setParameter("id",
				key);
		return query.getSingleResult();
	}

}
