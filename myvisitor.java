import minipython.analysis.*;
import minipython.node.*;
import java.util.*;

public class myvisitor extends DepthFirstAdapter 
{
	Function current_function = null;
	
 	private Hashtable<String, Node> variables;
	
	private Hashtable<String, LinkedList<Node>> functions = new Hashtable<>();

	private Hashtable<String, VARIABLE_TYPES> variableTypes;
	private LinkedList<Function> func_list = new LinkedList<>();
	

	/**
	* All the recognisable potential error types listed
	*/
	public static enum ERRORS {
		UNDECLARED_VARIABLE, // #1st Error
		UNDEFINED_FUNCTION,  // #2nd error
		WRONG_FUNCTION_PARAMETERS,     // #3rd error 
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
			Hashtable<String, VARIABLE_TYPES> variableTypes) {
		this.variables = variables;
		this.functions = functions;
		this.variableTypes = variableTypes;
	}

	@Override
	public void outAIdId(AIdId node){
		int line = ((TIdent) node.getIdent()).getLine();
		String name = node.getIdent().getText().trim();
		Node parent = node.parent();
		// 1) Undeclared variables
		if (parent instanceof AIdentifierExpression || parent instanceof AIdValueno) { //x = y[2] if y is undefined
			if (!variableTypes.containsKey(name)) 
			{
				// Print error message
				printError(node, ERRORS.UNDECLARED_VARIABLE);
			}

		}
		else if (parent instanceof AForStatementStatement) // ex for x in y : statement -> x could be not defined but y should be defined
		{
			AForStatementStatement forLoop = (AForStatementStatement) parent;
			AIdId id1 = (AIdId) forLoop.getLid(); 
			AIdId id2 = (AIdId) forLoop.getRid(); // for x in x is ilegal because x is global var
			String id2s = id2.toString().trim();
			// Check that the second identifer is an existing variable
			if(node == forLoop.getLid() )
			{

				if (name.equals(id2s) ) 
				{ 
					printError(node, ERRORS.REDIFINED_VARIABLE);
				}
				else if(!variableTypes.containsKey(id2s)) //right id is not defined
				{
					printError(id2, ERRORS.UNDECLARED_VARIABLE);
				}

			}			
		}
			// Check the parent's type and react accordingly
		
		if (parent instanceof AForStatementStatement)  //name == id1
		{
			AForStatementStatement forLoop = (AForStatementStatement) parent;
			//Create a variable for the first identifier
			AIdId id1 = (AIdId) forLoop.getLid();
			AIdId id2 = (AIdId) forLoop.getRid();
			// for x in y: and y=[1,2,3] x should be integer, if y =[1.2, 1.3] y should be double etc
			VARIABLE_TYPES variableType = variableTypes.get(id2.getIdent().getText().trim());
			variableTypes.put(name, variableType);
			variables.put(id1.toString().trim(), node);
		}	
	
	} 
	
