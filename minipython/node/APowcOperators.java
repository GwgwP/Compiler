/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class APowcOperators extends POperators
{
    private TPow _pow_;

    public APowcOperators()
    {
    }

    public APowcOperators(
        TPow _pow_)
    {
        setPow(_pow_);

    }
    public Object clone()
    {
        return new APowcOperators(
            (TPow) cloneNode(_pow_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAPowcOperators(this);
    }

    public TPow getPow()
    {
        return _pow_;
    }

    public void setPow(TPow node)
    {
        if(_pow_ != null)
        {
            _pow_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _pow_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_pow_);
    }

    void removeChild(Node child)
    {
        if(_pow_ == child)
        {
            _pow_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_pow_ == oldChild)
        {
            setPow((TPow) newChild);
            return;
        }

    }
}