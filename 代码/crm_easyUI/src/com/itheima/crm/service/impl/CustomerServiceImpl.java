package com.itheima.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
@Transactional
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer page, Integer rows) {
		PageBean<Customer> pageBean = new PageBean<Customer>();
		pageBean.setCurrPage(page);
		pageBean.setPageSize(rows);
		Integer totalCount = customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc / rows);
		pageBean.setTotalPage( num.intValue());
		Integer begin = (page - 1 )* rows;
		List<Customer> list = customerDao.findByPage(detachedCriteria,begin,rows);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}
	
}
