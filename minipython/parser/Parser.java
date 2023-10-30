/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.parser;

import minipython.lexer.*;
import minipython.node.*;
import minipython.analysis.*;
import java.util.*;

import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;

public class Parser
{
    public final Analysis ignoredTokens = new AnalysisAdapter();

    protected ArrayList nodeList;

    private final Lexer lexer;
    private final ListIterator stack = new LinkedList().listIterator();
    private int last_shift;
    private int last_pos;
    private int last_line;
    private Token last_token;
    private final TokenIndex converter = new TokenIndex();
    private final int[] action = new int[2];

    private final static int SHIFT = 0;
    private final static int REDUCE = 1;
    private final static int ACCEPT = 2;
    private final static int ERROR = 3;

    public Parser(Lexer lexer)
    {
        this.lexer = lexer;

        if(actionTable == null)
        {
            try
            {
                DataInputStream s = new DataInputStream(
                    new BufferedInputStream(
                    Parser.class.getResourceAsStream("parser.dat")));

                // read actionTable
                int length = s.readInt();
                actionTable = new int[length][][];
                for(int i = 0; i < actionTable.length; i++)
                {
                    length = s.readInt();
                    actionTable[i] = new int[length][3];
                    for(int j = 0; j < actionTable[i].length; j++)
                    {
                        for(int k = 0; k < 3; k++)
                        {
                            actionTable[i][j][k] = s.readInt();
                        }
                    }
                }

                // read gotoTable
                length = s.readInt();
                gotoTable = new int[length][][];
                for(int i = 0; i < gotoTable.length; i++)
                {
                    length = s.readInt();
                    gotoTable[i] = new int[length][2];
                    for(int j = 0; j < gotoTable[i].length; j++)
                    {
                        for(int k = 0; k < 2; k++)
                        {
                            gotoTable[i][j][k] = s.readInt();
                        }
                    }
                }

                // read errorMessages
                length = s.readInt();
                errorMessages = new String[length];
                for(int i = 0; i < errorMessages.length; i++)
                {
                    length = s.readInt();
                    StringBuffer buffer = new StringBuffer();

                    for(int j = 0; j < length; j++)
                    {
                        buffer.append(s.readChar());
                    }
                    errorMessages[i] = buffer.toString();
                }

                // read errors
                length = s.readInt();
                errors = new int[length];
                for(int i = 0; i < errors.length; i++)
                {
                    errors[i] = s.readInt();
                }

                s.close();
            }
            catch(Exception e)
            {
                throw new RuntimeException("The file \"parser.dat\" is either missing or corrupted.");
            }
        }
    }

    private int goTo(int index)
    {
        int state = state();
        int low = 1;
        int high = gotoTable[index].length - 1;
        int value = gotoTable[index][0][1];

        while(low <= high)
        {
            int middle = (low + high) / 2;

            if(state < gotoTable[index][middle][0])
            {
                high = middle - 1;
            }
            else if(state > gotoTable[index][middle][0])
            {
                low = middle + 1;
            }
            else
            {
                value = gotoTable[index][middle][1];
                break;
            }
        }

        return value;
    }

    private void push(int numstate, ArrayList listNode) throws ParserException, LexerException, IOException
    {
	this.nodeList = listNode;

        if(!stack.hasNext())
        {
            stack.add(new State(numstate, this.nodeList));
            return;
        }

        State s = (State) stack.next();
        s.state = numstate;
        s.nodes = this.nodeList;
    }

    private int state()
    {
        State s = (State) stack.previous();
        stack.next();
        return s.state;
    }

    private ArrayList pop()
    {
        return (ArrayList) ((State) stack.previous()).nodes;
    }

    private int index(Switchable token)
    {
        converter.index = -1;
        token.apply(converter);
        return converter.index;
    }

