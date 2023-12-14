/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class ANumberValueno extends PValueno
{
    private PNum _num_;

    public ANumberValueno()
    {
    }

    public ANumberValueno(
        PNum _num_)
    {
        setNum(_num_);

    }
    public Object clone()
    {
        return new ANumberValueno(
            (PNum) cloneNode(_num_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANumberValueno(this);
    }

    public PNum getNum()
    {
        return _num_;
    }

    public void setNum(PNum node)
    {
        if(_num_ != null)
        {
            _num_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _num_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_num_);
    }

    void removeChild(Node child)
    {
        if(_num_ == child)
        {
            _num_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_num_ == oldChild)
        {
            setNum((PNum) newChild);
            return;
        }

    }
}