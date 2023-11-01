/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class ADfdfdConjunction extends PConjunction
{
    private PInversion _inversion_;

    public ADfdfdConjunction()
    {
    }

    public ADfdfdConjunction(
        PInversion _inversion_)
    {
        setInversion(_inversion_);

    }
    public Object clone()
    {
        return new ADfdfdConjunction(
            (PInversion) cloneNode(_inversion_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADfdfdConjunction(this);
    }

    public PInversion getInversion()
    {
        return _inversion_;
    }

    public void setInversion(PInversion node)
    {
        if(_inversion_ != null)
        {
            _inversion_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _inversion_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_inversion_);
    }

    void removeChild(Node child)
    {
        if(_inversion_ == child)
        {
            _inversion_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_inversion_ == oldChild)
        {
            setInversion((PInversion) newChild);
            return;
        }

    }
}
