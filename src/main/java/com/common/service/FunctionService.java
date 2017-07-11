package com.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.FunctionDao;
import com.common.dto.Function;
import com.common.utils.tree.Tree;
import com.common.utils.tree.TreeUtils;


@Service
public class FunctionService 
{
	@Autowired
	private FunctionDao functionDao;
	
	public Function getFunctionsByOprId(int opr_id)
	{
		List<Tree> functions =  functionDao.getFunctionsByOprId(opr_id);
		
		Function root = new Function();
        root.setId("0");
        
        TreeUtils.build(root, functions);
		
		return root;
	}
}
