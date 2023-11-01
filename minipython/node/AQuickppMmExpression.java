/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class AQuickppMmExpression extends PExpression
{
    private PExpression _expression_;
    private PIncrement _increment_;

    public AQuickppMmExpression()
    {
    }

    public AQuickppMmExpression(
        PExpression _expression_,
        PIncrement _increment_)
    {
        setExpression(_expression_);

        setIncrement(_increment_);

    }
    public Object clone()
    {
        return new AQuickppMmExpression(
            (PExpression) cloneNode(_expression_),
            (PIncrement) cloneNode(_increment_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAQuickppMmExpression(this);
    }

    public PExpression getExpression()
    {
        return _expression_;
    }

    public void setExpression(PExpression node)
    {
        if(_expression_ != null)
        {
            _expression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _expression_ = node;
    }

    public PIncrement getIncrement()
    {
        return _increment_;
    }

    public void setIncrement(PIncrement node)
    {
        if(_increment_ != null)
        {
            _increment_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _increment_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_expression_)
            + toString(_increment_);
    }

    void removeChild(Node child)
    {
        if(_expression_ == child)
        {
            _expression_ = null;
            return;
        }

        if(_increment_ == child)
        {
            _increment_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_expression_ == oldChild)
        {
            setExpression((PExpression) newChild);
            return;
        }

        if(_increment_ == oldChild)
        {
            setIncrement((PIncrement) newChild);
            return;
        }

    }
}
