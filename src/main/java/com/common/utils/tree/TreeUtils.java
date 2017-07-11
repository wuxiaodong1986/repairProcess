package com.common.utils.tree;

import java.util.Iterator;
import java.util.List;


public class TreeUtils 
{
	public static Tree build(Tree tree, List<Tree> nodes)
    {
        if (null == nodes || nodes.size() == 0)
        {
            return tree;
        }
        
        nodes.add(0, tree);
        
        for (int i = 0; i < nodes.size(); i++)
        {
            for (int j = 0; j < nodes.size(); j++)
            {
                if (i == j)
                {
                    continue;
                }
                if (nodes.get(i).getId().equals(nodes.get(j).getP_id()))
                {
                    nodes.get(i).getNodes().add(nodes.get(j));
                    nodes.get(j).setParent(nodes.get(i));
                }
            }
        }
        
        for (int i = 0; i < nodes.size(); i++)
        {
        	if (nodes.get(i).getLevel() == 0 && !tree.getId().equals(nodes.get(i).getId()))
        	{
        		tree.getNodes().add(nodes.get(i));
        		nodes.get(i).setParent(tree);
        	}
        }
        
        return tree;
    }
	
	public static int getLevel(Tree tree)
	{
		if (null == tree.getParent())
		{
			return 0;
		}
		
		int level = 1;
		
		Tree node = tree.getParent();
		
		while ((node = node.getParent()) != null)
		{
			level++;
		}
		
		return level;
	}
	
	public static Tree getNodeById(Tree root, Object id)
	{
		if (root.getId().equals(id))
		{
			return root;
		}
		Iterator<Tree> tree = new TreeIterator(root);
        
        while (tree.hasNext())
        {
        	Tree node = tree.next();
            
            if (node.getId().equals(id))
            {
                return node;
            }
        }
        
        return null;
	}
}
