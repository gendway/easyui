package com.itheima.ssh.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.ssh.domain.Customer;

/**
 * 客户管理的DAO的接口
 * @author jt
 *
 */
public interface CustomerDao {

	void save(Customer customer);

	void update(Customer customer);

	void delete(Customer customer);

	Customer findById(Long id);

	List<Customer> findAll();

	Integer findCount(DetachedCriteria detachedCriteria);

	List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

}
