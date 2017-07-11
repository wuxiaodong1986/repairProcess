package com.college.beanInit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.college.dao.OrgDao;
import com.college.dto.Org;
import com.common.utils.tree.Tree;
import com.common.utils.tree.TreeBuilder;

@Configuration
public class OrgInit 
{
	@Autowired
	private OrgDao orgDao;
	
	@Scope("prototype")
	@Bean(name="collegeOrgTree")
	public Org getCollegeOrgTree()
	{
		List<Tree> collegeOrgs =  orgDao.getCollegeOrgs();
		
		Org root = new Org();
        root.setId("0");
        
        TreeBuilder.simpleBuild(root, collegeOrgs);
		
		return root;
	}
	
	@Scope("prototype")
	@Bean(name="collegeOrgMap")
	public Map<String, Org> getCollegeOrgMap()
	{
		List<Tree> orgs =  orgDao.getCollegeOrgs();
		
		Map<String, Org> orgMap = new HashMap<String, Org>();
		
		for (Tree tree : orgs)
		{
			Org org = (Org)tree;
			orgMap.put(org.getId(), org);
		}
		
		return orgMap;
	}
}
