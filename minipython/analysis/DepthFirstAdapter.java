/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.analysis;

import java.util.*;
import minipython.node.*;

public class DepthFirstAdapter extends AnalysisAdapter
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
        node.getPGoal().apply(this);
        node.getEOF().apply(this);
        outStart(node);
    }

    public void inAGoal(AGoal node)
    {
        defaultIn(node);
    }

    public void outAGoal(AGoal node)
    {
        defaultOut(node);
    }

    public void caseAGoal(AGoal node)
    {
        inAGoal(node);
        {
            Object temp[] = node.getCommands().toArray();
            for(int i = 0; i < temp.length; i++)
            {
                ((PCommands) temp[i]).apply(this);
            }
        }
        outAGoal(node);
    }

    public void inAFuncCommands(AFuncCommands node)
    {
        defaultIn(node);
    }

    public void outAFuncCommands(AFuncCommands node)
    {
        defaultOut(node);
    }

    public void caseAFuncCommands(AFuncCommands node)
    {
        inAFuncCommands(node);
        if(node.getFunction() != null)
        {
            node.getFunction().apply(this);
        }
        outAFuncCommands(node);
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

    public void inADedeFunction(ADedeFunction node)
    {
        defaultIn(node);
    }

    public void outADedeFunction(ADedeFunction node)
    {
        defaultOut(node);
    }

    public void caseADedeFunction(ADedeFunction node)
    {
        inADedeFunction(node);
        if(node.getDef() != null)
        {
            node.getDef().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getArgument() != null)
        {
            node.getArgument().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getSemi() != null)
        {
            node.getSemi().apply(this);
        }
        if(node.getStatement() != null)
        {
            node.getStatement().apply(this);
        }
        outADedeFunction(node);
    }

    public void inAFrfrfArgument(AFrfrfArgument node)
    {
        defaultIn(node);
    }

    public void outAFrfrfArgument(AFrfrfArgument node)
    {
        defaultOut(node);
    }

    public void caseAFrfrfArgument(AFrfrfArgument node)
    {
        inAFrfrfArgument(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getAssignValue() != null)
        {
            node.getAssignValue().apply(this);
        }
        {
            Object temp[] = node.getCiav().toArray();
            for(int i = 0; i < temp.length; i++)
            {
                ((PCiav) temp[i]).apply(this);
            }
        }
        outAFrfrfArgument(node);
    }

    public void inAFfCiav(AFfCiav node)
    {
        defaultIn(node);
    }

    public void outAFfCiav(AFfCiav node)
    {
        defaultOut(node);
    }

    public void caseAFfCiav(AFfCiav node)
    {
        inAFfCiav(node);
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getAssignValue() != null)
        {
            node.getAssignValue().apply(this);
        }
        outAFfCiav(node);
    }

    public void inAFrfrfrrfrfAssignValue(AFrfrfrrfrfAssignValue node)
    {
        defaultIn(node);
    }

    public void outAFrfrfrrfrfAssignValue(AFrfrfrrfrfAssignValue node)
    {
        defaultOut(node);
    }

    public void caseAFrfrfrrfrfAssignValue(AFrfrfrrfrfAssignValue node)
    {
        inAFrfrfrrfrfAssignValue(node);
        if(node.getEq() != null)
        {
            node.getEq().apply(this);
        }
        if(node.getValue() != null)
        {
            node.getValue().apply(this);
        }
        outAFrfrfrrfrfAssignValue(node);
    }

    public void inAIfStatementStatement(AIfStatementStatement node)
    {
        defaultIn(node);
    }

    public void outAIfStatementStatement(AIfStatementStatement node)
    {
        defaultOut(node);
    }

    public void caseAIfStatementStatement(AIfStatementStatement node)
    {
        inAIfStatementStatement(node);
        {
            Object temp[] = node.getTab().toArray();
            for(int i = 0; i < temp.length; i++)
            {
                ((TTab) temp[i]).apply(this);
            }
        }
        if(node.getIf() != null)
        {
            node.getIf().apply(this);
        }
        if(node.getComparison() != null)
        {
            node.getComparison().apply(this);
        }
        if(node.getSemi() != null)
        {
            node.getSemi().apply(this);
        }
        if(node.getStatement() != null)
        {
            node.getStatement().apply(this);
        }
        outAIfStatementStatement(node);
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
        {
            Object temp[] = node.getTab().toArray();
            for(int i = 0; i < temp.length; i++)
            {
                ((TTab) temp[i]).apply(this);
            }
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getEq() != null)
        {
            node.getEq().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
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
        {
            Object temp[] = node.getTab().toArray();
            for(int i = 0; i < temp.length; i++)
            {
                ((TTab) temp[i]).apply(this);
            }
        }
        if(node.getPrint() != null)
        {
            node.getPrint().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
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

    public void inALesseqComparison(ALesseqComparison node)
    {
        defaultIn(node);
    }

    public void outALesseqComparison(ALesseqComparison node)
    {
        defaultOut(node);
    }

    public void caseALesseqComparison(ALesseqComparison node)
    {
        inALesseqComparison(node);
        if(node.getLpar() != null)
        {
            node.getLpar().apply(this);
        }
        if(node.getLesseq() != null)
        {
            node.getLesseq().apply(this);
        }
        if(node.getRpar() != null)
        {
            node.getRpar().apply(this);
        }
        outALesseqComparison(node);
    }

    public void inAGreateqComparison(AGreateqComparison node)
    {
        defaultIn(node);
    }

    public void outAGreateqComparison(AGreateqComparison node)
    {
        defaultOut(node);
    }

    public void caseAGreateqComparison(AGreateqComparison node)
    {
        inAGreateqComparison(node);
        if(node.getLpar() != null)
        {
            node.getLpar().apply(this);
        }
        if(node.getGreateq() != null)
        {
            node.getGreateq().apply(this);
        }
        if(node.getRpar() != null)
        {
            node.getRpar().apply(this);
        }
        outAGreateqComparison(node);
    }

    public void inADiffComparison(ADiffComparison node)
    {
        defaultIn(node);
    }

    public void outADiffComparison(ADiffComparison node)
    {
        defaultOut(node);
    }

    public void caseADiffComparison(ADiffComparison node)
    {
        inADiffComparison(node);
        if(node.getLpar() != null)
        {
            node.getLpar().apply(this);
        }
        if(node.getNoteq() != null)
        {
            node.getNoteq().apply(this);
        }
        if(node.getRpar() != null)
        {
            node.getRpar().apply(this);
        }
        outADiffComparison(node);
    }

    public void inAEqeqComparison(AEqeqComparison node)
    {
        defaultIn(node);
    }

    public void outAEqeqComparison(AEqeqComparison node)
    {
        defaultOut(node);
    }

    public void caseAEqeqComparison(AEqeqComparison node)
    {
        inAEqeqComparison(node);
        if(node.getLpar() != null)
        {
            node.getLpar().apply(this);
        }
        if(node.getEqualequal() != null)
        {
            node.getEqualequal().apply(this);
        }
        if(node.getRpar() != null)
        {
            node.getRpar().apply(this);
        }
        outAEqeqComparison(node);
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
        if(node.getLpar() != null)
        {
            node.getLpar().apply(this);
        }
        if(node.getLess() != null)
        {
            node.getLess().apply(this);
        }
        if(node.getRpar() != null)
        {
            node.getRpar().apply(this);
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
        if(node.getLpar() != null)
        {
            node.getLpar().apply(this);
        }
        if(node.getGreat() != null)
        {
            node.getGreat().apply(this);
        }
        if(node.getRpar() != null)
        {
            node.getRpar().apply(this);
        }
        outAGreatcComparison(node);
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
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getMultiplication() != null)
        {
            node.getMultiplication().apply(this);
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
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getMultiplication() != null)
        {
            node.getMultiplication().apply(this);
        }
        outASubtractionExpression(node);
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

    public void inADivisionMultiplication(ADivisionMultiplication node)
    {
        defaultIn(node);
    }

    public void outADivisionMultiplication(ADivisionMultiplication node)
    {
        defaultOut(node);
    }

    public void caseADivisionMultiplication(ADivisionMultiplication node)
    {
        inADivisionMultiplication(node);
        if(node.getMultiplication() != null)
        {
            node.getMultiplication().apply(this);
        }
        if(node.getDiv() != null)
        {
            node.getDiv().apply(this);
        }
        if(node.getPower() != null)
        {
            node.getPower().apply(this);
        }
        outADivisionMultiplication(node);
    }

    public void inAModuloMultiplication(AModuloMultiplication node)
    {
        defaultIn(node);
    }

    public void outAModuloMultiplication(AModuloMultiplication node)
    {
        defaultOut(node);
    }

    public void caseAModuloMultiplication(AModuloMultiplication node)
    {
        inAModuloMultiplication(node);
        if(node.getMultiplication() != null)
        {
            node.getMultiplication().apply(this);
        }
        if(node.getMod() != null)
        {
            node.getMod().apply(this);
        }
        if(node.getPower() != null)
        {
            node.getPower().apply(this);
        }
        outAModuloMultiplication(node);
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
        if(node.getMultiplication() != null)
        {
            node.getMultiplication().apply(this);
        }
        if(node.getMult() != null)
        {
            node.getMult().apply(this);
        }
        if(node.getPower() != null)
        {
            node.getPower().apply(this);
        }
        outAMultiplicationMultiplication(node);
    }

    public void inAValuePower(AValuePower node)
    {
        defaultIn(node);
    }

    public void outAValuePower(AValuePower node)
    {
        defaultOut(node);
    }

    public void caseAValuePower(AValuePower node)
    {
        inAValuePower(node);
        if(node.getValue() != null)
        {
            node.getValue().apply(this);
        }
        outAValuePower(node);
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
        if(node.getPower() != null)
        {
            node.getPower().apply(this);
        }
        if(node.getPow() != null)
        {
            node.getPow().apply(this);
        }
        if(node.getValue() != null)
        {
            node.getValue().apply(this);
        }
        outAPowerPower(node);
    }

    public void inAIdentifierValue(AIdentifierValue node)
    {
        defaultIn(node);
    }

    public void outAIdentifierValue(AIdentifierValue node)
    {
        defaultOut(node);
    }

    public void caseAIdentifierValue(AIdentifierValue node)
    {
        inAIdentifierValue(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAIdentifierValue(node);
    }

    public void inANumbValue(ANumbValue node)
    {
        defaultIn(node);
    }

    public void outANumbValue(ANumbValue node)
    {
        defaultOut(node);
    }

    public void caseANumbValue(ANumbValue node)
    {
        inANumbValue(node);
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
        }
        outANumbValue(node);
    }

    public void inAParValue(AParValue node)
    {
        defaultIn(node);
    }

    public void outAParValue(AParValue node)
    {
        defaultOut(node);
    }

    public void caseAParValue(AParValue node)
    {
        inAParValue(node);
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        outAParValue(node);
    }
}
