package com.onrugi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	@Autowired
	@PersistenceContext private EntityManager entityManager;

	@Transactional
	public Integer loginUser(String username, String pass) {
		Integer role = null;
		System.out.println(username +" "+ pass);
		TypedQuery<Integer> query;
		try {
			System.out.println("day");
			String getRoleQuery = "SELECT v.role FROM User as v WHERE name = :username AND password = :pass";
			query = (TypedQuery<Integer>) entityManager.createQuery(getRoleQuery).setParameter("username", username)
					.setParameter("pass", pass);
			List<Integer> results = query.getResultList();
			if(!results.isEmpty()) {
				role = results.get(0);
			}
			System.out.println("nay");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

}
