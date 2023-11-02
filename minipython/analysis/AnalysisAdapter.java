/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.analysis;

import java.util.*;
import minipython.node.*;

public class AnalysisAdapter implements Analysis
{
    private Hashtable in;
    private Hashtable out;

    public Object getIn(Node node)
    {
        if(in == null)
        {
            return null;
        }

        return in.get(node);
    }

    public void setIn(Node node, Object in)
    {
        if(this.in == null)
        {
            this.in = new Hashtable(1);
        }

        if(in != null)
        {
            this.in.put(node, in);
        }
        else
        {
            this.in.remove(node);
        }
    }

    public Object getOut(Node node)
    {
        if(out == null)
        {
            return null;
        }

        return out.get(node);
    }

    public void setOut(Node node, Object out)
    {
        if(this.out == null)
        {
            this.out = new Hashtable(1);
        }

        if(out != null)
        {
            this.out.put(node, out);
        }
        else
        {
            this.out.remove(node);
        }
    }
    public void caseStart(Start node)
    {
        defaultCase(node);
    }

    public void caseAGoal(AGoal node)
    {
        defaultCase(node);
    }

    public void caseAFuncCommands(AFuncCommands node)
    {
        defaultCase(node);
    }

    public void caseAStatCommands(AStatCommands node)
    {
        defaultCase(node);
    }

    public void caseADedeFunction(ADedeFunction node)
    {
        defaultCase(node);
    }

    public void caseAFrfrfArgument(AFrfrfArgument node)
    {
        defaultCase(node);
    }

    public void caseAFfCiav(AFfCiav node)
    {
        defaultCase(node);
    }

    public void caseAFrfrfrrfrfAssignValue(AFrfrfrrfrfAssignValue node)
    {
        defaultCase(node);
    }

    public void caseAIfStatementStatement(AIfStatementStatement node)
    {
        defaultCase(node);
    }

    public void caseAAssignStatement(AAssignStatement node)
    {
        defaultCase(node);
    }

    public void caseAPrintStatement(APrintStatement node)
    {
        defaultCase(node);
    }

    public void caseATrueComparison(ATrueComparison node)
    {
        defaultCase(node);
    }

    public void caseAFalseComparison(AFalseComparison node)
    {
        defaultCase(node);
    }

    public void caseALesseqComparison(ALesseqComparison node)
    {
        defaultCase(node);
    }

    public void caseAGreateqComparison(AGreateqComparison node)
    {
        defaultCase(node);
    }

    public void caseADiffComparison(ADiffComparison node)
    {
        defaultCase(node);
    }

    public void caseAEqeqComparison(AEqeqComparison node)
    {
        defaultCase(node);
    }

    public void caseALesscComparison(ALesscComparison node)
    {
        defaultCase(node);
    }

    public void caseAGreatcComparison(AGreatcComparison node)
    {
        defaultCase(node);
    }

    public void caseAAdditionExpression(AAdditionExpression node)
    {
        defaultCase(node);
    }

    public void caseASubtractionExpression(ASubtractionExpression node)
    {
        defaultCase(node);
    }

    public void caseAMultiplicationExpression(AMultiplicationExpression node)
    {
        defaultCase(node);
    }

    public void caseAPowMultiplication(APowMultiplication node)
    {
        defaultCase(node);
    }

    public void caseADivisionMultiplication(ADivisionMultiplication node)
    {
        defaultCase(node);
    }

    public void caseAModuloMultiplication(AModuloMultiplication node)
    {
        defaultCase(node);
    }

    public void caseAMultiplicationMultiplication(AMultiplicationMultiplication node)
    {
        defaultCase(node);
    }

    public void caseAValuePower(AValuePower node)
    {
        defaultCase(node);
    }

    public void caseAPowerPower(APowerPower node)
    {
        defaultCase(node);
    }

    public void caseAIdentifierValue(AIdentifierValue node)
    {
        defaultCase(node);
    }

    public void caseANumbValue(ANumbValue node)
    {
        defaultCase(node);
    }

    public void caseAParValue(AParValue node)
    {
        defaultCase(node);
    }

    public void caseTTab(TTab node)
    {
        defaultCase(node);
    }

    public void caseTPlusplus(TPlusplus node)
    {
        defaultCase(node);
    }

    public void caseTEqualequal(TEqualequal node)
    {
        defaultCase(node);
    }

