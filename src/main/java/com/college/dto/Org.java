package com.college.dto;

import com.common.utils.tree.Tree;



/**
 * <菜单>
 * 
 * @author  wuxiaodong
 * @version  [版本号, 2015-2-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Org extends Tree
{
	private static final long serialVersionUID = 1L;

    private String name;
    
    public String getId() {
		return (String)id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getP_id() {
		return (String)p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    @Override
    public int compareTo(Tree node)
    {
        return Integer.parseInt((String)this.id) - Integer.parseInt((String)node.getId());
    }
    
}
