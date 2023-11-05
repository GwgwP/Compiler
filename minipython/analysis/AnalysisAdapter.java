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

    public void caseAWhileStatementStatement(AWhileStatementStatement node)
    {
        defaultCase(node);
    }

    public void caseAForStatementStatement(AForStatementStatement node)
    {
        defaultCase(node);
    }

    public void caseAReturnStatementStatement(AReturnStatementStatement node)
    {
        defaultCase(node);
    }

    public void caseAPrintStatementStatement(APrintStatementStatement node)
    {
        defaultCase(node);
    }

    public void caseAAssignStatement(AAssignStatement node)
    {
        defaultCase(node);
    }

    public void caseAIdMineqStatement(AIdMineqStatement node)
    {
        defaultCase(node);
    }

    public void caseAIdPluseqStatement(AIdPluseqStatement node)
    {
        defaultCase(node);
    }

    public void caseAIdDiveqStatement(AIdDiveqStatement node)
    {
        defaultCase(node);
    }

    public void caseAPinakasStatement(APinakasStatement node)
    {
        defaultCase(node);
    }

    public void caseAAssertionStatement(AAssertionStatement node)
    {
        defaultCase(node);
    }

    public void caseAFuncCallStatement(AFuncCallStatement node)
    {
        defaultCase(node);
    }

    public void caseAEreaFunctionCall(AEreaFunctionCall node)
    {
        defaultCase(node);
    }

    public void caseADdsasaArglist(ADdsasaArglist node)
    {
        defaultCase(node);
    }

    public void caseAFrfrfrfrfeCommaExpression(AFrfrfrfrfeCommaExpression node)
    {
        defaultCase(node);
    }

    public void caseADssComparison(ADssComparison node)
    {
        defaultCase(node);
    }

    public void caseAOrcComparison(AOrcComparison node)
    {
        defaultCase(node);
    }

    public void caseADdAfteror(ADdAfteror node)
    {
        defaultCase(node);
    }

    public void caseAAndcAfteror(AAndcAfteror node)
    {
        defaultCase(node);
    }

    public void caseAWAfterand(AWAfterand node)
    {
        defaultCase(node);
    }

    public void caseANotcAfterand(ANotcAfterand node)
    {
        defaultCase(node);
    }

    public void caseATrueAfternot(ATrueAfternot node)
    {
        defaultCase(node);
    }

    public void caseAFalseAfternot(AFalseAfternot node)
    {
        defaultCase(node);
    }

    public void caseALesseqAfternot(ALesseqAfternot node)
    {
        defaultCase(node);
    }

    public void caseAGreateqAfternot(AGreateqAfternot node)
    {
        defaultCase(node);
    }

    public void caseADiffAfternot(ADiffAfternot node)
    {
        defaultCase(node);
    }

    public void caseAEqeqAfternot(AEqeqAfternot node)
    {
        defaultCase(node);
    }

    public void caseALesscAfternot(ALesscAfternot node)
    {
        defaultCase(node);
    }

    public void caseAGreatcAfternot(AGreatcAfternot node)
    {
        defaultCase(node);
    }

    public void caseACalculationExpression(ACalculationExpression node)
    {
        defaultCase(node);
    }

    public void caseAExprWCExpression(AExprWCExpression node)
    {
        defaultCase(node);
    }

    public void caseAPinakasexpExpressionsWithoutCulc(APinakasexpExpressionsWithoutCulc node)
    {
        defaultCase(node);
    }

    public void caseARedExpressionsWithoutCulc(ARedExpressionsWithoutCulc node)
    {
        defaultCase(node);
    }

    public void caseALenExpExpressionsWithoutCulc(ALenExpExpressionsWithoutCulc node)
    {
        defaultCase(node);
    }

    public void caseAMaxExExpressionsWithoutCulc(AMaxExExpressionsWithoutCulc node)
    {
        defaultCase(node);
    }

    public void caseAMinExExpressionsWithoutCulc(AMinExExpressionsWithoutCulc node)
    {
        defaultCase(node);
    }

    public void caseAMarikaaaaaaaaExpressionsWithoutCulc(AMarikaaaaaaaaExpressionsWithoutCulc node)
    {
        defaultCase(node);
    }

    public void caseACvCommaValue(ACvCommaValue node)
    {
        defaultCase(node);
    }

    public void caseAPlplCalculation(APlplCalculation node)
    {
        defaultCase(node);
    }

    public void caseAMinminCalculation(AMinminCalculation node)
    {
        defaultCase(node);
    }

    public void caseAAdditionCalculation(AAdditionCalculation node)
    {
        defaultCase(node);
    }

    public void caseASubtractionCalculation(ASubtractionCalculation node)
    {
        defaultCase(node);
    }

    public void caseAMultiplicationCalculation(AMultiplicationCalculation node)
    {
        defaultCase(node);
    }

    public void caseAAdditionExCalculation(AAdditionExCalculation node)
    {
        defaultCase(node);
    }

    public void caseASubtractionExCalculation(ASubtractionExCalculation node)
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

    public void caseADivisionezMultiplication(ADivisionezMultiplication node)
    {
        defaultCase(node);
    }

    public void caseAModuloezMultiplication(AModuloezMultiplication node)
    {
        defaultCase(node);
    }

    public void caseAMultiplicationezMultiplication(AMultiplicationezMultiplication node)
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

    public void caseAPowe2rPower(APowe2rPower node)
    {
        defaultCase(node);
    }

    public void caseAIddotValue(AIddotValue node)
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

    public void caseADValue(ADValue node)
    {
        defaultCase(node);
    }

    public void caseAWeValue(AWeValue node)
    {
        defaultCase(node);
    }

    public void caseAPsrValue(APsrValue node)
    {
        defaultCase(node);
    }

    public void caseANonenonegoodValue(ANonenonegoodValue node)
    {
        defaultCase(node);
    }

    public void caseAEIdent(AEIdent node)
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

    public void caseTAssign(TAssign node)
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