    public void caseTMinusminus(TMinusminus node)
    {
        defaultCase(node);
    }

    public void caseTMineq(TMineq node)
    {
        defaultCase(node);
    }

    public void caseTPluseq(TPluseq node)
    {
        defaultCase(node);
    }

    public void caseTDiveq(TDiveq node)
    {
        defaultCase(node);
    }

    public void caseTMulteq(TMulteq node)
    {
        defaultCase(node);
    }

    public void caseTPlus(TPlus node)
    {
        defaultCase(node);
    }

    public void caseTMinus(TMinus node)
    {
        defaultCase(node);
    }

    public void caseTPow(TPow node)
    {
        defaultCase(node);
    }

    public void caseTMult(TMult node)
    {
        defaultCase(node);
    }

    public void caseTMod(TMod node)
    {
        defaultCase(node);
    }

    public void caseTDiv(TDiv node)
    {
        defaultCase(node);
    }

    public void caseTEq(TEq node)
    {
        defaultCase(node);
    }

    public void caseTDef(TDef node)
    {
        defaultCase(node);
    }

    public void caseTNot(TNot node)
    {
        defaultCase(node);
    }

    public void caseTLogicAnd(TLogicAnd node)
    {
        defaultCase(node);
    }

    public void caseTLogicOr(TLogicOr node)
    {
        defaultCase(node);
    }

    public void caseTLBr(TLBr node)
    {
        defaultCase(node);
    }

    public void caseTRBr(TRBr node)
    {
        defaultCase(node);
    }

    public void caseTLPar(TLPar node)
    {
        defaultCase(node);
    }

    public void caseTRPar(TRPar node)
    {
        defaultCase(node);
    }

    public void caseTComma(TComma node)
    {
        defaultCase(node);
    }

    public void caseTIn(TIn node)
    {
        defaultCase(node);
    }

    public void caseTIf(TIf node)
    {
        defaultCase(node);
    }

    public void caseTWhile(TWhile node)
    {
        defaultCase(node);
    }

    public void caseTFor(TFor node)
    {
        defaultCase(node);
    }

    public void caseTLen(TLen node)
    {
        defaultCase(node);
    }

    public void caseTMin(TMin node)
    {
        defaultCase(node);
    }

    public void caseTMax(TMax node)
    {
        defaultCase(node);
    }

    public void caseTPrint(TPrint node)
    {
        defaultCase(node);
    }

    public void caseTReturn(TReturn node)
    {
        defaultCase(node);
    }

    public void caseTAssert(TAssert node)
    {
        defaultCase(node);
    }

    public void caseTNoteq(TNoteq node)
    {
        defaultCase(node);
    }

    public void caseTLesseq(TLesseq node)
    {
        defaultCase(node);
    }

    public void caseTGreateq(TGreateq node)
    {
        defaultCase(node);
    }

    public void caseTLess(TLess node)
    {
        defaultCase(node);
    }

    public void caseTGreat(TGreat node)
    {
        defaultCase(node);
    }

    public void caseTTrue(TTrue node)
    {
        defaultCase(node);
    }

    public void caseTSemi(TSemi node)
    {
        defaultCase(node);
    }

    public void caseTFalse(TFalse node)
    {
        defaultCase(node);
    }

    public void caseTElse(TElse node)
    {
        defaultCase(node);
    }

    public void caseTNone(TNone node)
    {
        defaultCase(node);
    }

    public void caseTQuote(TQuote node)
    {
        defaultCase(node);
    }

    public void caseTBlank(TBlank node)
    {
        defaultCase(node);
    }

    public void caseTWhiteSpace(TWhiteSpace node)
    {
        defaultCase(node);
    }

    public void caseTLineComment(TLineComment node)
    {
        defaultCase(node);
    }

    public void caseTNumber(TNumber node)
    {
        defaultCase(node);
    }

    public void caseTDot(TDot node)
    {
        defaultCase(node);
    }

    public void caseTId(TId node)
    {
        defaultCase(node);
    }

    public void caseTStringDoubleQuotes(TStringDoubleQuotes node)
    {
        defaultCase(node);
    }

    public void caseTStringSingleQuotes(TStringSingleQuotes node)
    {
        defaultCase(node);
    }

    public void caseTEverythingElse(TEverythingElse node)
    {
        defaultCase(node);
    }

    public void caseEOF(EOF node)
    {
        defaultCase(node);
    }

    public void defaultCase(Node node)
    {
    }
}
