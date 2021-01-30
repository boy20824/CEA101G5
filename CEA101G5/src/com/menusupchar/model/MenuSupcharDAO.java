package com.menusupchar.model;

import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class MenuSupcharDAO implements MenuSupchar_interface {

	@Override
	public void insert(MenuSupcharVO menuSupcharVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(menuSupcharVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	
}
