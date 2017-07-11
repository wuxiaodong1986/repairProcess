package com.common.utils.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Tree implements Comparable<Tree>, Serializable
{
	protected static final long serialVersionUID = 1L;

	protected Object id;
	
	protected Object p_id;
	
	protected Tree parent;
	
	protected List<Tree> nodes = new ArrayList<Tree>();

    protected boolean selected = false;
    
    protected boolean disabled = false;
    
    public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Object getP_id() {
		return p_id;
	}

	public void setP_id(Object p_id) {
		this.p_id = p_id;
	}

	public Tree getParent() {
		return parent;
	}

	public void setParent(Tree parent) {
		this.parent = parent;
	}

	public List<Tree> getNodes() {
		return nodes;
	}

	public void setNodes(List<Tree> nodes) {
		this.nodes = nodes;
	}
	
	public void addNode(Tree node){
        this.nodes.add(node);
    }
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getLevel()
    {
        if (null == parent)
        {
            return 0;
        }
        
        Tree node = parent;
        
        int level = 1;
        
        while ((node = node.getParent()) != null)
        {
            level++;
        }
        
        return level;
    }
	
	public boolean getDisabled()
	{
		return disabled;
	}
	
	public void setDisabledId(Object id)
	{
		TreeIterator treeIterator = new TreeIterator(this);
		while(treeIterator.hasNext())
		{
			Tree tree = treeIterator.next();
			if (tree.getParent() != null && (id.equals(tree.getId()) || id.equals(tree.getParent().getId()) || tree.getParent().disabled == true))
			{
				tree.disabled = true;
			}
		}
	}
	
	public int compareTo(Tree node)
    {
    	return Integer.parseInt(this.id.toString()) - Integer.parseInt(node.id.toString());
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Tree)
        {
            return this.getId().equals(((Tree)obj).getId());
        }
        return false;
    }
}
