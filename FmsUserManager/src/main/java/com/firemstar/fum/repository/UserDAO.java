package com.firemstar.fum.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.firemstar.fum.db.model.AccessLog;
import com.firemstar.fum.db.model.TUser;

@Repository
@Transactional
public class UserDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void create(TUser user) {
		entityManager.persist(user);
	}
	
	public void delete(TUser user) {
		if(entityManager.contains(user)) entityManager.remove(user);
		else {
			entityManager.remove(entityManager.merge(user));
		}
	}

	@SuppressWarnings("unchecked")
	public List getAll() {
		return entityManager.createQuery("from TUser").getResultList();
	}
	
	public TUser getByEmail(String email) {
		return (TUser) entityManager.createQuery(
				"from TUser where email = :email")
				.setParameter("email", email)
				.getSingleResult();
	}
	
	public TUser getByUser(String userId, String passwd) {
		return (TUser) entityManager.createQuery(
				"from TUser where userId = :userId and password = :passwd")
				.setParameter("userId", userId)
				.setParameter("passwd", passwd)
				.getSingleResult();
	}
	
	public TUser getByUser(String userId) {
		return (TUser) entityManager.createQuery(
				"from TUser where userId = :userId ")
				.setParameter("userId", userId)
				.getSingleResult();
	}
	
	public TUser getByAccessToken(String userId, String accessToken) {
		return (TUser) entityManager.createQuery(
				"from TUser where userId = :userId and accessToken = :accessToken")
				.setParameter("userId", userId)
				.setParameter("accessToken", accessToken)
				.getSingleResult();
	}
	
	
	public TUser getById(long id) {
		return entityManager.find(TUser.class, id);
	}
	
	public void update(TUser user) {
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
