import minipython.analysis.*;
import minipython.node.*;
import java.util.*;
import java.util.function.Function;

public class myvisitor extends DepthFirstAdapter 
{
	
 	private Hashtable<String, Node> variables;
	private Hashtable<String, Node> functions;
	private Hashtable<Node, VARIABLE_TYPES> variableTypes;
	private List<Function> functionList;
	/**
	* All the recognisable potential error types listed
	*/
	public static enum ERRORS {
		DEFINED_FUNCTION,
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
	/**
	* 
	*/
	public static enum VARIABLE_TYPES {
		INTEGER,
		DOUBLE,
		STRING,
		NONE,
		UNKNOWN,
	}
	
	
	public myvisitor(Hashtable<String, Node> variables, Hashtable<String, Node> functions, 
			Hashtable<Node, VARIABLE_TYPES> variableTypes) {
		this.variables = variables;
		this.functions = functions;
		this.variableTypes = variableTypes;
		this.functionList = new ArrayList<>();
	}


	public void inAIdId(AIdId node){
		int line = ((TIdent) node.getIdent()).getLine();
		
		String name = node.getIdent().getText();
		Node parent = node.parent();

		// 1) Undeclared variables
		if (parent instanceof AIdentifierExpression) {
			if (!variables.containsKey(name)) {
				// Print error message
				printError(node, ERRORS.UNDECLARED_VARIABLE);
			}

		} 
		else if (parent instanceof AForStatementStatement) 
		{
			AForStatementStatement forLoop = (AForStatementStatement) parent;

			// Check that the second identifer is an existing variable
			AIdId id2 = (AIdId) forLoop.getRid();
			if (name.equals(id2.getIdent().getText()) && !variables.containsKey(name)) {
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
	
		if (functions.containsKey(fName))
		{
			Hashtable<String, Node> functions2 = new Hashtable();
			functions2 = (Hashtable<String, Node>) functions.clone();
			
			int stop = functions2.size();
			for (int i=0; i<= stop; i++)
			{
				ADefFuncFunction func_more = (ADefFuncFunction) functions2.get(fName);
				
				if(node.getArgument().size() != func_more.getArgument().size()) //e.g def f(x, y) and def f() is legal
				{
					
					functions.put(fName, node);
				}
				else
				{
					//((ACommaIdCiav)(((AArgArgument)node.getArgument().get(0)).getCiav())).getAssignValue();
					System.out.println("f"+((AArgArgument)node.getArgument().get(0)).getCiav());
					//check if we have same f(x), f(x=1) (default times, opote einai ilegal)
					System.out.println(node.getArgument().getClass().toString());
					// for (List item : node.getArgument())
					// if(node.getArgument().get(i))
				}
				
				
				printError(null, ERRORS.DEFINED_FUNCTION);
				//System.out.println("Line " + 1 + ": " +" Function " + fName +" is already defined");
				functions2.remove(i);
			}
		}
		else
		{
			functions.put(fName, node);
		}
	}
	@Override
	public void inAIdentifierExpression(AIdentifierExpression node)
    {
		//String name = node.getId().getText();
		//String name =node.getId().toString();
	}

}
