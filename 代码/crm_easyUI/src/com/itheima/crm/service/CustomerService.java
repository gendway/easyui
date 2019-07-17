package com.itheima.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;

public interface CustomerService {

	PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer page, Integer rows);

	void save(Customer customer);

}
