package com.itheima.ssh.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.ssh.dao.CustomerDao;
import com.itheima.ssh.domain.Customer;
/**
 * 客户管理的DAO的实现类
 * @author jt
 *
 */
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {
	
	/*private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}*/

	@Override
	public void save(Customer customer) {
		System.out.println("DAO中的save方法执行了...");
		this.getHibernateTemplate().save(customer);
	}

	@Override
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
	}

	@Override
	public void delete(Customer customer) {
		this.getHibernateTemplate().delete(customer);
	}

	@Override
	public Customer findById(Long id) {
		return this.getHibernateTemplate().get(Customer.class, id);
	}

	@Override
	public List<Customer> findAll() {
//		List<Customer> list = (List<Customer>) this.getHibernateTemplate().find("from Customer");
		
	/*	DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria);*/
		
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByNamedQuery("queryAll");
		return list;
	}
	
	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size() > 0){
			return list.get(0).intValue();
		}
		return null;
	}

	@Override
	public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
	}

}