    public Start parse() throws ParserException, LexerException, IOException
    {
        push(0, null);

        List ign = null;
        while(true)
        {
            while(index(lexer.peek()) == -1)
            {
                if(ign == null)
                {
                    ign = new TypedLinkedList(NodeCast.instance);
                }

                ign.add(lexer.next());
            }

            if(ign != null)
            {
                ignoredTokens.setIn(lexer.peek(), ign);
                ign = null;
            }

            last_pos = lexer.peek().getPos();
            last_line = lexer.peek().getLine();
            last_token = lexer.peek();

            int index = index(lexer.peek());
            action[0] = actionTable[state()][0][1];
            action[1] = actionTable[state()][0][2];

            int low = 1;
            int high = actionTable[state()].length - 1;

            while(low <= high)
            {
                int middle = (low + high) / 2;

                if(index < actionTable[state()][middle][0])
                {
                    high = middle - 1;
                }
                else if(index > actionTable[state()][middle][0])
                {
                    low = middle + 1;
                }
                else
                {
                    action[0] = actionTable[state()][middle][1];
                    action[1] = actionTable[state()][middle][2];
                    break;
                }
            }

            switch(action[0])
            {
                case SHIFT:
		    {
		        ArrayList list = new ArrayList();
		        list.add(lexer.next());
                        push(action[1], list);
                        last_shift = action[1];
                    }
		    break;
                case REDUCE:
                    switch(action[1])
                    {

                    case 0:
		    {
			ArrayList list = new0();
			push(goTo(0), list);
		    }
		    break;


                    case 1:
		    {
			ArrayList list = new1();
			push(goTo(0), list);
		    }
		    break;


                    case 2:
		    {
			ArrayList list = new2();
			push(goTo(1), list);
		    }
		    break;


                    case 3:
		    {
			ArrayList list = new3();
			push(goTo(1), list);
		    }
		    break;


                    case 4:
		    {
			ArrayList list = new4();
			push(goTo(2), list);
		    }
		    break;


                    case 5:
		    {
			ArrayList list = new5();
			push(goTo(2), list);
		    }
		    break;


                    case 6:
		    {
			ArrayList list = new6();
			push(goTo(2), list);
		    }
		    break;


                    case 7:
		    {
			ArrayList list = new7();
			push(goTo(2), list);
		    }
		    break;


                    case 8:
		    {
			ArrayList list = new8();
			push(goTo(2), list);
		    }
		    break;


                    case 9:
		    {
			ArrayList list = new9();
			push(goTo(2), list);
		    }
		    break;


                    case 10:
		    {
			ArrayList list = new10();
			push(goTo(3), list);
		    }
		    break;


                    case 11:
		    {
			ArrayList list = new11();
			push(goTo(3), list);
		    }
		    break;


                    case 12:
		    {
			ArrayList list = new12();
			push(goTo(3), list);
		    }
		    break;


                    case 13:
		    {
			ArrayList list = new13();
			push(goTo(3), list);
		    }
		    break;


                    case 14:
		    {
			ArrayList list = new14();
			push(goTo(4), list);
		    }
		    break;


                    case 15:
		    {
			ArrayList list = new15();
			push(goTo(4), list);
		    }
		    break;


                    case 16:
		    {
			ArrayList list = new16();
			push(goTo(4), list);
		    }
		    break;


                    case 17:
		    {
			ArrayList list = new17();
			push(goTo(5), list);
		    }
		    break;


                    case 18:
		    {
			ArrayList list = new18();
			push(goTo(5), list);
		    }
		    break;


                    case 19:
		    {
			ArrayList list = new19();
			push(goTo(6), list);
		    }
		    break;


                    case 20:
		    {
			ArrayList list = new20();
			push(goTo(6), list);
		    }
		    break;


                    case 21:
		    {
			ArrayList list = new21();
			push(goTo(7), list);
		    }
		    break;


                    case 22:
		    {
			ArrayList list = new22();
			push(goTo(7), list);
		    }
		    break;


                    case 23:
		    {
			ArrayList list = new23();
			push(goTo(7), list);
		    }
		    break;


                    case 24:
		    {
			ArrayList list = new24();
			push(goTo(8), list);
		    }
		    break;


                    case 25:
		    {
			ArrayList list = new25();
			push(goTo(8), list);
		    }
		    break;


                    case 26:
		    {
			ArrayList list = new26();
			push(goTo(9), list);
		    }
		    break;


                    case 27:
		    {
			ArrayList list = new27();
			push(goTo(9), list);
		    }
		    break;

                    }
                    break;
                case ACCEPT:
                    {
                        EOF node2 = (EOF) lexer.next();
                        PProgramme node1 = (PProgramme) ((ArrayList)pop()).get(0);
                        Start node = new Start(node1, node2);
                        return node;
                    }
                case ERROR:
                    throw new ParserException(last_token,
                        "[" + last_line + "," + last_pos + "] " +
                        errorMessages[errors[action[1]]]);
            }
        }
    }



