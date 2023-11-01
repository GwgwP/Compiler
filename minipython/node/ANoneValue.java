/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class ANoneValue extends PValue
{
    private TNone _none_;

    public ANoneValue()
    {
    }

    public ANoneValue(
        TNone _none_)
    {
        setNone(_none_);

    }
    public Object clone()
    {
        return new ANoneValue(
            (TNone) cloneNode(_none_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANoneValue(this);
    }

    public TNone getNone()
    {
        return _none_;
    }

    public void setNone(TNone node)
    {
        if(_none_ != null)
        {
            _none_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _none_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_none_);
    }

    void removeChild(Node child)
    {
        if(_none_ == child)
        {
            _none_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_none_ == oldChild)
        {
            setNone((TNone) newChild);
            return;
        }

    }
}
