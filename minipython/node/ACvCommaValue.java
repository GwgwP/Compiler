/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class ACvCommaValue extends PCommaValue
{
    private TComma _comma_;
    private PValue _value_;

    public ACvCommaValue()
    {
    }

    public ACvCommaValue(
        TComma _comma_,
        PValue _value_)
    {
        setComma(_comma_);

        setValue(_value_);

    }
    public Object clone()
    {
        return new ACvCommaValue(
            (TComma) cloneNode(_comma_),
            (PValue) cloneNode(_value_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACvCommaValue(this);
    }

    public TComma getComma()
    {
        return _comma_;
    }

    public void setComma(TComma node)
    {
        if(_comma_ != null)
        {
            _comma_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _comma_ = node;
    }

    public PValue getValue()
    {
        return _value_;
    }

    public void setValue(PValue node)
    {
        if(_value_ != null)
        {
            _value_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _value_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_comma_)
            + toString(_value_);
    }

    void removeChild(Node child)
    {
        if(_comma_ == child)
        {
            _comma_ = null;
            return;
        }

        if(_value_ == child)
        {
            _value_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_comma_ == oldChild)
        {
            setComma((TComma) newChild);
            return;
        }

        if(_value_ == oldChild)
        {
            setValue((PValue) newChild);
            return;
        }

    }
}