    ArrayList new0()
    {
        ArrayList nodeList = new ArrayList();

        PProgramme pprogrammeNode1;
        {
        TypedLinkedList listNode2 = new TypedLinkedList();
        {
        }

        pprogrammeNode1 = new AProgramme(listNode2);
        }
	nodeList.add(pprogrammeNode1);
        return nodeList;
    }



    ArrayList new1()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList1 = (ArrayList) pop();
        PProgramme pprogrammeNode1;
        {
        TypedLinkedList listNode3 = new TypedLinkedList();
        {
        TypedLinkedList listNode2 = new TypedLinkedList();
        listNode2 = (TypedLinkedList)nodeArrayList1.get(0);
	if(listNode2 != null)
	{
	  listNode3.addAll(listNode2);
	}
        }

        pprogrammeNode1 = new AProgramme(listNode3);
        }
	nodeList.add(pprogrammeNode1);
        return nodeList;
    }



    ArrayList new2()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList1 = (ArrayList) pop();
        PCommands pcommandsNode1;
        {
        PStatement pstatementNode2;
        pstatementNode2 = (PStatement)nodeArrayList1.get(0);

        pcommandsNode1 = new AStatCommands(pstatementNode2);
        }
	nodeList.add(pcommandsNode1);
        return nodeList;
    }



    ArrayList new3()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList1 = (ArrayList) pop();
        PCommands pcommandsNode1;
        {
        PExpression pexpressionNode2;
        pexpressionNode2 = (PExpression)nodeArrayList1.get(0);

        pcommandsNode1 = new AExprCommands(pexpressionNode2);
        }
	nodeList.add(pcommandsNode1);
        return nodeList;
    }



    ArrayList new4()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList4 = (ArrayList) pop();
        ArrayList nodeArrayList3 = (ArrayList) pop();
        ArrayList nodeArrayList2 = (ArrayList) pop();
        ArrayList nodeArrayList1 = (ArrayList) pop();
        PStatement pstatementNode1;
        {
        TypedLinkedList listNode2 = new TypedLinkedList();
        TIf tifNode3;
        PComparison pcomparisonNode4;
        TSemi tsemiNode5;
        PStatement pstatementNode6;
        {
        }
        tifNode3 = (TIf)nodeArrayList1.get(0);
        pcomparisonNode4 = (PComparison)nodeArrayList2.get(0);
        tsemiNode5 = (TSemi)nodeArrayList3.get(0);
        pstatementNode6 = (PStatement)nodeArrayList4.get(0);

        pstatementNode1 = new AIfStatement(listNode2, tifNode3, pcomparisonNode4, tsemiNode5, pstatementNode6);
        }
	nodeList.add(pstatementNode1);
        return nodeList;
    }



    ArrayList new5()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList5 = (ArrayList) pop();
        ArrayList nodeArrayList4 = (ArrayList) pop();
        ArrayList nodeArrayList3 = (ArrayList) pop();
        ArrayList nodeArrayList2 = (ArrayList) pop();
        ArrayList nodeArrayList1 = (ArrayList) pop();
        PStatement pstatementNode1;
        {
        TypedLinkedList listNode3 = new TypedLinkedList();
        TIf tifNode4;
        PComparison pcomparisonNode5;
        TSemi tsemiNode6;
        PStatement pstatementNode7;
        {
        TypedLinkedList listNode2 = new TypedLinkedList();
        listNode2 = (TypedLinkedList)nodeArrayList1.get(0);
	if(listNode2 != null)
	{
	  listNode3.addAll(listNode2);
	}
        }
        tifNode4 = (TIf)nodeArrayList2.get(0);
        pcomparisonNode5 = (PComparison)nodeArrayList3.get(0);
        tsemiNode6 = (TSemi)nodeArrayList4.get(0);
        pstatementNode7 = (PStatement)nodeArrayList5.get(0);

        pstatementNode1 = new AIfStatement(listNode3, tifNode4, pcomparisonNode5, tsemiNode6, pstatementNode7);
        }
	nodeList.add(pstatementNode1);
        return nodeList;
    }



    ArrayList new6()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList3 = (ArrayList) pop();
        ArrayList nodeArrayList2 = (ArrayList) pop();
        ArrayList nodeArrayList1 = (ArrayList) pop();
        PStatement pstatementNode1;
        {
        TypedLinkedList listNode2 = new TypedLinkedList();
        TId tidNode3;
        TEq teqNode4;
        PExpression pexpressionNode5;
        {
        }
        tidNode3 = (TId)nodeArrayList1.get(0);
        teqNode4 = (TEq)nodeArrayList2.get(0);
        pexpressionNode5 = (PExpression)nodeArrayList3.get(0);

        pstatementNode1 = new AAssignStatement(listNode2, tidNode3, teqNode4, pexpressionNode5);
        }
	nodeList.add(pstatementNode1);
        return nodeList;
    }



    ArrayList new7()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList4 = (ArrayList) pop();
        ArrayList nodeArrayList3 = (ArrayList) pop();
        ArrayList nodeArrayList2 = (ArrayList) pop();
        ArrayList nodeArrayList1 = (ArrayList) pop();
        PStatement pstatementNode1;
        {
        TypedLinkedList listNode3 = new TypedLinkedList();
        TId tidNode4;
        TEq teqNode5;
        PExpression pexpressionNode6;
        {
        TypedLinkedList listNode2 = new TypedLinkedList();
        listNode2 = (TypedLinkedList)nodeArrayList1.get(0);
	if(listNode2 != null)
	{
	  listNode3.addAll(listNode2);
	}
        }
        tidNode4 = (TId)nodeArrayList2.get(0);
        teqNode5 = (TEq)nodeArrayList3.get(0);
        pexpressionNode6 = (PExpression)nodeArrayList4.get(0);

        pstatementNode1 = new AAssignStatement(listNode3, tidNode4, teqNode5, pexpressionNode6);
        }
	nodeList.add(pstatementNode1);
        return nodeList;
    }



    ArrayList new8()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList2 = (ArrayList) pop();
        ArrayList nodeArrayList1 = (ArrayList) pop();
        PStatement pstatementNode1;
        {
        TypedLinkedList listNode2 = new TypedLinkedList();
        TPrint tprintNode3;
        PExpression pexpressionNode4;
        {
        }
        tprintNode3 = (TPrint)nodeArrayList1.get(0);
        pexpressionNode4 = (PExpression)nodeArrayList2.get(0);

        pstatementNode1 = new APrintStatement(listNode2, tprintNode3, pexpressionNode4);
        }
	nodeList.add(pstatementNode1);
        return nodeList;
    }



    ArrayList new9()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList3 = (ArrayList) pop();
        ArrayList nodeArrayList2 = (ArrayList) pop();
        ArrayList nodeArrayList1 = (ArrayList) pop();
        PStatement pstatementNode1;
        {
        TypedLinkedList listNode3 = new TypedLinkedList();
        TPrint tprintNode4;
        PExpression pexpressionNode5;
        {
        TypedLinkedList listNode2 = new TypedLinkedList();
        listNode2 = (TypedLinkedList)nodeArrayList1.get(0);
	if(listNode2 != null)
	{
	  listNode3.addAll(listNode2);
	}
        }
        tprintNode4 = (TPrint)nodeArrayList2.get(0);
        pexpressionNode5 = (PExpression)nodeArrayList3.get(0);

        pstatementNode1 = new APrintStatement(listNode3, tprintNode4, pexpressionNode5);
        }
	nodeList.add(pstatementNode1);
        return nodeList;
    }



    ArrayList new10()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList1 = (ArrayList) pop();
        PComparison pcomparisonNode1;
        {
        TTrue ttrueNode2;
        ttrueNode2 = (TTrue)nodeArrayList1.get(0);

        pcomparisonNode1 = new ATrueComparison(ttrueNode2);
        }
	nodeList.add(pcomparisonNode1);
        return nodeList;
    }



    ArrayList new11()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList1 = (ArrayList) pop();
        PComparison pcomparisonNode1;
        {
        TFalse tfalseNode2;
        tfalseNode2 = (TFalse)nodeArrayList1.get(0);

        pcomparisonNode1 = new AFalseComparison(tfalseNode2);
        }
	nodeList.add(pcomparisonNode1);
        return nodeList;
    }



    ArrayList new12()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList3 = (ArrayList) pop();
        ArrayList nodeArrayList2 = (ArrayList) pop();
        ArrayList nodeArrayList1 = (ArrayList) pop();
        PComparison pcomparisonNode1;
        {
        PExpression pexpressionNode2;
        TLess tlessNode3;
        PExpression pexpressionNode4;
        pexpressionNode2 = (PExpression)nodeArrayList1.get(0);
        tlessNode3 = (TLess)nodeArrayList2.get(0);
        pexpressionNode4 = (PExpression)nodeArrayList3.get(0);

        pcomparisonNode1 = new ALesscComparison(pexpressionNode2, tlessNode3, pexpressionNode4);
        }
	nodeList.add(pcomparisonNode1);
        return nodeList;
    }



    ArrayList new13()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList3 = (ArrayList) pop();
        ArrayList nodeArrayList2 = (ArrayList) pop();
        ArrayList nodeArrayList1 = (ArrayList) pop();
        PComparison pcomparisonNode1;
        {
        PExpression pexpressionNode2;
        TGreat tgreatNode3;
        PExpression pexpressionNode4;
        pexpressionNode2 = (PExpression)nodeArrayList1.get(0);
        tgreatNode3 = (TGreat)nodeArrayList2.get(0);
        pexpressionNode4 = (PExpression)nodeArrayList3.get(0);

        pcomparisonNode1 = new AGreatcComparison(pexpressionNode2, tgreatNode3, pexpressionNode4);
        }
	nodeList.add(pcomparisonNode1);
        return nodeList;
    }



    ArrayList new14()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList1 = (ArrayList) pop();
        PExpression pexpressionNode1;
        {
        PMultiplication pmultiplicationNode2;
        pmultiplicationNode2 = (PMultiplication)nodeArrayList1.get(0);

        pexpressionNode1 = new AMultiplicationExpression(pmultiplicationNode2);
        }
	nodeList.add(pexpressionNode1);
        return nodeList;
    }



    ArrayList new15()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList3 = (ArrayList) pop();
        ArrayList nodeArrayList2 = (ArrayList) pop();
        ArrayList nodeArrayList1 = (ArrayList) pop();
        PExpression pexpressionNode1;
        {
        PExpression pexpressionNode2;
        TPlus tplusNode3;
        PMultiplication pmultiplicationNode4;
        pexpressionNode2 = (PExpression)nodeArrayList1.get(0);
        tplusNode3 = (TPlus)nodeArrayList2.get(0);
        pmultiplicationNode4 = (PMultiplication)nodeArrayList3.get(0);

        pexpressionNode1 = new AAdditionExpression(pexpressionNode2, tplusNode3, pmultiplicationNode4);
        }
	nodeList.add(pexpressionNode1);
        return nodeList;
    }



    ArrayList new16()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList3 = (ArrayList) pop();
        ArrayList nodeArrayList2 = (ArrayList) pop();
        ArrayList nodeArrayList1 = (ArrayList) pop();
        PExpression pexpressionNode1;
        {
        PExpression pexpressionNode2;
        TMinus tminusNode3;
        PMultiplication pmultiplicationNode4;
        pexpressionNode2 = (PExpression)nodeArrayList1.get(0);
        tminusNode3 = (TMinus)nodeArrayList2.get(0);
        pmultiplicationNode4 = (PMultiplication)nodeArrayList3.get(0);

        pexpressionNode1 = new ASubtractionExpression(pexpressionNode2, tminusNode3, pmultiplicationNode4);
        }
	nodeList.add(pexpressionNode1);
        return nodeList;
    }



    ArrayList new17()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList1 = (ArrayList) pop();
        PMultiplication pmultiplicationNode1;
        {
        PPower ppowerNode2;
        ppowerNode2 = (PPower)nodeArrayList1.get(0);

        pmultiplicationNode1 = new APowMultiplication(ppowerNode2);
        }
	nodeList.add(pmultiplicationNode1);
        return nodeList;
    }



    ArrayList new18()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList3 = (ArrayList) pop();
        ArrayList nodeArrayList2 = (ArrayList) pop();
        ArrayList nodeArrayList1 = (ArrayList) pop();
        PMultiplication pmultiplicationNode1;
        {
        PMultiplication pmultiplicationNode2;
        TMult tmultNode3;
        PPower ppowerNode4;
        pmultiplicationNode2 = (PMultiplication)nodeArrayList1.get(0);
        tmultNode3 = (TMult)nodeArrayList2.get(0);
        ppowerNode4 = (PPower)nodeArrayList3.get(0);

        pmultiplicationNode1 = new AMultiplicationMultiplication(pmultiplicationNode2, tmultNode3, ppowerNode4);
        }
	nodeList.add(pmultiplicationNode1);
        return nodeList;
    }



    ArrayList new19()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList1 = (ArrayList) pop();
        PPower ppowerNode1;
        {
        PSomething psomethingNode2;
        psomethingNode2 = (PSomething)nodeArrayList1.get(0);

        ppowerNode1 = new ASomethingPower(psomethingNode2);
        }
	nodeList.add(ppowerNode1);
        return nodeList;
    }



    ArrayList new20()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList3 = (ArrayList) pop();
        ArrayList nodeArrayList2 = (ArrayList) pop();
        ArrayList nodeArrayList1 = (ArrayList) pop();
        PPower ppowerNode1;
        {
        PPower ppowerNode2;
        TPow tpowNode3;
        PSomething psomethingNode4;
        ppowerNode2 = (PPower)nodeArrayList1.get(0);
        tpowNode3 = (TPow)nodeArrayList2.get(0);
        psomethingNode4 = (PSomething)nodeArrayList3.get(0);

        ppowerNode1 = new APowerPower(ppowerNode2, tpowNode3, psomethingNode4);
        }
	nodeList.add(ppowerNode1);
        return nodeList;
    }



    ArrayList new21()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList1 = (ArrayList) pop();
        PSomething psomethingNode1;
        {
        TId tidNode2;
        tidNode2 = (TId)nodeArrayList1.get(0);

        psomethingNode1 = new AIdentifierSomething(tidNode2);
        }
	nodeList.add(psomethingNode1);
        return nodeList;
    }



    ArrayList new22()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList1 = (ArrayList) pop();
        PSomething psomethingNode1;
        {
        TNumber tnumberNode2;
        tnumberNode2 = (TNumber)nodeArrayList1.get(0);

        psomethingNode1 = new ANumbSomething(tnumberNode2);
        }
	nodeList.add(psomethingNode1);
        return nodeList;
    }



    ArrayList new23()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList3 = (ArrayList) pop();
        ArrayList nodeArrayList2 = (ArrayList) pop();
        ArrayList nodeArrayList1 = (ArrayList) pop();
        PSomething psomethingNode1;
        {
        TLPar tlparNode2;
        PExpression pexpressionNode3;
        TRPar trparNode4;
        tlparNode2 = (TLPar)nodeArrayList1.get(0);
        pexpressionNode3 = (PExpression)nodeArrayList2.get(0);
        trparNode4 = (TRPar)nodeArrayList3.get(0);

        psomethingNode1 = new AParSomething(tlparNode2, pexpressionNode3, trparNode4);
        }
	nodeList.add(psomethingNode1);
        return nodeList;
    }



    ArrayList new24()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList1 = (ArrayList) pop();
        TypedLinkedList listNode2 = new TypedLinkedList();
        {
        PCommands pcommandsNode1;
        pcommandsNode1 = (PCommands)nodeArrayList1.get(0);
	if(pcommandsNode1 != null)
	{
	  listNode2.add(pcommandsNode1);
	}
        }
	nodeList.add(listNode2);
        return nodeList;
    }



    ArrayList new25()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList2 = (ArrayList) pop();
        ArrayList nodeArrayList1 = (ArrayList) pop();
        TypedLinkedList listNode3 = new TypedLinkedList();
        {
        TypedLinkedList listNode1 = new TypedLinkedList();
        PCommands pcommandsNode2;
        listNode1 = (TypedLinkedList)nodeArrayList1.get(0);
        pcommandsNode2 = (PCommands)nodeArrayList2.get(0);
	if(listNode1 != null)
	{
	  listNode3.addAll(listNode1);
	}
	if(pcommandsNode2 != null)
	{
	  listNode3.add(pcommandsNode2);
	}
        }
	nodeList.add(listNode3);
        return nodeList;
    }



    ArrayList new26()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList1 = (ArrayList) pop();
        TypedLinkedList listNode2 = new TypedLinkedList();
        {
        TTab ttabNode1;
        ttabNode1 = (TTab)nodeArrayList1.get(0);
	if(ttabNode1 != null)
	{
	  listNode2.add(ttabNode1);
	}
        }
	nodeList.add(listNode2);
        return nodeList;
    }



    ArrayList new27()
    {
        ArrayList nodeList = new ArrayList();

        ArrayList nodeArrayList2 = (ArrayList) pop();
        ArrayList nodeArrayList1 = (ArrayList) pop();
        TypedLinkedList listNode3 = new TypedLinkedList();
        {
        TypedLinkedList listNode1 = new TypedLinkedList();
        TTab ttabNode2;
        listNode1 = (TypedLinkedList)nodeArrayList1.get(0);
        ttabNode2 = (TTab)nodeArrayList2.get(0);
	if(listNode1 != null)
	{
	  listNode3.addAll(listNode1);
	}
	if(ttabNode2 != null)
	{
	  listNode3.add(ttabNode2);
	}
        }
	nodeList.add(listNode3);
        return nodeList;
    }



    private static int[][][] actionTable;
