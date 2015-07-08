package com.optum.operations.momlibrary.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.optum.operations.momlibrary.dao.MoMDAO;
import com.optum.operations.momlibrary.entity.MoMUnit;
import com.optum.operations.momlibrary.entity.Role;
import com.optum.operations.momlibrary.entity.User;
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
		User user = new User();
		Role role = new Role();
		role.setName("admin");
		user.setAccess(role);
		user.setLanID("kperry");
		user.setFirstName("Katy");
		user.setLastName("Perry");
		update.setUser(user);
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
		User user2 = new User();
		user2.setAccess(role);
		user2.setLanID("scatacut");
		user2.setFirstName("Shin");
		user2.setLastName("Catacutan");
		update2.setUser(user2);
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
		User user3 = new User();
		user3.setAccess(role);
		user3.setLanID("tswift");
		user3.setFirstName("Taytay");
		user3.setLastName("Swift");
		update3.setUser(user3);
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
