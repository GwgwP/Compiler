/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.analysis;

import minipython.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object in);
    Object getOut(Node node);
    void setOut(Node node, Object out);

    void caseStart(Start node);
    void caseAGoal(AGoal node);
    void caseAFuncCommands(AFuncCommands node);
    void caseAStatCommands(AStatCommands node);
    void caseADedeFunction(ADedeFunction node);
    void caseAFrfrfArgument(AFrfrfArgument node);
    void caseAFfCiav(AFfCiav node);
    void caseAFrfrfrrfrfAssignValue(AFrfrfrrfrfAssignValue node);
    void caseAIfStatementStatement(AIfStatementStatement node);
    void caseAWhileStatementStatement(AWhileStatementStatement node);
    void caseAForStatementStatement(AForStatementStatement node);
    void caseAReturnStatementStatement(AReturnStatementStatement node);
    void caseAPrintStatementStatement(APrintStatementStatement node);
    void caseAAssignStatement(AAssignStatement node);
    void caseAIdMineqStatement(AIdMineqStatement node);
    void caseAIdPluseqStatement(AIdPluseqStatement node);
    void caseAIdDiveqStatement(AIdDiveqStatement node);
    void caseAPinakasStatement(APinakasStatement node);
    void caseAAssertionStatement(AAssertionStatement node);
    void caseAFuncCallStatement(AFuncCallStatement node);
    void caseAEreaFunctionCall(AEreaFunctionCall node);
    void caseADdsasaArglist(ADdsasaArglist node);
    void caseAFrfrfrfrfeCommaExpression(AFrfrfrfrfeCommaExpression node);
    void caseADssComparison(ADssComparison node);
    void caseAOrcComparison(AOrcComparison node);
    void caseADdAfteror(ADdAfteror node);
    void caseAAndcAfteror(AAndcAfteror node);
    void caseAWAfterand(AWAfterand node);
    void caseANotcAfterand(ANotcAfterand node);
    void caseATrueAfternot(ATrueAfternot node);
    void caseAFalseAfternot(AFalseAfternot node);
    void caseALesseqAfternot(ALesseqAfternot node);
    void caseAGreateqAfternot(AGreateqAfternot node);
    void caseADiffAfternot(ADiffAfternot node);
    void caseAEqeqAfternot(AEqeqAfternot node);
    void caseALesscAfternot(ALesscAfternot node);
    void caseAGreatcAfternot(AGreatcAfternot node);
    void caseACalculationExpression(ACalculationExpression node);
    void caseAExprWCExpression(AExprWCExpression node);
    void caseAPinakasexpExpressionsWithoutCulc(APinakasexpExpressionsWithoutCulc node);
    void caseARedExpressionsWithoutCulc(ARedExpressionsWithoutCulc node);
    void caseALenExpExpressionsWithoutCulc(ALenExpExpressionsWithoutCulc node);
    void caseAMaxExExpressionsWithoutCulc(AMaxExExpressionsWithoutCulc node);
    void caseAMinExExpressionsWithoutCulc(AMinExExpressionsWithoutCulc node);
    void caseAMarikaaaaaaaaExpressionsWithoutCulc(AMarikaaaaaaaaExpressionsWithoutCulc node);
    void caseACvCommaValue(ACvCommaValue node);
    void caseAPlplCalculation(APlplCalculation node);
    void caseAMinminCalculation(AMinminCalculation node);
    void caseAAdditionCalculation(AAdditionCalculation node);
    void caseASubtractionCalculation(ASubtractionCalculation node);
    void caseAMultiplicationCalculation(AMultiplicationCalculation node);
    void caseAAdditionExCalculation(AAdditionExCalculation node);
    void caseASubtractionExCalculation(ASubtractionExCalculation node);
    void caseAPowMultiplication(APowMultiplication node);
    void caseADivisionMultiplication(ADivisionMultiplication node);
    void caseAModuloMultiplication(AModuloMultiplication node);
    void caseAMultiplicationMultiplication(AMultiplicationMultiplication node);
    void caseADivisionezMultiplication(ADivisionezMultiplication node);
    void caseAModuloezMultiplication(AModuloezMultiplication node);
    void caseAMultiplicationezMultiplication(AMultiplicationezMultiplication node);
    void caseAValuePower(AValuePower node);
    void caseAPowerPower(APowerPower node);
    void caseAPowe2rPower(APowe2rPower node);
    void caseAIddotValue(AIddotValue node);
    void caseAIdentifierValue(AIdentifierValue node);
    void caseANumbValue(ANumbValue node);
    void caseADValue(ADValue node);
    void caseAWeValue(AWeValue node);
    void caseAPsrValue(APsrValue node);
    void caseANonenonegoodValue(ANonenonegoodValue node);
    void caseAEIdent(AEIdent node);

    void caseTTab(TTab node);
    void caseTPlusplus(TPlusplus node);
    void caseTEqualequal(TEqualequal node);
    void caseTMinusminus(TMinusminus node);
    void caseTMineq(TMineq node);
    void caseTPluseq(TPluseq node);
    void caseTDiveq(TDiveq node);
    void caseTMulteq(TMulteq node);
    void caseTPlus(TPlus node);
    void caseTMinus(TMinus node);
    void caseTPow(TPow node);
    void caseTMult(TMult node);
    void caseTMod(TMod node);
    void caseTDiv(TDiv node);
    void caseTNoteq(TNoteq node);
    void caseTLesseq(TLesseq node);
    void caseTGreateq(TGreateq node);
    void caseTLess(TLess node);
    void caseTGreat(TGreat node);
    void caseTAssign(TAssign node);
    void caseTDef(TDef node);
    void caseTNot(TNot node);
    void caseTLogicAnd(TLogicAnd node);
    void caseTLogicOr(TLogicOr node);
    void caseTLBr(TLBr node);
    void caseTRBr(TRBr node);
    void caseTLPar(TLPar node);
    void caseTRPar(TRPar node);
    void caseTComma(TComma node);
    void caseTIn(TIn node);
    void caseTIf(TIf node);
    void caseTWhile(TWhile node);
    void caseTFor(TFor node);
    void caseTLen(TLen node);
    void caseTMin(TMin node);
    void caseTMax(TMax node);
    void caseTPrint(TPrint node);
    void caseTReturn(TReturn node);
    void caseTAssert(TAssert node);
    void caseTTrue(TTrue node);
    void caseTSemi(TSemi node);
    void caseTFalse(TFalse node);
    void caseTElse(TElse node);
    void caseTNone(TNone node);
    void caseTQuote(TQuote node);
    void caseTBlank(TBlank node);
    void caseTWhiteSpace(TWhiteSpace node);
    void caseTLineComment(TLineComment node);
    void caseTNumber(TNumber node);
    void caseTDot(TDot node);
    void caseTId(TId node);
    void caseTStringDoubleQuotes(TStringDoubleQuotes node);
    void caseTStringSingleQuotes(TStringSingleQuotes node);
    void caseTEverythingElse(TEverythingElse node);
    void caseEOF(EOF node);
}
