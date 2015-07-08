package com.uhg.optum.ssmo.otnd.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.uhg.optum.ssmo.otnd.dao.MoMDAO;
import com.uhg.optum.ssmo.otnd.entity.MoMUnit;
import com.uhg.optum.ssmo.otnd.entity.Role;
import com.uhg.optum.ssmo.otnd.entity.Employee;
@Repository
public class MoMDaoImpl implements MoMDAO {

	@Override
	public List<MoMUnit> searchMoM(MoMUnit mom) {
		List<MoMUnit> updates = new ArrayList<MoMUnit>();
		MoMUnit update = new MoMUnit();
		update.setTitle("This is a title");
		update.setDescription("Donec porta erat vel sapien malesuada, sit amet imperdiet arcu interdum.");
		update.setId(10001);
		List<String> tags = new ArrayList<String>();
		tags.add("tag1");
		tags.add("tag2");
		tags.add("tag3");
		update.setTags(tags);
		Employee employee = new Employee();
		Role role = new Role();
		role.setName("admin");
		employee.setRoleType(role);
		employee.setNetworkID("kperry");
		employee.setFirstName("Katy");
		employee.setLastName("Perry");
		update.setUser(employee);
		update.setCreateDate("06/23/2015");
		update.setUpdateDate("06/23/2015");
		updates.add(update);
		
		MoMUnit update2 = new MoMUnit();
		update2.setTitle("Test Update Title");
		update2.setDescription("Fusce tristique sed dolor id volutpat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras");
		update2.setId(10002);
		List<String> tags2 = new ArrayList<String>();
		tags2.add("tag1");
		tags2.add("tag2");
		tags2.add("tag3");
		update2.setTags(tags2);
		Employee employee2 = new Employee();
		employee2.setRoleType(role);
		employee2.setNetworkID("scatacut");
		employee2.setFirstName("Shin");
		employee2.setLastName("Catacutan");
		update2.setUser(employee2);
		update2.setCreateDate("06/24/2015");
		update2.setUpdateDate("06/24/2015");
		updates.add(update2);
		
		MoMUnit update3 = new MoMUnit();
		update3.setTitle("Super Title");
		update3.setDescription("In vitae sapien vulputate, eleifend ipsum non, vulputate arcu. Ut venenatis sem in neque interdum mollis.");
		update3.setId(10003);
		List<String> tags3 = new ArrayList<String>();
		tags3.add("tag1");
		tags3.add("tag3");
		tags3.add("tag3");
		update3.setTags(tags3);
		Employee employee3 = new Employee();
		employee3.setRoleType(role);
		employee3.setNetworkID("tswift");
		employee3.setFirstName("Taytay");
		employee3.setLastName("Swift");
		update3.setUser(employee3);
		update3.setCreateDate("06/25/3015");
		update3.setUpdateDate("06/25/3015");
		updates.add(update3);
		return updates;
	}

	@Override
	public void addMoM(MoMUnit mom) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMoM(MoMUnit mom) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMoM(MoMUnit mom) {
		// TODO Auto-generated method stub
		
	}

}
