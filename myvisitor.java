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
		UNDECLARED_VARIABLE, // #1st Error
		UNDEFINED_FUNCTION,  // #2nd error
		WRONG_FUNCTION_PARAMETERS,     //3rd error 
		TYPE_MISSMATCH,      // #4th error
		NONE,                // #5th error
		MISUSED_FUNCTION,    // #6th error
		REDEFINED_FUNCTION,  // #7th error
		UNDEFINED_ARRAY
		// UNORDERED_PARAMS,
		// ADD_TYPE_MISSMATCH,
		// MINUS_TYPE_MISSMATCH,
		// IDENTICAL_FUNCTIONS,
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
		if (parent instanceof AIdentifierExpression || parent instanceof AIdValueno) { //x = y[2] if y is undefined
			if (!variables.containsKey(name)) {
				// Print error message
				printError(node, ERRORS.UNDECLARED_VARIABLE);
			}

		}
		else if (parent instanceof AForStatementStatement) // ex for x in y : statement -> x could be not defined but y should be defined
		{
			AForStatementStatement forLoop = (AForStatementStatement) parent;

			// Check that the second identifer is an existing variable
			AIdId id2 = (AIdId) forLoop.getRid();
			if (name.equals(id2.getIdent().getText()) && !variables.containsKey(id2)) { 
				// Print error message
				printError(node, ERRORS.UNDECLARED_VARIABLE);
			}
			else{
				variables.put(id2.getIdent().getText(), id2);
			}
		}
		
	}
		
	public void outAIdId(AIdId node)
	{
		Node parent = node.parent();
		String name = node.getIdent().getText();
			// Check the parent's type and react accordingly
		if (parent instanceof AAssignStatementStatement|| parent instanceof AAsvalueAssignValue|| parent instanceof AArgArgument) {
			// Create a new variable
			variables.put(name, node);
			variableTypes.put(node, VARIABLE_TYPES.UNKNOWN);
		} 
		else if (parent instanceof ADefFuncFunction) 
		{
			// Create a new function
			functions.put(name, node);
		} 
		else if (parent instanceof AForStatementStatement)  //name == id1
		{
			AForStatementStatement forLoop = (AForStatementStatement) parent;
			//Create a variable for the first identifier
			AIdId id1 = (AIdId) forLoop.getLid();
			AIdId id2 = (AIdId) forLoop.getRid();
			// for x in y: and y=[1,2,3] x should be integer, if y =[1.2, 1.3] y should be double etc
			
			String variableType = variableTypes.get(id2).name();
			if (variableType.equals("INTEGER")) 
			{
				variableTypes.put(id1, VARIABLE_TYPES.INTEGER);
			}
			else if (variableType.equals("DOUBLE"))
			{
				variableTypes.put(id1, VARIABLE_TYPES.DOUBLE);
			} 
			else if (variableType.equals("STRING")) 
			{
				variableTypes.put(id1, VARIABLE_TYPES.STRING);
			} else if (variableType.equals("NONE")) 
			{
				variableTypes.put(id1, VARIABLE_TYPES.NONE);
			} 
			else if (variableType.equals("UNKNOWN")) 
			{
				variableTypes.put(id1, VARIABLE_TYPES.UNKNOWN);
			}
			variables.put(name, node); 
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
		
	
		if (functions.containsKey(fName))
		{
			Hashtable<String, Node> functions2 = new Hashtable();
			functions2 = (Hashtable<String, Node>) functions.clone();
			
			int stop = functions2.size();
			for (int i=0; i<= stop; i++)
			{
				if(functions2.get(fName)!= null) // if functions2.get(i) == fname
				{
					ADefFuncFunction func_more = (ADefFuncFunction) functions2.get(fName);
					
					if(node.getArgument().size() == func_more.getArgument().size() && node.getArgument().size() == 0) //e.g def f() and def f() is Elegal
					{
						
						printError(node, ERRORS.REDEFINED_FUNCTION);
					}
					else
					{
						// calculation of same name function's arguments and assignements
						int count_total_vars = 0;
						int count_default_vars = 0;
						AArgArgument argument = ((AArgArgument) (func_more.getArgument().get(0)));
						if (argument.getAssignValue()!=null) //TODO: CHECK IF IT WORKS
						{
							count_total_vars++;
							count_default_vars++;

						}
						else
						{
							count_total_vars++;
						}
						LinkedList ciav = argument.getCiav();
						if(ciav!=null)
						{
							for ( int k = 0; k < ciav.size();k++)
							{
								ACommaIdCiav c = (ACommaIdCiav) ciav.get(k);
								if (c.getAssignValue()!=null) //TODO: CHECK IF IT WORKS
								{
									count_total_vars++;
									count_default_vars++;

								}
								else
								{
									count_total_vars++;
								}
							}
						}
						//
						// calculation of initial name function's arguments and assignements
						int count_total_vars_new = 0;
						int count_default_vars_new = 0;
						AArgArgument argument_n = ((AArgArgument) (node.getArgument().get(0)));
						if (argument.getAssignValue()!=null) //TODO: CHECK IF IT WORKS
						{
							count_total_vars_new++;
							count_default_vars_new++;

						}
						else
						{
							count_total_vars++;
						}
						LinkedList ciav_n = argument_n.getCiav();
						if(ciav!=null)
						{
							for ( int k = 0; k < ciav_n.size();k++)
							{
								ACommaIdCiav c = (ACommaIdCiav) ciav_n.get(k);
								if (c.getAssignValue()!=null) //TODO: CHECK IF IT WORKS
								{
									count_total_vars_new++;
									count_default_vars_new++;

								}
								else
								{
									count_total_vars_new++;
								}
							}
						}
						
						if(count_total_vars - count_default_vars == count_total_vars_new - count_default_vars_new)
						{
							printError(node, nul);
						}
						

					}
					functions2.remove(i);
				}				
				
				printError(null, ERRORS.DEFINED_FUNCTION);
				//System.out.println("Line " + 1 + ": " +" Function " + fName +" is already defined");
				
			}
		}
		else
		{
			functions.put(fName, node);
		}
	}
	private void printError(ADefFuncFunction node, myvisitor.ERRORS redefinedFunction) {
	}


	public void outADefFuncFunction(ADefFuncFunction node)
	{

	}
	@Override
	public void inAIdentifierExpression(AIdentifierExpression node)
    {
		//String name = node.getId().getText();
		//String name =node.getId().toString();
	}

}
