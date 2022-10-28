package com.tom.ws.soap.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tom.ws.soap.entity.User;
import com.tom.ws.soap.helper.SessionHelper;



public class UserDAO {
	
	private final EntityManager entityManager = SessionHelper.getEntityManager();
	
	@SuppressWarnings("unchecked")
	public Map<String, String> getUsers() {
		Map<String, String> usersMap = new HashMap<>();
		Query query = entityManager.createQuery("Select c From User c");
		
		List<User> resultList = query.getResultList();
		for(User user:resultList) {
			usersMap.put(user.getUsername(), user.getPassword());
		}	
		
		
		return usersMap;
	}

}
