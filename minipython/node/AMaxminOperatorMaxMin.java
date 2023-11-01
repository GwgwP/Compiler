/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class AMaxminOperatorMaxMin extends POperatorMaxMin
{
    private TMax _max_;

    public AMaxminOperatorMaxMin()
    {
    }

    public AMaxminOperatorMaxMin(
        TMax _max_)
    {
        setMax(_max_);

    }
    public Object clone()
    {
        return new AMaxminOperatorMaxMin(
            (TMax) cloneNode(_max_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMaxminOperatorMaxMin(this);
    }

    public TMax getMax()
    {
        return _max_;
    }

    public void setMax(TMax node)
    {
        if(_max_ != null)
        {
            _max_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _max_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_max_);
    }

    void removeChild(Node child)
    {
        if(_max_ == child)
        {
            _max_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_max_ == oldChild)
        {
            setMax((TMax) newChild);
            return;
        }

    }
}
