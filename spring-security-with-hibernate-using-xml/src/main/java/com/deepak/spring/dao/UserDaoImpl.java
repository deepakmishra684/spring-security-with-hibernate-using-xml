package com.deepak.spring.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.deepak.spring.model.User;

public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public User getUserByName(String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		String hql = "from User as user where user.username=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, username);
		List list = query.list();
		if(list.isEmpty())
			return null;
		return (User)list.get(0);
	}
}
