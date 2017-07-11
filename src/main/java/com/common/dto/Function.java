package com.common.dto;

import com.common.utils.tree.Tree;



public class Function extends Tree
{
	private static final long serialVersionUID = 1L;

	private int ishow;
    
    private String name;
    
    private String url;
    
    private String logo;
    
    private String logname;
    
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

	public int getIshow()
    {
        return ishow;
    }

    public void setIshow(int ishow)
    {
        this.ishow = ishow;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLogname()
    {
        return logname;
    }

    public void setLogname(String logname)
    {
        this.logname = logname;
    }

    public boolean containsId(String id)
    {
    	return checkId(this, id);
    }
    
    private boolean checkId(Function function, String id)
    {
    	if (id.equals(function.getId()))
    	{
    		return true;
    	}
    	for (Tree tree : function.getNodes())
    	{
    		Function node = (Function)tree;
    		if (checkId(node, id))
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    @Override
    public int compareTo(Tree node)
    {
        return Integer.parseInt(this.id.toString()) - Integer.parseInt(node.getId().toString());
    }
}
