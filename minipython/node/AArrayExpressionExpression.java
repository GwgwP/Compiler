/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class AArrayExpressionExpression extends PExpression
{
    private TId _id_;
    private TLBr _lBr_;
    private PExpression _expression_;
    private TRBr _rBr_;

    public AArrayExpressionExpression()
    {
    }

    public AArrayExpressionExpression(
        TId _id_,
        TLBr _lBr_,
        PExpression _expression_,
        TRBr _rBr_)
    {
        setId(_id_);

        setLBr(_lBr_);

        setExpression(_expression_);

        setRBr(_rBr_);

    }
    public Object clone()
    {
        return new AArrayExpressionExpression(
            (TId) cloneNode(_id_),
            (TLBr) cloneNode(_lBr_),
            (PExpression) cloneNode(_expression_),
            (TRBr) cloneNode(_rBr_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAArrayExpressionExpression(this);
    }

    public TId getId()
    {
        return _id_;
    }

    public void setId(TId node)
    {
        if(_id_ != null)
        {
            _id_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _id_ = node;
    }

    public TLBr getLBr()
    {
        return _lBr_;
    }

    public void setLBr(TLBr node)
    {
        if(_lBr_ != null)
        {
            _lBr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _lBr_ = node;
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

    public TRBr getRBr()
    {
        return _rBr_;
    }

    public void setRBr(TRBr node)
    {
        if(_rBr_ != null)
        {
            _rBr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _rBr_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_id_)
            + toString(_lBr_)
            + toString(_expression_)
            + toString(_rBr_);
    }

    void removeChild(Node child)
    {
        if(_id_ == child)
        {
            _id_ = null;
            return;
        }

        if(_lBr_ == child)
        {
            _lBr_ = null;
            return;
        }

        if(_expression_ == child)
        {
            _expression_ = null;
            return;
        }

        if(_rBr_ == child)
        {
            _rBr_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        if(_lBr_ == oldChild)
        {
            setLBr((TLBr) newChild);
            return;
        }

        if(_expression_ == oldChild)
        {
            setExpression((PExpression) newChild);
            return;
        }

        if(_rBr_ == oldChild)
        {
            setRBr((TRBr) newChild);
            return;
        }

    }
}