/*      {
			{{-1, REDUCE, 0}, {0, SHIFT, 1}, {21, SHIFT, 2}, {25, SHIFT, 3}, {31, SHIFT, 4}, {46, SHIFT, 5}, {48, SHIFT, 6}, },
			{{-1, REDUCE, 26}, },
			{{-1, ERROR, 2}, {21, SHIFT, 2}, {46, SHIFT, 5}, {48, SHIFT, 16}, },
			{{-1, ERROR, 3}, {21, SHIFT, 2}, {39, SHIFT, 18}, {41, SHIFT, 19}, {46, SHIFT, 5}, {48, SHIFT, 16}, },
			{{-1, ERROR, 4}, {21, SHIFT, 2}, {46, SHIFT, 5}, {48, SHIFT, 16}, },
			{{-1, REDUCE, 22}, },
			{{-1, REDUCE, 21}, {14, SHIFT, 23}, },
			{{-1, ERROR, 7}, {52, ACCEPT, -1}, },
			{{-1, REDUCE, 24}, },
			{{-1, REDUCE, 2}, },
			{{-1, REDUCE, 3}, {8, SHIFT, 24}, {9, SHIFT, 25}, },
			{{-1, REDUCE, 14}, {11, SHIFT, 26}, },
			{{-1, REDUCE, 17}, {10, SHIFT, 27}, },
			{{-1, REDUCE, 19}, },
			{{-1, REDUCE, 1}, {0, SHIFT, 1}, {21, SHIFT, 2}, {25, SHIFT, 3}, {31, SHIFT, 4}, {46, SHIFT, 5}, {48, SHIFT, 6}, },
			{{-1, ERROR, 15}, {0, SHIFT, 29}, {25, SHIFT, 30}, {31, SHIFT, 31}, {48, SHIFT, 32}, },
			{{-1, REDUCE, 21}, },
			{{-1, ERROR, 17}, {8, SHIFT, 24}, {9, SHIFT, 25}, {22, SHIFT, 33}, },
			{{-1, REDUCE, 10}, },
			{{-1, REDUCE, 11}, },
			{{-1, ERROR, 20}, {40, SHIFT, 34}, },
			{{-1, ERROR, 21}, {8, SHIFT, 24}, {9, SHIFT, 25}, {37, SHIFT, 35}, {38, SHIFT, 36}, },
			{{-1, REDUCE, 8}, {8, SHIFT, 24}, {9, SHIFT, 25}, },
			{{-1, ERROR, 23}, {21, SHIFT, 2}, {46, SHIFT, 5}, {48, SHIFT, 16}, },
			{{-1, ERROR, 24}, {21, SHIFT, 2}, {46, SHIFT, 5}, {48, SHIFT, 16}, },
			{{-1, ERROR, 25}, {21, SHIFT, 2}, {46, SHIFT, 5}, {48, SHIFT, 16}, },
			{{-1, ERROR, 26}, {21, SHIFT, 2}, {46, SHIFT, 5}, {48, SHIFT, 16}, },
			{{-1, ERROR, 27}, {21, SHIFT, 2}, {46, SHIFT, 5}, {48, SHIFT, 16}, },
			{{-1, REDUCE, 25}, },
			{{-1, REDUCE, 27}, },
			{{-1, ERROR, 30}, {21, SHIFT, 2}, {39, SHIFT, 18}, {41, SHIFT, 19}, {46, SHIFT, 5}, {48, SHIFT, 16}, },
			{{-1, ERROR, 31}, {21, SHIFT, 2}, {46, SHIFT, 5}, {48, SHIFT, 16}, },
			{{-1, ERROR, 32}, {14, SHIFT, 44}, },
			{{-1, REDUCE, 23}, },
			{{-1, ERROR, 34}, {0, SHIFT, 1}, {25, SHIFT, 3}, {31, SHIFT, 4}, {48, SHIFT, 45}, },
			{{-1, ERROR, 35}, {21, SHIFT, 2}, {46, SHIFT, 5}, {48, SHIFT, 16}, },
			{{-1, ERROR, 36}, {21, SHIFT, 2}, {46, SHIFT, 5}, {48, SHIFT, 16}, },
			{{-1, REDUCE, 6}, {8, SHIFT, 24}, {9, SHIFT, 25}, },
			{{-1, REDUCE, 15}, {11, SHIFT, 26}, },
			{{-1, REDUCE, 16}, {11, SHIFT, 26}, },
			{{-1, REDUCE, 18}, {10, SHIFT, 27}, },
			{{-1, REDUCE, 20}, },
			{{-1, ERROR, 42}, {40, SHIFT, 49}, },
			{{-1, REDUCE, 9}, {8, SHIFT, 24}, {9, SHIFT, 25}, },
			{{-1, ERROR, 44}, {21, SHIFT, 2}, {46, SHIFT, 5}, {48, SHIFT, 16}, },
			{{-1, ERROR, 45}, {14, SHIFT, 23}, },
			{{-1, REDUCE, 4}, },
			{{-1, REDUCE, 12}, {8, SHIFT, 24}, {9, SHIFT, 25}, },
			{{-1, REDUCE, 13}, {8, SHIFT, 24}, {9, SHIFT, 25}, },
			{{-1, ERROR, 49}, {0, SHIFT, 1}, {25, SHIFT, 3}, {31, SHIFT, 4}, {48, SHIFT, 45}, },
			{{-1, REDUCE, 7}, {8, SHIFT, 24}, {9, SHIFT, 25}, },
			{{-1, REDUCE, 5}, },
        };*/
    private static int[][][] gotoTable;
