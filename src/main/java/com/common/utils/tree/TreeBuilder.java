package com.common.utils.tree;

import java.util.List;

public class TreeBuilder
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
                    nodes.get(i).addNode(nodes.get(j));
                    nodes.get(j).setParent(nodes.get(i));
                }
            }
        }
        
        return tree;
    }
    
    public static Tree simpleBuild(Tree tree, List<Tree> nodes)
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
                    nodes.get(i).addNode(nodes.get(j));
                }
            }
        }
        
        return tree;
    }
}
