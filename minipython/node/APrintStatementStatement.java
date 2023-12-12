/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class APrintStatementStatement extends PStatement
{
    private PExpression _l_;
    private final LinkedList _r_ = new TypedLinkedList(new R_Cast());

    public APrintStatementStatement()
    {
    }

    public APrintStatementStatement(
        PExpression _l_,
        List _r_)
    {
        setL(_l_);

        {
            this._r_.clear();
            this._r_.addAll(_r_);
        }

    }
    public Object clone()
    {
        return new APrintStatementStatement(
            (PExpression) cloneNode(_l_),
            cloneList(_r_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAPrintStatementStatement(this);
    }

    public PExpression getL()
    {
        return _l_;
    }

    public void setL(PExpression node)
    {
        if(_l_ != null)
        {
            _l_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _l_ = node;
    }

    public LinkedList getR()
    {
        return _r_;
    }

    public void setR(List list)
    {
        _r_.clear();
        _r_.addAll(list);
    }

    public String toString()
    {
        return ""
            + toString(_l_)
            + toString(_r_);
    }

    void removeChild(Node child)
    {
        if(_l_ == child)
        {
            _l_ = null;
            return;
        }

        if(_r_.remove(child))
        {
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_l_ == oldChild)
        {
            setL((PExpression) newChild);
            return;
        }

        for(ListIterator i = _r_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set(newChild);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

    }

    private class R_Cast implements Cast
    {
        public Object cast(Object o)
        {
            PExpression node = (PExpression) o;

            if((node.parent() != null) &&
                (node.parent() != APrintStatementStatement.this))
            {
                node.parent().removeChild(node);
            }

            if((node.parent() == null) ||
                (node.parent() != APrintStatementStatement.this))
            {
                node.parent(APrintStatementStatement.this);
            }

            return node;
        }
    }
}
