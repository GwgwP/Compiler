/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class ADivisionMultiplication extends PMultiplication
{
    private PMultiplication _multiplication_;
    private TDiv _div_;
    private PPower _power_;

    public ADivisionMultiplication()
    {
    }

    public ADivisionMultiplication(
        PMultiplication _multiplication_,
        TDiv _div_,
        PPower _power_)
    {
        setMultiplication(_multiplication_);

        setDiv(_div_);

        setPower(_power_);

    }
    public Object clone()
    {
        return new ADivisionMultiplication(
            (PMultiplication) cloneNode(_multiplication_),
            (TDiv) cloneNode(_div_),
            (PPower) cloneNode(_power_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADivisionMultiplication(this);
    }

    public PMultiplication getMultiplication()
    {
        return _multiplication_;
    }

    public void setMultiplication(PMultiplication node)
    {
        if(_multiplication_ != null)
        {
            _multiplication_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _multiplication_ = node;
    }

    public TDiv getDiv()
    {
        return _div_;
    }

    public void setDiv(TDiv node)
    {
        if(_div_ != null)
        {
            _div_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _div_ = node;
    }

    public PPower getPower()
    {
        return _power_;
    }

    public void setPower(PPower node)
    {
        if(_power_ != null)
        {
            _power_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _power_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_multiplication_)
            + toString(_div_)
            + toString(_power_);
    }

    void removeChild(Node child)
    {
        if(_multiplication_ == child)
        {
            _multiplication_ = null;
            return;
        }

        if(_div_ == child)
        {
            _div_ = null;
            return;
        }

        if(_power_ == child)
        {
            _power_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_multiplication_ == oldChild)
        {
            setMultiplication((PMultiplication) newChild);
            return;
        }

        if(_div_ == oldChild)
        {
            setDiv((TDiv) newChild);
            return;
        }

        if(_power_ == oldChild)
        {
            setPower((PPower) newChild);
            return;
        }

    }
}
