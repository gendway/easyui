package com.itheima.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.crm.domain.Customer;

public interface CustomerDao {

	Integer findCount(DetachedCriteria detachedCriteria);

	List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer rows);

	void save(Customer customer);

}
