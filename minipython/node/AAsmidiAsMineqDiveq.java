/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class AAsmidiAsMineqDiveq extends PAsMineqDiveq
{
    private TEq _eq_;

    public AAsmidiAsMineqDiveq()
    {
    }

    public AAsmidiAsMineqDiveq(
        TEq _eq_)
    {
        setEq(_eq_);

    }
    public Object clone()
    {
        return new AAsmidiAsMineqDiveq(
            (TEq) cloneNode(_eq_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAsmidiAsMineqDiveq(this);
    }

    public TEq getEq()
    {
        return _eq_;
    }

    public void setEq(TEq node)
    {
        if(_eq_ != null)
        {
            _eq_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _eq_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_eq_);
    }

    void removeChild(Node child)
    {
        if(_eq_ == child)
        {
            _eq_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_eq_ == oldChild)
        {
            setEq((TEq) newChild);
            return;
        }

    }
}
