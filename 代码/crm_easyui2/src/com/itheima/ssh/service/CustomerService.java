package com.itheima.ssh.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.ssh.domain.Customer;
import com.itheima.ssh.domain.PageBean;

/**
 * 客户管理的业务层的接口
 * @author jt
 *
 */
public interface CustomerService {

	void save(Customer customer);
	
	void update(Customer customer);
	
	void delete(Customer customer);
	
	Customer findById(Long id);

	List<Customer> findAll();

	PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer page, Integer rows);
}
