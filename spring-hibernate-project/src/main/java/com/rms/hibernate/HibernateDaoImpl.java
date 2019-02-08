package com.rms.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public int getVehicleCount() {
		String hql = "select count(*) from VEHICLE";
		Query query = getSessionFactory().openSession().createQuery(hql);
		return ((Long) query.uniqueResult()).intValue();
	}

	public void saveAll(List<Object> params) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for (Object obj : params) {
			session.save(obj);
		}
		session.getTransaction().commit();
		session.close();
	}
	
	public List<Vehicle> getVehicles() {
		String hql = "select veh from VEHICLE veh";
		Query query = getSessionFactory().openSession().createQuery(hql);
		return query.getResultList();
	}
}
