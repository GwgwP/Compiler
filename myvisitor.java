import minipython.analysis.*;
import minipython.node.*;
import java.util.*;

public class myvisitor extends DepthFirstAdapter 
{
	private Hashtable symtable;	

	myvisitor(Hashtable symtable) 
	{
		this.symtable = symtable;
	}

	/**
	 * given example modified to suit our grammar
	 */
	public void inADefFuncFunction(ADefFuncFunction node)
	{
		String fName = node.getId().toString();
		int line = ((TId) node.getId()).getLine();
		if (symtable.containsKey(fName))
		{
			System.out.println("Line " + line + ": " +" Function " + fName +" is already defined");
		}
		else
		{
			symtable.put(fName, node);
		}
	}
	public void inAIdentifierExpression(AIdentifierExpression node)
	{
		
		String name = node.getId().getText();

	}

}