	public void inADoubleQuotesValueno(ADoubleQuotesValueno node)
	{
		
		Node grandpa = node.parent().parent();
		Node parent = node.parent();
		String id = null;

		if(grandpa instanceof AAssignStatementStatement)
		{
			id = ((AAssignStatementStatement)grandpa).getId().toString().trim();
			
		}
		else if(grandpa instanceof ACommaIdCiav)
		{

			id = ((ACommaIdCiav)grandpa).getId().toString().trim();

			AArgArgument g = (AArgArgument)grandpa.parent();

			ADefFuncFunction gp = (ADefFuncFunction) g.parent();
				
			String func_name = gp.getId().toString().trim();
			
			current_function.addVar_type("STRING");
			// for(int i =0; i< func_list.size();i++)
			// {
			// 	if(func_list.get(i).getName().equals(func_name))
			// 	{
			// 		func_list.get(i).addVar_type("STRING");
			// 	}
			// }
		}
		else if(grandpa instanceof AArgArgument)
		{
			id = ((AArgArgument)grandpa).getId().toString().trim();
			ADefFuncFunction gp = (ADefFuncFunction)grandpa.parent();

			String func_name = gp.getId().toString().trim();
			for(int i =0; i< func_list.size();i++)
			{
				if(func_list.get(i).getName().toString().trim().equals(func_name))
				{
					func_list.get(i).addVar_type("STRING");
				}
			}
		}
		if(id!=null)
		{
			
			if(variableTypes.containsKey(id))
			{
				variableTypes.remove(id);	
			}
			variableTypes.put(id, VARIABLE_TYPES.STRING);
		}	
		//Checking error 4
		if(grandpa instanceof AAdditionExExpression || grandpa instanceof ASubtractionExExpression || grandpa instanceof AMultiplicationExpression || grandpa instanceof APowerExpression || grandpa instanceof APlplExpression || grandpa instanceof AMinminExpression || grandpa instanceof ADivisionExpression || grandpa instanceof AModuloExpression  )	
		{
			printError(node, ERRORS.TYPE_MISSMATCH);
		}
	}
	@Override
    public void inAAssignStatementStatement(AAssignStatementStatement node) // in every assign statement we put the variable as unknown and if it has a value like string, number etc it will be replaced in the other outASingleQuotesValueno etc
    {
        String id = node.getId().toString().trim();
        if (variableTypes.containsKey(id))
        {	
            variableTypes.remove(id);
        }
        variableTypes.put(id, VARIABLE_TYPES.UNKNOWN);

    }
	@Override
	public void inASingleQuotesValueno(ASingleQuotesValueno node)
	{
		Node grandpa = node.parent().parent();
		Node parent = node.parent();
		String id = null;

		//System.out.println(parent.getClass());
		if(grandpa instanceof AAssignStatementStatement)
		{
			id = ((AAssignStatementStatement)grandpa).getId().toString().trim();
			
		}
		if(grandpa instanceof ACommaIdCiav)
		{
			id = ((ACommaIdCiav)grandpa).getId().toString().trim();


			AArgArgument g = (AArgArgument)grandpa.parent();
						System.out.println("200");

			ADefFuncFunction gp = (ADefFuncFunction) g.parent();
						System.out.println("202");

			String func_name = gp.getId().toString().trim();
			
			current_function.addVar_type("STRING");
			// for(int i =0; i< func_list.size();i++)
			// {
			// 	if(func_list.get(i).getName().equals(func_name))
			// 	{
			// 		func_list.get(i).addVar_type("STRING");
			// 	}
			// }
		}
		else if(grandpa instanceof AArgArgument)
		{
			id = ((AArgArgument)grandpa).getId().toString().trim();
			ADefFuncFunction gp = (ADefFuncFunction)grandpa.parent();

			String func_name = gp.getId().toString().trim();
			for(int i =0; i< func_list.size();i++)
			{
				if(func_list.get(i).getName().toString().trim().equals(func_name))
				{
					func_list.get(i).addVar_type("STRING");
				}
			}
		}
		if(id!=null)
		{
			if(variableTypes.containsKey(id))
			{
				variableTypes.remove(id);
				
			}
			variableTypes.put(id, VARIABLE_TYPES.STRING);
		}	
		
	}
	public void inANumNum(ANumNum node)
	{
		Node grandpa = node.parent().parent().parent();
		Node parent = node.parent().parent();
		String id = null;

		if(grandpa instanceof AAssignStatementStatement)
		{
			id = ((AAssignStatementStatement)grandpa).getId().toString().trim();
			
		}
		if(grandpa instanceof ACommaIdCiav)
		{
			id = ((ACommaIdCiav)grandpa).getId().toString().trim();
			AArgArgument g = (AArgArgument)grandpa.parent();
			ADefFuncFunction gp = (ADefFuncFunction) g.parent();
			String func_name = gp.getId().toString().trim();


			current_function.addVar_type("NUMBER");
			// for(int i =0; i< func_list.size();i++)
			// {
			// 	if(func_list.get(i).getName().equals(func_name))
			// 	{
			// 		func_list.get(i).addVar_type("NUMBER");
			// 	}
			// }

		}
		else if(grandpa instanceof AArgArgument)
		{
			id = ((AArgArgument)grandpa).getId().toString().trim();
			ADefFuncFunction gp = (ADefFuncFunction)grandpa.parent();
			String func_name = gp.getId().toString().trim();
			for(int i =0; i< func_list.size();i++)
			{
				if(func_list.get(i).getName().toString().trim().equals(func_name))
				{
					func_list.get(i).addVar_type("NUMBER");
				}
			}


		}
		
		if(id!=null)
		{
			if(variableTypes.containsKey(id)) //TODO: CHECK
			{
				variableTypes.remove(id);
				
			}
			variableTypes.put(id, VARIABLE_TYPES.NUMBER);
		}	

	}
	@Override
	public void inANoneValueno(ANoneValueno node)
	{
		Node grandpa = node.parent().parent();
		Node parent = node.parent();
		String id = null;

		if(grandpa instanceof AAssignStatementStatement)
		{
			id = ((AAssignStatementStatement)grandpa).getId().toString().trim();
			
		}
		if(grandpa instanceof ACommaIdCiav)
		{
			id = ((ACommaIdCiav)grandpa).getId().toString().trim();

			AArgArgument g = (AArgArgument)grandpa.parent();

			ADefFuncFunction gp = (ADefFuncFunction) g.parent();
			String func_name = gp.getId().toString().trim();
			
			current_function.addVar_type("NONE");
			// for(int i =0; i< func_list.size();i++)
			// {
			// 	if(func_list.get(i).getName().equals(func_name))
			// 	{
			// 		func_list.get(i).addVar_type("NONE");
			// 	}
			// }

		}
		else if(grandpa instanceof AArgArgument)
		{
			id = ((AArgArgument)grandpa).getId().toString().trim();
			ADefFuncFunction gp = (ADefFuncFunction)grandpa.parent();

			String func_name = gp.getId().toString().trim();
			for(int i =0; i< func_list.size();i++)
			{
				if(func_list.get(i).getName().toString().trim().equals(func_name))
				{
					func_list.get(i).addVar_type("NONE");
				}
			}
		}
		if(id!=null)
		{
			if(variableTypes.containsKey(id)) //TODO: CHECK
			{
				variableTypes.remove(id);
				
			}

			variableTypes.put(id, VARIABLE_TYPES.NONE);
			
		}	

	}
	

