package com.uhg.optum.ssmo.otnd.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.uhg.optum.ssmo.otnd.dao.EmployeeDao;
import com.uhg.optum.ssmo.otnd.entity.Employee;
@Repository("EmployeeDao")
public class EmployeeDaoImpl extends AbstractDao implements EmployeeDao {

	@Override
	public Employee getEmployee(String lanId) {
		Criteria criteria = getSession().createCriteria(Employee.class);
        criteria.add(Restrictions.eq("networkID",lanId));
		return (Employee) criteria.uniqueResult();
	}

	@Override
	public void addEmployee(Employee Employee) {
		persist(Employee);
	}

	@Override
	public void updateEmployee(Employee Employee) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteEmployee(Employee Employee) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Employee> getEmployees(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

}
