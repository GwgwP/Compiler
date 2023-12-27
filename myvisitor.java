import minipython.analysis.*;
import minipython.node.*;
import java.util.*;
import java.util.function.Function;

public class myvisitor extends DepthFirstAdapter 
{
	
 	private Hashtable<String, Node> variables;
	
	private Hashtable<String, LinkedList<Node>> functions = new Hashtable<>();

	private Hashtable<Node, VARIABLE_TYPES> variableTypes;
	private ArrayList<Function> func_list = new ArrayList();
	

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
		UNDEFINED_ARRAY,
		REDIFINED_VARIABLE
		// UNORDERED_PARAMS,
		// ADD_TYPE_MISSMATCH,
		// MINUS_TYPE_MISSMATCH,
		// IDENTICAL_FUNCTIONS,
	}
	/**
	* 
	*/
	public static enum VARIABLE_TYPES {
		NUMBER,
		STRING,
		NONE,
		UNKNOWN,
	}
	
	
	public myvisitor(Hashtable<String, Node> variables, Hashtable<String, LinkedList<Node>> functions, 
			Hashtable<Node, VARIABLE_TYPES> variableTypes) {
		this.variables = variables;
		this.functions = functions;
		this.variableTypes = variableTypes;
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
			AIdId id2 = (AIdId) forLoop.getRid(); // for x in x is ilegal because x is global var
			if (name.equals(id2.getIdent().getText()) ) { 
				printError(node, ERRORS.REDIFINED_VARIABLE);
			}
			else if(!variables.containsKey(id2.getIdent().getText())){
				printError(node, ERRORS.UNDECLARED_VARIABLE);
			}
			
			String variableType = variableTypes.get(id2).name();
			if(variables.containsKey(name)) //TODO: CHECK
			{
				if (variableType.equals("NUMBER")) 
				{
					if(!variableTypes.get(name).name().equals("NUMBER"))
					{
						printError(node, ERRORS.TYPE_MISSMATCH);
					}
				}
				else if (variableType.equals("STRING")) 
				{
					if(!variableTypes.get(name).name().equals("STRING"))
					{
						printError(node, ERRORS.TYPE_MISSMATCH);
					}
				} else if (variableType.equals("NONE")) 
				{
					if(!variableTypes.get(name).name().equals("NONE"))
					{
						printError(node, ERRORS.TYPE_MISSMATCH);
					}
				} 
				else if (variableType.equals("UNKNOWN")) 
				{
					if(!variableTypes.get(name).name().equals("UNKNOWN"))
					{
						printError(node, ERRORS.TYPE_MISSMATCH);
					}
				}
			}
			else
			{
				if (variableType.equals("NUMBER")) 
				{
					variableTypes.put(node, VARIABLE_TYPES.NUMBER);
				}
				else if (variableType.equals("STRING")) 
				{
					variableTypes.put(node, VARIABLE_TYPES.STRING);
				} else if (variableType.equals("NONE")) 
				{
					variableTypes.put(node, VARIABLE_TYPES.NONE);
				} 
				else if (variableType.equals("UNKNOWN")) 
				{
					variableTypes.put(node, VARIABLE_TYPES.UNKNOWN);
				}
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
		else if (parent instanceof AForStatementStatement)  //name == id1
		{
			AForStatementStatement forLoop = (AForStatementStatement) parent;
			//Create a variable for the first identifier
			AIdId id1 = (AIdId) forLoop.getLid();
			AIdId id2 = (AIdId) forLoop.getRid();
			// for x in y: and y=[1,2,3] x should be integer, if y =[1.2, 1.3] y should be double etc
			
			String variableType = variableTypes.get(id2).name();
			variables.put(id1.getIdent().getText(), node);
		}	
	}

	private void printError(Node node, myvisitor.ERRORS error) {
		switch (error) {
            case UNDECLARED_VARIABLE:
                System.out.println("Error at Node " + node + ": Undeclared variable");
                break;
            case UNDEFINED_FUNCTION:
                System.out.println("Error at Node " + node + ": Undefined function");
                break;
            case WRONG_FUNCTION_PARAMETERS:
                System.out.println("Error at Node " + node + ": Wrong function parameters");
                break;
            case TYPE_MISSMATCH:
                System.out.println("Error at Node " + node + ": Type mismatch");
                break;
            case NONE:
                System.out.println("No error at Node " + node);
                break;
            case MISUSED_FUNCTION:
                System.out.println("Error at Node " + node + ": Misused function");
                break;
            case REDEFINED_FUNCTION:
                System.out.println("Error at Node " + node + ": Redefined function");
                break;
            case UNDEFINED_ARRAY:
                System.out.println("Error at Node " + node + ": Undefined array");
                break;
            // Add cases for other error types as needed

            default:
                System.out.println("Unknown error at Node " + node);
        }
		System.exit(-1);

	}
	
	@Override
	public void inAFuncCallFunctionCall(AFuncCallFunctionCall node) {
		int x = 0;
		String id = node.getId().toString();
		if(!functions.containsKey(id))
		{
			printError(node, ERRORS.UNDEFINED_FUNCTION);
		}
		else
		{	
			//counting how many arguments are given and compare it with the ones saved.
			
			AArglistArglist args =  (AArglistArglist)node.getArglist().get(0);
			
			if(node.getArglist().size()!= 0)
			{
				x++;
				LinkedList more = args.getR();

				for(int i =0 ; i< more.size();i++)
				{
					x++;
				}

			}
			else
			{
				x = 0;
			}

			int counter = 0;
			for(int i =0; i< func_list.size();i++)
			{
				if(func_list.get[i].getName().equals(id))
				{
					
					if(!(func_list.get[i].getTotal_vars() - func_list.get[i].getDef_vars() <= x && x <= func_list.get[i].getTotal_vars()))
					{
						counter++;
						
					}
					else
					{
						counter--;
					}
				}
			}

		}
		if(counter!=0)
		{
			printError(node, ERRORS.WRONG_FUNCTION_PARAMETERS);
		}
		
		
	}
	/**
	 * given example modified to suit our grammar
	 */
	@Override
	public void inADefFuncFunction(ADefFuncFunction node)
	{ 

		String fName = node.getId().toString();

		if (functions.containsKey(fName)) //if function exists check that number of parameters is different
		{
			LinkedList<Node> nodeList = functions.get(fName);
			int count_total_vars_new = 0;
			int count_default_vars_new = 0;

			LinkedList<Integer> cont = countVars(node);
			count_total_vars_new = cont.get(0); //0 returns the total variables
			count_default_vars_new = cont.get(1); //1 returns default variables.
			
			for (Node n:nodeList)
			{				

				ADefFuncFunction func_more = (ADefFuncFunction) n;
			
				// calculation of same name function's arguments and assignements
				int count_total_vars = 0;
				int count_default_vars = 0;
				LinkedList<Integer> cont1 = countVars(func_more);
				count_total_vars = cont1.get(0);
				count_default_vars = cont1.get(1); 
				System.out.println("total vars:"+count_total_vars);
				System.out.println("def vars:"+count_default_vars);
				System.out.println("total vars new :"+count_total_vars_new);
				System.out.println("def vars new:"+count_default_vars_new);
					
					
				if(count_total_vars == count_total_vars_new ||count_total_vars - count_default_vars == count_total_vars_new - count_default_vars_new||count_total_vars-count_default_vars==count_total_vars_new||count_total_vars_new-count_default_vars_new==count_total_vars|| (count_total_vars_new>count_total_vars && count_total_vars_new-count_default_vars_new <= count_total_vars||count_total_vars>count_total_vars_new && count_total_vars-count_default_vars <= count_total_vars_new ) )
				{
					
					// System.out.println("total vars:"+count_total_vars);
					// System.out.println("def vars:"+count_default_vars);
					// System.out.println("total vars new :"+count_total_vars_new);
					// System.out.println("def vars new:"+count_default_vars_new);

					printError(node, ERRORS.REDEFINED_FUNCTION);
				}
				
			}
		}		
		
	}

	private LinkedList<Integer> countVars(ADefFuncFunction node)
	{
		int count_total_vars = 0;
		int count_default_vars =0;
		LinkedList<Integer> vars = new LinkedList<>();
		
		if (node.getArgument().size() == 0)//there are no arguments f()
		{
			
		}
		else //(func_more.getArgument().get(0)!=null) //there are arguments f(x1,x2,...)
		{
		

			AArgArgument argument = ((AArgArgument) (node.getArgument().get(0)));
			
			count_total_vars++;
			if(argument.getAssignValue().size()!=0)
			{
				
				count_default_vars++;
			}					
			
			
			LinkedList ciav = argument.getCiav();
			
			
			if (ciav.size()!=0)
			{
				ACommaIdCiav l = (ACommaIdCiav) ciav.get(0);

			}
			
			
			for ( int k = 0; k < ciav.size();k++)
			{
				ACommaIdCiav c = (ACommaIdCiav) ciav.get(k);
				
				if (c.getAssignValue().size()== 0) 
				{
					count_total_vars++;

				}
				else
				{
					count_default_vars++;
					count_total_vars++;
				}
				
			
			}
			
		}
		vars.add(count_total_vars);
		vars.add(count_default_vars);
		return vars;
	}

	

	public void outADefFuncFunction(ADefFuncFunction node)
	{
		String name = node.getId().toString();
		// functions.put(fName, node);
		// Create a new function
		if (functions.containsKey(name)) {
			// If it exists, add the node to the existing list of nodes
			functions.get(name).add(node);

			Function f = new Function(name,countVars(node).get(1),countVars(node).get(0));
			func_list.add(f);

		} else {
			
			Function f = new Function(name,countVars(node).get(1),countVars(node).get(0));
			func_list.add(f);
			// If it doesn't exist, create a new list with the node and associate it with the function name
			LinkedList<Node> nodeList = new LinkedList<>();
			nodeList.add(node);
			functions.put(name, nodeList);
		}
	}
	@Override
	public void inAIdentifierExpression(AIdentifierExpression node)
    {
		//String name = node.getId().getText();
		//String name =node.getId().toString();
	}

	@Override
	public void outANumNum(ANumNum node) {
		variableTypes.put(node, VARIABLE_TYPES.NUMBER);
	}


}
