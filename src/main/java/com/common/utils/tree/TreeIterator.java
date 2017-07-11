package com.common.utils.tree;

import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

public class TreeIterator implements Iterator<Tree>
{
    private Stack<Tree> stack = new Stack<Tree>();
    
    private int level = 0;
    
    public TreeIterator(Tree sortVo) 
    {
        stack.push(sortVo);
    }

    public TreeIterator(Tree sortVo, int level) 
    {
        stack.push(sortVo);
        
        this.level = level;
    }
    
    public boolean hasNext()
    {
        if (stack.empty())
        {
            return false;
        }
        else
        {
            if (this.level==0)
            {
                return true;
            }
            else
            {
            	Tree tree = next();
                if (null == tree)
                {
                    return false;
                }
                else
                {
                    stack.push(tree);
                    return true;
                }
            }
        }
    }

    public Tree next()
    {
        if (stack.empty())
        {
            return null;
        }

        Tree tree = stack.pop();

        if (tree.getNodes().size() != 0)
        {
            Collections.sort(tree.getNodes());
            for (Tree node : tree.getNodes())
            {
                stack.push(node);
            }
        }
        
        if (level != 0 && TreeUtils.getLevel(tree) > level)
        {
            return next();
        }
        
        return tree;
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

}