	// public void outAIdId(AIdId node)
	// {
		
		
	// }

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
			case REDIFINED_VARIABLE:
				System.out.println("Error at Node " + node + ": Redifined variable");
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
		String id = node.getId().toString().trim();
		if(!functions.containsKey(id))
		{
			printError(node, ERRORS.UNDEFINED_FUNCTION);
		}
		else
		{	
			
			//counting how many arguments are given and compare it with the ones saved.
			
			
			
			if(node.getArglist().size()!= 0)
			{
				AArglistArglist args =  (AArglistArglist)node.getArglist().get(0);
				x++;
				LinkedList<PExpression> more = (LinkedList<PExpression> )args.getR();

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
				if(func_list.get(i).getName().equals(id))
				{
					//System.out.println("i: "+i+" x:"+x);
					if(!(func_list.get(i).getTotal_vars() - func_list.get(i).getDef_vars() <= x && x <= func_list.get(i).getTotal_vars()))
					{
						//System.out.println("METABLITES "+"TOTAL: "+func_list.get(i).getTotal_vars()+"\nDEF: "+func_list.get(i).getDef_vars() +"\nX: "+ x);
						counter++;
					}
					else
					{
						counter--;
					}
				}
			}
			if(counter>0)
			{
				//System.out.println("counter"+counter);
				printError(node, ERRORS.WRONG_FUNCTION_PARAMETERS);
			}

		}
		
		
		
	}
	/**
	 * given example modified to suit our grammar
	 */
	@Override
	public void inADefFuncFunction(ADefFuncFunction node)
	{ 

		String fName = node.getId().toString().trim();


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
					
				if(count_total_vars == count_total_vars_new ||count_total_vars - count_default_vars == count_total_vars_new - count_default_vars_new||count_total_vars-count_default_vars==count_total_vars_new||count_total_vars_new-count_default_vars_new==count_total_vars|| (count_total_vars_new>count_total_vars && count_total_vars_new-count_default_vars_new <= count_total_vars||count_total_vars>count_total_vars_new && count_total_vars-count_default_vars <= count_total_vars_new ) )
				{
					printError(node, ERRORS.REDEFINED_FUNCTION);
				}
				
			}
		}	
		String name = node.getId().toString().trim();
		// functions.put(fName, node);
		// Create a new function
		if (functions.containsKey(name)) {
			// If it exists, add the node to the existing list of nodes
			functions.get(name).add(node);

			Function f = new Function(countVars(node).get(1), countVars(node).get(0),name);
			func_list.add(f);
			current_function = f;

		} else {
			
			Function f = new Function(countVars(node).get(1),countVars(node).get(0),name);
			func_list.add(f);

		
			current_function = f;
			
			
			// If it doesn't exist, create a new list with the node and associate it with the function name
			LinkedList<Node> nodeList = new LinkedList<>();
			nodeList.add(node);
			functions.put(name, nodeList);

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

	@Override
	public void inAArgArgument(AArgArgument node) {
		ADefFuncFunction parent = (ADefFuncFunction) node.parent();
		
		String func_name = parent.getId().toString().trim();
		
		String id = node.getId().toString().trim();
		if (node.getAssignValue().size()==0)
		{
			if(variableTypes.containsKey(id))
			{
				variableTypes.remove(id);
			}

			variableTypes.put(id, VARIABLE_TYPES.UNKNOWN);
			current_function.addVar_type("UNKNOWN");
			// for(int i =0; i< func_list.size();i++)
			// {
			// 	if(func_list.get(i).getName().equals(func_name) && current_function.equals() )
			// 	{
			// 		func_list.get(i).addVar_type("UNKNOWN");
			// 	}
			// }
		}

		
	}
	@Override
	public void inACommaIdCiav(ACommaIdCiav node) {
		AArgArgument parent = (AArgArgument) node.parent();
		ADefFuncFunction parentp = (ADefFuncFunction) parent.parent();

		String id = node.getId().toString().trim();
		
		String func_name = parentp.getId().toString().trim();

		if (node.getAssignValue().size()==0)
		{
			if(variableTypes.containsKey(id))
			{
				variableTypes.remove(id);
			}
			variableTypes.put(id, VARIABLE_TYPES.UNKNOWN);

			current_function.addVar_type("UNKNOWN");

			// for(int i =0; i< func_list.size();i++)
			// {
			// 	if(func_list.get(i).getName().equals(func_name))
			// 	{
			// 		func_list.get(i).addVar_type("UNKNOWN");
			// 	}
			// }
		}
	}
	@Override
	public void outAPrintStatementStatement(APrintStatementStatement node)
	{
		// System.out.println("-------------------------------KLHSH THS PRINT-------------------------");
		// for (Map.Entry<String, VARIABLE_TYPES> entry : variableTypes.entrySet()) {
        //     String key = entry.getKey();
        //     VARIABLE_TYPES value = entry.getValue();
        //     System.out.println("Key: " + key + ", Value: " + value);
        // }
		// System.out.println("------------------------TELOS KLHSH THS PRINT-------------------------");
		// System.out.println();
		// System.out.println("-------------------------------KLHSH THS VAR_TYPES-------------------------");
		// System.out.println(func_list.size());
		// LinkedList ll = func_list.get(0).gettVar_types();
		// System.out.println("-------------------------------TELOS KLHSH THS VAR_TYPES-------------------------");
	
		// System.out.println("-------------------------------KLHSH THS VAR_TYPES-------------------------");
		// System.out.println(func_list.size());
		// ll = func_list.get(1).gettVar_types();
		// System.out.println("-------------------------------TELOS KLHSH THS VAR_TYPES-------------------------");
		for (Function element : func_list) {
            System.out.println("func: "+element.getName());
			for(String type : element.gettVar_types()){
				System.out.println("type: "+type);
			}
        }
	}

	// public void inADefFuncFunction(ADefFuncFunction node)
	// {
		
	// }
	@Override
	public void inAIdentifierExpression(AIdentifierExpression node)
    {
		//String name = node.getId().getText();
		//String name =node.getId().toString();
	}

	// @Override
	// public void outANumNum(ANumNum node) {
	// 	variableTypes.put(node, VARIABLE_TYPES.NUMBER);
	// }
	
	// @Override
	// public void outANoneValueno(ANoneValueno node) {
	// 	variableTypes.put(node, VARIABLE_TYPES.NONE);
	// }

	// public void outALenExpExpression(ALenExpExpression node) {
	// 	variableTypes.put(node, VARIABLE_TYPES.NUMBER);
	// }

	// @Override
	// public void outAMaxExprExpression(AMaxExprExpression node) {
	// 	variableTypes.put(node, VARIABLE_TYPES.NUMBER);
	// }

	// @Override
	// public void outAMinExprExpression(AMinExprExpression node) {
	// 	variableTypes.put(node, VARIABLE_TYPES.NUMBER);
	// }
	// @Override
	// public void outAAdditionExExpression(AAdditionExExpression node) {
	// 	// Find the class type of left and right children
	// 	Class<?> lClass = node.getL().getClass();
	// 	Class<?> rClass = node.getR().getClass();
	// 	VARIABLE_TYPES lType = variableTypes.get(lClass.cast(node.getL()));
	// 	VARIABLE_TYPES rType = variableTypes.get(rClass.cast(node.getR()));

	// 	System.out.println(lType);
	// 	System.out.println(rType);
	// 	// AIdentifierArithmetics inserts Aidentifier node and not itself
	// 	// Hence, the AIdentifier's type should be retrieved
	// 	if (node.getL() instanceof AIdentifierExpression) {
	// 		lType = findVariableType(
	// 				((AIdId) ((AIdentifierExpression) node.getL()).getId()).getIdent().getText());
	// 	}

	// 	if (node.getR() instanceof AIdentifierExpression) {
	// 		rType = findVariableType(
	// 				((AIdId) ((AIdentifierExpression) node.getR()).getId()).getIdent().getText());
	// 	}

	// 	// All children must return a number for the expression to be valid
	// 	if (rType == VARIABLE_TYPES.NONE || lType == VARIABLE_TYPES.NONE) {
	// 		printError(node, ERRORS.NONE);
	// 	} else if (lType == VARIABLE_TYPES.UNKNOWN || rType == VARIABLE_TYPES.UNKNOWN) {
	// 		variableTypes.put(node, VARIABLE_TYPES.UNKNOWN);
	// 	} else if (lType == VARIABLE_TYPES.NUMBER && rType == VARIABLE_TYPES.NUMBER) {
	// 		variableTypes.put(node, VARIABLE_TYPES.NUMBER);
	// 	} else {
	// 		printError(node.getR(), ERRORS.TYPE_MISSMATCH);
	// 	}
	// }

	// private VARIABLE_TYPES findVariableType(String token) {
	// 	for (Node node : variableTypes.keySet()) {
	// 		if (node instanceof AIdId) {
	// 			if (token.trim().equals(((AIdId) node).getIdent().getText().trim())) {
	// 				return variableTypes.get(node);
	// 			}
	// 		}
	// 	}

	// 	return null;
	// }
}
