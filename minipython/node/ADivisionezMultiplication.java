/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class ADivisionezMultiplication extends PMultiplication
{
    private PMultiplication _multiplication_;
    private TDiv _div_;
    private PExpressionsWithoutCulc _expressionsWithoutCulc_;

    public ADivisionezMultiplication()
    {
    }

    public ADivisionezMultiplication(
        PMultiplication _multiplication_,
        TDiv _div_,
        PExpressionsWithoutCulc _expressionsWithoutCulc_)
    {
        setMultiplication(_multiplication_);

        setDiv(_div_);

        setExpressionsWithoutCulc(_expressionsWithoutCulc_);

    }
    public Object clone()
    {
        return new ADivisionezMultiplication(
            (PMultiplication) cloneNode(_multiplication_),
            (TDiv) cloneNode(_div_),
            (PExpressionsWithoutCulc) cloneNode(_expressionsWithoutCulc_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADivisionezMultiplication(this);
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

    public PExpressionsWithoutCulc getExpressionsWithoutCulc()
    {
        return _expressionsWithoutCulc_;
    }

    public void setExpressionsWithoutCulc(PExpressionsWithoutCulc node)
    {
        if(_expressionsWithoutCulc_ != null)
        {
            _expressionsWithoutCulc_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _expressionsWithoutCulc_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_multiplication_)
            + toString(_div_)
            + toString(_expressionsWithoutCulc_);
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

        if(_expressionsWithoutCulc_ == child)
        {
            _expressionsWithoutCulc_ = null;
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

        if(_expressionsWithoutCulc_ == oldChild)
        {
            setExpressionsWithoutCulc((PExpressionsWithoutCulc) newChild);
            return;
        }

    }
}