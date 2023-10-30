/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.analysis;

import minipython.node.*;

public class ReversedDepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(Node node)
    {
    }

    public void defaultOut(Node node)
    {
    }

    public void caseStart(Start node)
    {
        inStart(node);
        node.getEOF().apply(this);
        node.getPProgramme().apply(this);
        outStart(node);
    }

    public void inAProgramme(AProgramme node)
    {
        defaultIn(node);
    }

    public void outAProgramme(AProgramme node)
    {
        defaultOut(node);
    }

    public void caseAProgramme(AProgramme node)
    {
        inAProgramme(node);
        {
            Object temp[] = node.getCommands().toArray();
            for(int i = temp.length - 1; i >= 0; i--)
            {
                ((PCommands) temp[i]).apply(this);
            }
        }
        outAProgramme(node);
    }

    public void inAStatCommands(AStatCommands node)
    {
        defaultIn(node);
    }

    public void outAStatCommands(AStatCommands node)
    {
        defaultOut(node);
    }

    public void caseAStatCommands(AStatCommands node)
    {
        inAStatCommands(node);
        if(node.getStatement() != null)
        {
            node.getStatement().apply(this);
        }
        outAStatCommands(node);
    }

    public void inAExprCommands(AExprCommands node)
    {
        defaultIn(node);
    }

    public void outAExprCommands(AExprCommands node)
    {
        defaultOut(node);
    }

    public void caseAExprCommands(AExprCommands node)
    {
        inAExprCommands(node);
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        outAExprCommands(node);
    }

    public void inAIfStatement(AIfStatement node)
    {
        defaultIn(node);
    }

    public void outAIfStatement(AIfStatement node)
    {
        defaultOut(node);
    }

    public void caseAIfStatement(AIfStatement node)
    {
        inAIfStatement(node);
        if(node.getStatement() != null)
        {
            node.getStatement().apply(this);
        }
        if(node.getSemi() != null)
        {
            node.getSemi().apply(this);
        }
        if(node.getComparison() != null)
        {
            node.getComparison().apply(this);
        }
        if(node.getIf() != null)
        {
            node.getIf().apply(this);
        }
        {
            Object temp[] = node.getTab().toArray();
            for(int i = temp.length - 1; i >= 0; i--)
            {
                ((TTab) temp[i]).apply(this);
            }
        }
        outAIfStatement(node);
    }

    public void inAAssignStatement(AAssignStatement node)
    {
        defaultIn(node);
    }

    public void outAAssignStatement(AAssignStatement node)
    {
        defaultOut(node);
    }

    public void caseAAssignStatement(AAssignStatement node)
    {
        inAAssignStatement(node);
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getEq() != null)
        {
            node.getEq().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        {
            Object temp[] = node.getTab().toArray();
            for(int i = temp.length - 1; i >= 0; i--)
            {
                ((TTab) temp[i]).apply(this);
            }
        }
        outAAssignStatement(node);
    }

    public void inAPrintStatement(APrintStatement node)
    {
        defaultIn(node);
    }

    public void outAPrintStatement(APrintStatement node)
    {
        defaultOut(node);
    }

    public void caseAPrintStatement(APrintStatement node)
    {
        inAPrintStatement(node);
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getPrint() != null)
        {
            node.getPrint().apply(this);
        }
        {
            Object temp[] = node.getTab().toArray();
            for(int i = temp.length - 1; i >= 0; i--)
            {
                ((TTab) temp[i]).apply(this);
            }
        }
        outAPrintStatement(node);
    }

    public void inATrueComparison(ATrueComparison node)
    {
        defaultIn(node);
    }

    public void outATrueComparison(ATrueComparison node)
    {
        defaultOut(node);
    }

    public void caseATrueComparison(ATrueComparison node)
    {
        inATrueComparison(node);
        if(node.getTrue() != null)
        {
            node.getTrue().apply(this);
        }
        outATrueComparison(node);
    }

    public void inAFalseComparison(AFalseComparison node)
    {
        defaultIn(node);
    }

    public void outAFalseComparison(AFalseComparison node)
    {
        defaultOut(node);
    }

    public void caseAFalseComparison(AFalseComparison node)
    {
        inAFalseComparison(node);
        if(node.getFalse() != null)
        {
            node.getFalse().apply(this);
        }
        outAFalseComparison(node);
    }

    public void inALesscComparison(ALesscComparison node)
    {
        defaultIn(node);
    }

    public void outALesscComparison(ALesscComparison node)
    {
        defaultOut(node);
    }

    public void caseALesscComparison(ALesscComparison node)
    {
        inALesscComparison(node);
        if(node.getRpar() != null)
        {
            node.getRpar().apply(this);
        }
        if(node.getLess() != null)
        {
            node.getLess().apply(this);
        }
        if(node.getLpar() != null)
        {
            node.getLpar().apply(this);
        }
        outALesscComparison(node);
    }

    public void inAGreatcComparison(AGreatcComparison node)
    {
        defaultIn(node);
    }

    public void outAGreatcComparison(AGreatcComparison node)
    {
        defaultOut(node);
    }

    public void caseAGreatcComparison(AGreatcComparison node)
    {
        inAGreatcComparison(node);
        if(node.getRpar() != null)
        {
            node.getRpar().apply(this);
        }
        if(node.getGreat() != null)
        {
            node.getGreat().apply(this);
        }
        if(node.getLpar() != null)
        {
            node.getLpar().apply(this);
        }
        outAGreatcComparison(node);
    }

    public void inAMultiplicationExpression(AMultiplicationExpression node)
    {
        defaultIn(node);
    }

    public void outAMultiplicationExpression(AMultiplicationExpression node)
    {
        defaultOut(node);
    }

    public void caseAMultiplicationExpression(AMultiplicationExpression node)
    {
        inAMultiplicationExpression(node);
        if(node.getMultiplication() != null)
        {
            node.getMultiplication().apply(this);
        }
        outAMultiplicationExpression(node);
    }

    public void inAAdditionExpression(AAdditionExpression node)
    {
        defaultIn(node);
    }

    public void outAAdditionExpression(AAdditionExpression node)
    {
        defaultOut(node);
    }

    public void caseAAdditionExpression(AAdditionExpression node)
    {
        inAAdditionExpression(node);
        if(node.getMultiplication() != null)
        {
            node.getMultiplication().apply(this);
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        outAAdditionExpression(node);
    }

    public void inASubtractionExpression(ASubtractionExpression node)
    {
        defaultIn(node);
    }

    public void outASubtractionExpression(ASubtractionExpression node)
    {
        defaultOut(node);
    }

    public void caseASubtractionExpression(ASubtractionExpression node)
    {
        inASubtractionExpression(node);
        if(node.getMultiplication() != null)
        {
            node.getMultiplication().apply(this);
        }
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        outASubtractionExpression(node);
    }

    public void inAPowMultiplication(APowMultiplication node)
    {
        defaultIn(node);
    }

    public void outAPowMultiplication(APowMultiplication node)
    {
        defaultOut(node);
    }

    public void caseAPowMultiplication(APowMultiplication node)
    {
        inAPowMultiplication(node);
        if(node.getPower() != null)
        {
            node.getPower().apply(this);
        }
        outAPowMultiplication(node);
    }

    public void inAMultiplicationMultiplication(AMultiplicationMultiplication node)
    {
        defaultIn(node);
    }

    public void outAMultiplicationMultiplication(AMultiplicationMultiplication node)
    {
        defaultOut(node);
    }

    public void caseAMultiplicationMultiplication(AMultiplicationMultiplication node)
    {
        inAMultiplicationMultiplication(node);
        if(node.getPower() != null)
        {
            node.getPower().apply(this);
        }
        if(node.getMult() != null)
        {
            node.getMult().apply(this);
        }
        if(node.getMultiplication() != null)
        {
            node.getMultiplication().apply(this);
        }
        outAMultiplicationMultiplication(node);
    }

    public void inASomethingPower(ASomethingPower node)
    {
        defaultIn(node);
    }

    public void outASomethingPower(ASomethingPower node)
    {
        defaultOut(node);
    }

    public void caseASomethingPower(ASomethingPower node)
    {
        inASomethingPower(node);
        if(node.getSomething() != null)
        {
            node.getSomething().apply(this);
        }
        outASomethingPower(node);
    }

    public void inAPowerPower(APowerPower node)
    {
        defaultIn(node);
    }

    public void outAPowerPower(APowerPower node)
    {
        defaultOut(node);
    }

    public void caseAPowerPower(APowerPower node)
    {
        inAPowerPower(node);
        if(node.getSomething() != null)
        {
            node.getSomething().apply(this);
        }
        if(node.getPow() != null)
        {
            node.getPow().apply(this);
        }
        if(node.getPower() != null)
        {
            node.getPower().apply(this);
        }
        outAPowerPower(node);
    }

    public void inAIdentifierSomething(AIdentifierSomething node)
    {
        defaultIn(node);
    }

    public void outAIdentifierSomething(AIdentifierSomething node)
    {
        defaultOut(node);
    }

    public void caseAIdentifierSomething(AIdentifierSomething node)
    {
        inAIdentifierSomething(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAIdentifierSomething(node);
    }

    public void inANumbSomething(ANumbSomething node)
    {
        defaultIn(node);
    }

    public void outANumbSomething(ANumbSomething node)
    {
        defaultOut(node);
    }

    public void caseANumbSomething(ANumbSomething node)
    {
        inANumbSomething(node);
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
        }
        outANumbSomething(node);
    }

    public void inAParSomething(AParSomething node)
    {
        defaultIn(node);
    }

    public void outAParSomething(AParSomething node)
    {
        defaultOut(node);
    }

    public void caseAParSomething(AParSomething node)
    {
        inAParSomething(node);
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        outAParSomething(node);
    }
}
