package com.itheima.ssh.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.itheima.ssh.domain.Customer;
import com.itheima.ssh.domain.PageBean;
import com.itheima.ssh.service.CustomerService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;

/**
 * 客户管理的Action的类
 * 
 * @author jt
 *
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	// 模型驱动使用的对象
	private Customer customer = new Customer();

	public Customer getModel() {
		return customer;
	}

	// 按名称自动装配:
	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	// 接收分页数据:提供了两个参数名称：page 代表当前页数 和 rows 每页显示记录数
	private Integer page = 1;
	private Integer rows = 3;

	public void setPage(Integer page) {
		if (page == null) {
			page = 1;
		}
		this.page = page;
	}

	public void setRows(Integer rows) {
		if (rows == null) {
			rows = 3;
		}
		this.rows = rows;
	}

	/**
	 * 分页查询客户的方法:findByPage
	 * 
	 * @return
	 * @throws IOException
	 */
	public String findByPage() throws IOException {
		// 创建离线条件查询:
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		// 设置条件
		// 调用业务层:
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria, page, rows);
		// 使用JSONlib转成JSON。 FastJSON gson
		// 创建一个Map集合:
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pageBean.getTotalCount());
		map.put("rows", pageBean.getList());
		// 将map转成JSON:
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonObject.toString());
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonObject.toString());
		return NONE;
	}

	/**
	 * 编写save方法：
	 * 
	 * @throws IOException
	 */
	public String save() throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			System.out.println("Action中的save方法执行了...");
			// 调用业务层:
			customerService.save(customer);
			map.put("msg", "保存成功!");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "保存失败!");
		}
		// 将map转成JSON:
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonObject.toString());
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonObject.toString());// {"msg":..}
		return NONE;
	}

	public String findById() throws IOException {
		customer = customerService.findById(customer.getCust_id());
		System.out.println(customer);
		JSONObject jsonObject = JSONObject.fromObject(customer);
		System.out.println(jsonObject.toString());
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonObject.toString());// {"msg":..}
		return NONE;
	}

	public String update() throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			System.out.println("Action中的save方法执行了...");
			// 调用业务层:
			customerService.update(customer);
			map.put("msg", "保存成功!");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "保存失败!");
		}
		// 将map转成JSON:
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonObject.toString());
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonObject.toString());// {"msg":..}

		return NONE;
	}

	public String delete() throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			System.out.println("Action中的save方法执行了...");
			// 调用业务层:
			customer = customerService.findById(customer.getCust_id());
			customerService.delete(customer);
			map.put("msg", "保存成功!");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "保存失败!");
		}
		// 将map转成JSON:
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonObject.toString());
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonObject.toString());// {"msg":..}

		return NONE;
	}
}
