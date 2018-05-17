package com.firemstar.fum.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.firemstar.fum.db.model.AccessLog;
import com.firemstar.fum.db.model.User;

@Repository
@Transactional
public class UserDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void create(User user) {
		entityManager.persist(user);
	}
	
	public void delete(User user) {
		if(entityManager.contains(user)) entityManager.remove(user);
		else {
			entityManager.remove(entityManager.merge(user));
		}
	}

	@SuppressWarnings("unchecked")
	public List getAll() {
		return entityManager.createQuery("from User").getResultList();
	}
	
	public User getByEmail(String email) {
		return (User) entityManager.createQuery(
				"from User where email = :email")
				.setParameter("email", email)
				.getSingleResult();
	}
	
	public User getByUser(String userId, String passwd) {
		return (User) entityManager.createQuery(
				"from User where userId = :userId and password = :passwd")
				.setParameter("userId", userId)
				.setParameter("passwd", passwd)
				.getSingleResult();
	}
	
	public User getByAccessToken(String userId, String accessToken) {
		return (User) entityManager.createQuery(
				"from User where userId = :userId and accessToken = :accessToken")
				.setParameter("userId", userId)
				.setParameter("accessToken", accessToken)
				.getSingleResult();
	}
	
	
	public User getById(long id) {
		return entityManager.find(User.class, id);
	}
	
	public void update(User user) {
		entityManager.merge(user);
	}
	
	public void createAccessLog(AccessLog log) {
		entityManager.persist(log);
	}
	
	public void updateAccessLog(AccessLog log) {
		entityManager.merge(log);
	}
	
	@SuppressWarnings("unchecked")
	public AccessLog getByAccessLog(String userid) {
		return  (AccessLog) entityManager.createQuery(
				"from AccessLog where userId = :userId order by  acid asc") 
				.setFirstResult(1)
				.setParameter("userId", userid)
				.getSingleResult();
		
	}
	

}