/*      {
			{{-1, 7}, },
			{{-1, 8}, {14, 28}, },
			{{-1, 9}, {34, 46}, {49, 51}, },
			{{-1, 20}, {30, 42}, },
			{{-1, 10}, {2, 17}, {3, 21}, {4, 22}, {23, 37}, {30, 21}, {31, 43}, {35, 47}, {36, 48}, {44, 50}, },
			{{-1, 11}, {24, 38}, {25, 39}, },
			{{-1, 12}, {26, 40}, },
			{{-1, 13}, {27, 41}, },
			{{-1, 14}, },
			{{-1, 15}, },
        };*/
    private static String[] errorMessages;
/*      {
			"expecting: tab, '(', 'if', 'print', number, id, EOF",
			"expecting: tab, 'if', 'print', id",
			"expecting: '(', number, id",
			"expecting: '(', 'true', 'false', number, id",
			"expecting: tab, '+', '-', '**', '*', '(', ')', 'if', 'print', '<', '>', ':', number, id, EOF",
			"expecting: tab, '+', '-', '**', '*', '=', '(', 'if', 'print', number, id, EOF",
			"expecting: EOF",
			"expecting: tab, '+', '-', '(', 'if', 'print', number, id, EOF",
			"expecting: tab, '+', '-', '*', '(', ')', 'if', 'print', '<', '>', ':', number, id, EOF",
			"expecting: '+', '-', ')'",
			"expecting: ':'",
			"expecting: '+', '-', '<', '>'",
			"expecting: '='",
			"expecting: '+', '-', ':'",
        };*/
    private static int[] errors;
/*      {
			0, 1, 2, 3, 2, 4, 5, 6, 0, 0, 7, 8, 4, 4, 0, 1, 4, 9, 10, 10, 10, 11, 7, 2, 2, 2, 2, 2, 0, 1, 3, 2, 12, 4, 1, 2, 2, 7, 8, 8, 4, 4, 10, 7, 2, 12, 0, 13, 13, 1, 7, 0, 
        };*/
}
