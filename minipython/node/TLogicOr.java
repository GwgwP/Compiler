/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import minipython.analysis.*;

public final class TLogicOr extends Token
{
    public TLogicOr()
    {
        super.setText("or");
    }

    public TLogicOr(int line, int pos)
    {
        super.setText("or");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TLogicOr(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTLogicOr(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TLogicOr text.");
    }
}