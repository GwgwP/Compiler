/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class AFalseComparisons extends PComparisons
{

    public AFalseComparisons()
    {
    }
    public Object clone()
    {
        return new AFalseComparisons();
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFalseComparisons(this);
    }

    public String toString()
    {
        return "";
    }

    void removeChild(Node child)
    {
    }

    void replaceChild(Node oldChild, Node newChild)
    {
    }
}
