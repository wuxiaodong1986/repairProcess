package com.college.beanInit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.college.dao.AddressDao;
import com.college.dto.Address;
import com.common.utils.tree.Tree;
import com.common.utils.tree.TreeBuilder;

@Configuration
public class AddressInit 
{
	@Autowired
	private AddressDao addressDao;
	
	@Scope("prototype")
	@Bean(name="collegeAddressTree")
	public Address getCollegeAddressTree()
	{
		List<Tree> collegeAddresses =  addressDao.getCollegeAddresss();
		
		Address root = new Address();
		root.setId(0);
        
        TreeBuilder.simpleBuild(root, collegeAddresses);
		
		return root;
	}
	
	@Scope("prototype")
	@Bean(name="collegeAddressMap")
	public Map<Integer, Address> getCollegeOrgMap()
	{
		List<Tree> addresses =  addressDao.getCollegeAddresss();
		
		Map<Integer, Address> addressMap = new HashMap<Integer, Address>();
		
		for (Tree tree : addresses)
		{
			Address address = (Address)tree;
			addressMap.put(address.getId(), address);
		}
		
		return addressMap;
	}
}
