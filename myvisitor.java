import minipython.analysis.*;
import minipython.node.*;
import java.util.*;

public class myvisitor extends DepthFirstAdapter 
{
	private Hashtable symtable;	
	
	/**
	* All the recognisable potential error types listed
	*/
	public static enum ERRORS {
		UNDECLARED_VARIABLE, // #1st Error
		UNDEFINED_FUNCTION,
		UNORDERED_PARAMS,
		WRONG_PARAMS,
		TYPE_MISSMATCH,
		ADD_TYPE_MISSMATCH,
		MINUS_TYPE_MISSMATCH,
		NONE_OPERATION,
		IDENTICAL_FUNCTIONS,
	}
	
	myvisitor(Hashtable symtable) 
	{
		this.symtable = symtable;
	}

	public void inAIdId(AIdId node){
		int line = ((TIdent) node.getIdent()).getLine();
		
		String name = node.getIdent().getText();
		Node parent = node.parent();

		// 1) Undeclared variables
		if (parent instanceof AIdentifierExpression) {
			if (!symtable.containsKey(name)) {
				// Print error message
				printError(node, ERRORS.UNDECLARED_VARIABLE);
			}

		} 
		else if (parent instanceof AForStatementStatement) 
		{
			AForStatementStatement forLoop = (AForStatementStatement) parent;

			// Check that the second identifer is an existing variable
			AIdId id2 = (AIdId) forLoop.getRid();
			if (name.equals(id2.getIdent().getText()) && !symtable.containsKey(name)) {
				// Print error message
				printError(node, ERRORS.UNDECLARED_VARIABLE);
			}
		}
	}
	private void printError(AIdId node, myvisitor.ERRORS undeclaredVariable) {
	}

	

	/**
	 * given example modified to suit our grammar
	 */
	@Override
	public void inADefFuncFunction(ADefFuncFunction node)
	{
		String fName = node.getId().toString();
		System.out.println(fName);
		//int line = ((TIdent) node.getId().toString()).getLine();
		//int line = ((TIdent) node.getId()).getLine();
	
		if (symtable.containsKey(fName))
		{
			System.out.println("Line " + 1 + ": " +" Function " + fName +" is already defined");
		}
		else
		{
			symtable.put(fName, node);
		}
	}
	@Override
	public void inAIdentifierExpression(AIdentifierExpression node)
    {
		//String name = node.getId().getText();
		//String name =node.getId().toString();
	}

}
