import minipython.analysis.*;
import minipython.node.*;
import java.util.*;
public class MyVisitor extends DepthFirstAdapter 
{
	public static int total_errors=0;
	public static Function current_function = null;
	public static String curr_type_add = "null"; //type of the arguments that will be used in an addition together and so must be same type
    public static LinkedList<String> function_argument_list = new LinkedList<>();

	
 	public static Hashtable<String, Node> variables;
	
	public static Hashtable<String, LinkedList<Node>> functions = new Hashtable<>();

	public static Hashtable<String, VARIABLE_TYPES> variableTypes;

	//these variables are initialized again in the "in" function of addition and multiplication respectively
	String add_type = "null"; //what is the type of the current addition. we can only have str + str or number + number
	
	

	public static LinkedList<Function> func_list = new LinkedList<>();
	

	public LinkedList<Function> getFunc_list() {
		return func_list;
	}
	public Hashtable<String, LinkedList<Node>> getFunctions() {
		return functions;
	}

	/**
	* All the recognisable potential error types listed
	*/
	public static enum ERRORS {
		UNDECLARED_VARIABLE, // #1st Error
		UNDEFINED_FUNCTION,  // #2nd error
		WRONG_FUNCTION_PARAMETERS,     // #3rd error 
		TYPE_MISMATCH,      // #4th error
		NONE,                // #5th error
		MISUSED_FUNCTION,    // #6th error
		REDEFINED_FUNCTION,  // #7th error
		REDEFINED_VARIABLE,
		NO_ERROR //DEBUG
	}
	public static enum VARIABLE_TYPES {
		NUMBER,
		STRING,
		NONE,
		UNKNOWN,
	}
	
	public MyVisitor(Hashtable<String, Node> variables, Hashtable<String, LinkedList<Node>> functions, 
			Hashtable<String, VARIABLE_TYPES> variableTypes) {
		this.variables = variables;
		this.functions = functions;
		this.variableTypes = variableTypes;
	}

	@Override
	public void inAIdId(AIdId node){
		int line = ((TIdent) node.getIdent()).getLine();
		String name = node.getIdent().getText().trim();
		Node parent = node.parent();
		Node parent3 = node.parent().parent().parent();
		Boolean foundError = false;

		// 1) Undeclared variables
		if (parent instanceof AIdentifierExpression || parent instanceof AIdValueno) { //x = y[2] if y is undefined
			if (!variableTypes.containsKey(name)) 
			{
				// Print error message
				foundError = true;
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
					foundError = true;
					printError(node, ERRORS.REDEFINED_VARIABLE);
				}
				else if(!variableTypes.containsKey(id2s)) //right id is not defined
				{
					foundError = true;
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
			if (node == forLoop.getLid() && variableTypes.containsKey(id2.getIdent().getText().trim())) 
			{
				VARIABLE_TYPES variableType = variableTypes.get(id2.getIdent().getText().trim());
				variableTypes.put(name, variableType);
				variables.put(id1.toString().trim(), node);
			}
		}

		
		if(node.parent().parent().parent() instanceof AAdditionExExpression )
		{
			boolean thisIdIsFunction = false;
			for(Function f:func_list)
			{
				if(f.getName().equals(name))
				{
					thisIdIsFunction = true;
				}
			}
			if(!thisIdIsFunction)
			{
				calculateTypeOfAdditionId(node,variableTypes.get(name),name);
			}
		}

		//if return is the "4th parent" we have an id inside of an other expression in the return
		//the return type of the function will depend on the type of this expression.
		if(node.parent().parent().parent().parent() instanceof AReturnStatementStatement)
		{ 
			if (!variableTypes.containsKey(name)) 
			{
				// Print error message
				foundError = true;
				printError(node, ERRORS.UNDECLARED_VARIABLE);
			}
			else 
			{
				
				if( parent3 instanceof AMultiplicationExpression||parent3 instanceof ASubtractionExExpression || parent3 instanceof APlplExpression || parent3 instanceof AMinminExpression || parent3 instanceof ADivisionExpression || parent3 instanceof AModuloExpression || parent3 instanceof APowerExpression)
				{
					//we check if we are in the return statement of a function and if there is a  division or power
					//this method finds the index of the id in the linked list of this function so we can check the type of the variable
					//if it is unknown we will make it to be number because you cant have division or power with any other type of variable
					//this way we will be able to control if the variable is called correctly since it will no longer be unknown
					int index = findIndex(current_function.getVars(), name);
					if((variableTypes.get(name).toString()).equals("STRING") || (variableTypes.get(name).toString()).equals("NONE"))
					{
						foundError = true;
						printError(node, ERRORS.TYPE_MISMATCH);
					}
					if(current_function.gettVar_types().get(index).equals("UNKNOWN"))
					{
						current_function.gettVar_types().set(index,"NUMBER");
					} 
					current_function.setReturnType("NUMBER");
				}
			}
		}
		//if return is the "3th parent" we have an id alone in the return. 
		//the return type of the function will be the same as the type of this id.
		if(node.parent().parent().parent() instanceof AReturnStatementStatement)
		{ 
			if (!variableTypes.containsKey(name)) 
			{
				// Print error message
				foundError = true;
				printError(node, ERRORS.UNDECLARED_VARIABLE);
			}
			else
			{
				current_function.setReturnType(variableTypes.get(name).toString());
				if((variableTypes.get(name).toString()).equals("UNKNOWN"))
				{
					current_function.setVariableOfReturn(name);
				}
			}
		}

		if(node.parent().parent().parent() instanceof AArglistArglist){
			if(variableTypes.containsKey(name))
			{
				function_argument_list.add(variableTypes.get(name).toString());
				for (Function f: func_list){
					if(f.getName().equals(function_argument_list.get(0)))
					{
						if((function_argument_list.size()-f.gettVar_types().size())<2)
						{
							if(!(f.gettVar_types().get(function_argument_list.size()-2)).equals(function_argument_list.get(function_argument_list.size()-1))&&!((f.gettVar_types().get(function_argument_list.size()-2)).equals("UNKNOWN")))
							{
								foundError = true;
								printError(node, ERRORS.TYPE_MISMATCH);
							}
							//if the variable was declared as unknown inside the function, now a type is given to it by the arguments
							if((f.gettVar_types().get(function_argument_list.size()-2)).equals("UNKNOWN"))
							{
								f.gettVar_types().set((function_argument_list.size()-2),(variableTypes.get(name).toString()));
								variableTypes.put(f.getVars().get(function_argument_list.size()-2), variableTypes.get(name));
								if((f.getVars().get(function_argument_list.size()-2)).equals(f.getVariableOfReturn()))
								{
									f.setReturnType(variableTypes.get(name).toString());
								}
							}
							//if the variable was declared as unknown inside the function, now a type is given to it by the arguments
							if((f.gettVar_types().get(function_argument_list.size()-2)).equals("UNKNOWN"))
							{
								//f.setVarTypeByIndex((function_argument_list.size()-2),variableTypes.get(name).toString());
								f.gettVar_types().set((function_argument_list.size()-2), variableTypes.get(name).toString());
								if(variableTypes.get(name).toString().equals("STRING"))
								{
									variableTypes.put(f.getVars().get(function_argument_list.size()-2), VARIABLE_TYPES.STRING);
								}
								else if(variableTypes.get(name).toString().equals("NUMBER"))
								{
									variableTypes.put(f.getVars().get(function_argument_list.size()-2), VARIABLE_TYPES.NUMBER);
								}
								else if(variableTypes.get(name).toString().equals("NONE"))
								{
									variableTypes.put(f.getVars().get(function_argument_list.size()-2), VARIABLE_TYPES.NONE);
								}
							}
							for(int j:f.getVarOfSameType())
							{
								if(j==function_argument_list.size()-2)
								{
									if(curr_type_add.equals("null"))
									{
										if(!variableTypes.get(name).toString().equals("UNKNOWN"))
										{
											curr_type_add = variableTypes.get(name).toString();
										}
									}
									else
									{
										if(!curr_type_add.equals(variableTypes.get(name).toString()))
										{
											foundError = true;
											printError(node, ERRORS.TYPE_MISMATCH);
										}
										f.setReturnType(curr_type_add);
									}
								}
							}
						}
					}
				}
				AFuncCallFunctionCall fCall = (AFuncCallFunctionCall) node.parent().parent().parent().parent();
				if((function_argument_list.size()-1)==CountItemsInString(fCall.getArglist().toString().trim()))
				{
					funcAfterHandlingArguments(fCall);
				}
			}
			
		}
		// Addressing 5th error part2 (y = None -> print y+2)
		if(parent3 instanceof AMultiplicationExpression|| parent3 instanceof AAdditionExExpression || parent3 instanceof ASubtractionExExpression || parent3 instanceof APlplExpression || parent3 instanceof AMinminExpression || parent3 instanceof ADivisionExpression || parent3 instanceof AModuloExpression || parent3 instanceof AMultiplicationExpression || parent3 instanceof APowerExpression || parent3 instanceof ALenExpExpression)
		{
			if(variableTypes.containsKey(name))
			{
				String type = variableTypes.get(name).name().trim();
				if(type.equals("NONE"))
				{
					foundError = true;
					printError(node, ERRORS.NONE);
				}
				if(node.parent().parent().parent() instanceof AAdditionExExpression  && !(node.parent().parent().parent() instanceof AReturnStatementStatement))
				{
					AAdditionExExpression greatGrandpa = (AAdditionExExpression) node.parent().parent().parent();
					if(node.parent().parent() == greatGrandpa.getL())
					{
						add_type = variableTypes.get(name).name().trim();
					}
					else if(node.parent().parent() == greatGrandpa.getR())
					{
						if(!add_type.equals("UNKNOWN")&&!add_type.equals("null"))
						{
							if(!(variableTypes.get(name).name().trim()).equals(add_type))
							{
								foundError = true;
								printError(node, ERRORS.TYPE_MISMATCH);
							}
						}
					}
				}
			}

		}
		
		//if there wasn't an error and also we're not in an addition we call the functions that checks for assignment and handes it
		//if we were in an addition the function is already called from within the calculateTypeOfAddition function
		if(!foundError)
		{
			if(variableTypes.containsKey(name))
			{
				if(!(node.parent().parent() instanceof AAdditionExExpression) && !variableTypes.get(name).toString().equals("UNKNOWN") && !variableTypes.get(name).toString().equals("NONE"))
				{
					calculateTypeOfAssign(parent, variableTypes.get(name));
				}
			}
			
		}
		
	
	} 

	public void inADoubleQuotesValueno(ADoubleQuotesValueno node)
	{
		
		Node grandpa = node.parent().parent();
		Node parent = node.parent();
		String id = null;
		Boolean foundError = false;

		id = getString(grandpa, id);
			
		//Checking error 4
		if(grandpa instanceof AMultiplicationExpression||grandpa instanceof ASubtractionExExpression || grandpa instanceof APowerExpression || grandpa instanceof APlplExpression || grandpa instanceof AMinminExpression || grandpa instanceof ADivisionExpression || grandpa instanceof AModuloExpression  )	
		{
			foundError = true;
			printError(node, ERRORS.TYPE_MISMATCH);
		}
		if(grandpa instanceof AReturnStatementStatement){ 
			//if it is a return "something" save that the function returns type STRING
			current_function.setReturnType("STRING");
		}
		if(grandpa instanceof AArglistArglist){

			function_argument_list.add("STRING");
			for (Function f: func_list){
				if(f.getName().equals(function_argument_list.get(0)))
				{
					if((function_argument_list.size()-f.gettVar_types().size())<2)
					{
						if(!(f.gettVar_types().get(function_argument_list.size()-2)).equals(function_argument_list.get(function_argument_list.size()-1))&&!((f.gettVar_types().get(function_argument_list.size()-2)).equals("UNKNOWN")))
						{	
			                foundError = true;				
							printError(node, ERRORS.TYPE_MISMATCH);
						}
						//if the variable was declared as unknown inside the function, now a type is given to it by the arguments
						if((f.gettVar_types().get(function_argument_list.size()-2)).equals("UNKNOWN"))
						{
							f.gettVar_types().set((function_argument_list.size()-2),"STRING");
							variableTypes.put(f.getVars().get(function_argument_list.size()-2), VARIABLE_TYPES.STRING);
							if((f.getVars().get(function_argument_list.size()-2)).equals(f.getVariableOfReturn()))
							{
								f.setReturnType("STRING");
							}
						}
						for(int j:f.getVarOfSameType())
						{
							if(j==function_argument_list.size()-2)
							{
								if(curr_type_add.equals("null"))
								{
									curr_type_add = "STRING";
								}
								else
								{
									if(!curr_type_add.equals("STRING"))
									{
										foundError = true;
										printError(node, ERRORS.TYPE_MISMATCH);
									}
									f.setReturnType(curr_type_add);
								}
							}
						}
					}
				}
			}
			AFuncCallFunctionCall fCall = (AFuncCallFunctionCall) node.parent().parent().parent();
			if((function_argument_list.size()-1)==CountItemsInString(fCall.getArglist().toString().trim()))
			{
				funcAfterHandlingArguments(fCall);
			}
		}
		if(node.parent().parent() instanceof AAdditionExExpression )
		{
			calculateTypeOfAddition(node.parent(), VARIABLE_TYPES.STRING);
		}
		if(id!=null)
		{
			if(variableTypes.containsKey(id))
			{
				variableTypes.remove(id);
			}
			variableTypes.put(id, VARIABLE_TYPES.STRING);
		}
		//if there wasn't an error and also we're not in an addition we call the functions that checks for assignment and handes it
		//if we were in an addition the function is already called from within the calculateTypeOfAddition function
		if(!foundError)
		{
			if(!(node.parent().parent() instanceof AAdditionExExpression))
			{
				calculateTypeOfAssign(parent, VARIABLE_TYPES.STRING);
			}
		}
	}
	
	@Override
    public void inAAssignStatementStatement(AAssignStatementStatement node) // in every assign statement we put the variable as unknown and if it has a value like string, number etc it will be replaced in the other outASingleQuotesValueno etc
    {
		add_type = "null";
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
		Boolean foundError = false;

		String id = null;
		id = getString(grandpa, id);
		if(node.parent().parent() instanceof AMultiplicationExpression||grandpa instanceof ASubtractionExExpression || grandpa instanceof APowerExpression || grandpa instanceof APlplExpression || grandpa instanceof AMinminExpression || grandpa instanceof ADivisionExpression || grandpa instanceof AModuloExpression  )	
		{
			foundError = true;
			printError(node, ERRORS.TYPE_MISMATCH);
		}
		if(grandpa instanceof AReturnStatementStatement){
			//if it is a return 'something' save that the function returns type STRING
			current_function.setReturnType("STRING");
		}	
		if(grandpa instanceof AArglistArglist){
			function_argument_list.add("STRING");
			for (Function f: func_list){
				if(f.getName().equals(function_argument_list.get(0)))
				{
					if((function_argument_list.size()-f.gettVar_types().size())<2)
					{
						if(!(f.gettVar_types().get(function_argument_list.size()-2)).equals(function_argument_list.get(function_argument_list.size()-1))&&!((f.gettVar_types().get(function_argument_list.size()-2)).equals("UNKNOWN")))
						{
							foundError = true;
							printError(node, ERRORS.TYPE_MISMATCH);
						}
						//if the variable was declared as unknown inside the function, now a type is given to it by the arguments
						if((f.gettVar_types().get(function_argument_list.size()-2)).equals("UNKNOWN"))
						{
							f.gettVar_types().set((function_argument_list.size()-2),"STRING");
							variableTypes.put(f.getVars().get(function_argument_list.size()-2), VARIABLE_TYPES.STRING);
							if((f.getVars().get(function_argument_list.size()-2)).equals(f.getVariableOfReturn()))
							{
								f.setReturnType("STRING");
							}
						}
						for(int j:f.getVarOfSameType())
						{
							if(j==function_argument_list.size()-2)
							{
								if(curr_type_add.equals("null"))
								{
									curr_type_add = "STRING";
								}
								else
								{
									if(!curr_type_add.equals("STRING"))
									{
										foundError = true;
										printError(node, ERRORS.TYPE_MISMATCH);
									}
									f.setReturnType(curr_type_add);
								}
							}
						}
					}
				}
			}
			AFuncCallFunctionCall fCall = (AFuncCallFunctionCall) node.parent().parent().parent();
			if((function_argument_list.size()-1)==CountItemsInString(fCall.getArglist().toString().trim()))
			{
				funcAfterHandlingArguments(fCall);
			}
		}
		if(node.parent().parent() instanceof AAdditionExExpression )
		{
			calculateTypeOfAddition(node.parent(), VARIABLE_TYPES.STRING);
		}
		if(id!=null)
		{
			if(variableTypes.containsKey(id)) 
			{
				variableTypes.remove(id);
				
			}
			variableTypes.put(id, VARIABLE_TYPES.NUMBER);
		}
		//if there wasn't an error and also we're not in an addition we call the functions that checks for assignment and handes it
		//if we were in an addition the function is already called from within the calculateTypeOfAddition function
		if(!foundError)
		{
			if(!(node.parent().parent() instanceof AAdditionExExpression))
			{
				calculateTypeOfAssign(parent, VARIABLE_TYPES.STRING);
			}
		}
	}

	

	@Override
	public void inANumNum(ANumNum node)
	{
		Node grandpa = node.parent().parent().parent();
		Node parent = node.parent().parent();
		Boolean foundError = false;
		
		String id = null;

		// if(grandpa.parent() instanceof AAssignStatementStatement && !(node.parent().parent().parent() instanceof AAdditionExExpression))
		// {
		// 	//id = ((AAssignStatementStatement)grandpa).getId().toString().trim();
		// 	calculateTypeOfAssign(parent, VARIABLE_TYPES.NUMBER);
			
		// }
		if(grandpa instanceof ACommaIdCiav)
		{
			id = ((ACommaIdCiav)grandpa).getId().toString().trim();


			current_function.addVar_type("NUMBER");
			current_function.addVar(id);
		

		}
		else if(grandpa instanceof AArgArgument)
		{
			id = ((AArgArgument)grandpa).getId().toString().trim();
			ADefFuncFunction gp = (ADefFuncFunction)grandpa.parent();
			String func_name = gp.getId().toString().trim();
            for (Function function : func_list) {
                if (function.getName().toString().trim().equals(func_name)) {
                    function.addVar_type("NUMBER");
                    function.addVar(id);
                }
            }
		}
		
			
		if(grandpa instanceof AReturnStatementStatement){
			//if it is a return number save that the function returns type STRING
			current_function.setReturnType("NUMBER");
		}
		
		if(grandpa instanceof AArglistArglist)
		{
			function_argument_list.add("NUMBER");
			for (Function f: func_list){
				if(f.getName().equals(function_argument_list.get(0)))
				{
					if((function_argument_list.size()-f.gettVar_types().size())<2)
					{
						if(!(f.gettVar_types().get(function_argument_list.size()-2)).equals(function_argument_list.get(function_argument_list.size()-1))&&!((f.gettVar_types().get(function_argument_list.size()-2)).equals("UNKNOWN")))
						{
							foundError = true;
							printError(node, ERRORS.TYPE_MISMATCH);
						}
						//if the variable was declared as unknown inside the function, now a type is given to it by the arguments
						if((f.gettVar_types().get(function_argument_list.size()-2)).equals("UNKNOWN"))
						{
							f.gettVar_types().set((function_argument_list.size()-2),"NUMBER");
							variableTypes.put(f.getVars().get(function_argument_list.size()-2), VARIABLE_TYPES.NUMBER);
							if((f.getVars().get(function_argument_list.size()-2)).equals(f.getVariableOfReturn()))
							{
								f.setReturnType("NUMBER");
							}
						}
						for(int j:f.getVarOfSameType())
						{
							if(j==function_argument_list.size()-2)
							{
								if(curr_type_add.equals("null"))
								{
									curr_type_add = "NUMBER";
								}
								else
								{
									if(!curr_type_add.equals("NUMBER"))
									{
										foundError = true;
										printError(node, ERRORS.TYPE_MISMATCH);
									}
									f.setReturnType(curr_type_add);
								}
							}
						}

					}
				}
			}
			AFuncCallFunctionCall fCall = (AFuncCallFunctionCall) node.parent().parent().parent().parent();
			if((function_argument_list.size()-1)==CountItemsInString(fCall.getArglist().toString().trim()))
			{
				funcAfterHandlingArguments(fCall);
			}
		}
		if(node.parent().parent().parent() instanceof AAdditionExExpression)
		{
			calculateTypeOfAddition(parent, VARIABLE_TYPES.NUMBER);
		}
		if(id!=null)
		{
			if(variableTypes.containsKey(id)) 
			{
				variableTypes.remove(id);
				
			}
			variableTypes.put(id, VARIABLE_TYPES.NUMBER);
		}
		//if there wasn't an error and also we're not in an addition we call the functions that checks for assignment and handes it
		//if we were in an addition the function is already called from within the calculateTypeOfAddition function
		if(!foundError)
		{
			if(!(node.parent().parent().parent() instanceof AAdditionExExpression))
			{
				calculateTypeOfAssign(parent, VARIABLE_TYPES.NUMBER);
			}
		}
	}
	@Override
	public void inANoneValueno(ANoneValueno node)
	{
		Node grandpa = node.parent().parent();
		String id = null;

		if(grandpa instanceof AAssignStatementStatement)
		{
			id = ((AAssignStatementStatement)grandpa).getId().toString().trim();
			
		}
		if(grandpa instanceof ACommaIdCiav)
		{
			id = ((ACommaIdCiav)grandpa).getId().toString().trim();
			
			current_function.addVar_type("NONE");
			current_function.addVar(id);
		

		}
		else if(grandpa instanceof AArgArgument)
		{
			id = ((AArgArgument)grandpa).getId().toString().trim();
			ADefFuncFunction gp = (ADefFuncFunction)grandpa.parent();

			String func_name = gp.getId().toString().trim();
            for (Function function : func_list) {
                if (function.getName().toString().trim().equals(func_name)) {
                    function.addVar_type("NONE");
                    function.addVar(id);
                }
            }
		}
		if(id!=null)
		{
			if(variableTypes.containsKey(id)) 
			{
				variableTypes.remove(id);
				
			}

			variableTypes.put(id, VARIABLE_TYPES.NONE);
			
		}	
		if(grandpa instanceof AReturnStatementStatement){ 
			//if it is a return "something" save that the function returns type STRING
			current_function.setReturnType("NONE");
		}
		if(grandpa instanceof AArglistArglist){
			function_argument_list.add("NONE");
			for (Function f: func_list){
				if(f.getName().equals(function_argument_list.get(0)))
				{
					if((function_argument_list.size()-f.gettVar_types().size())<2)
					{
						if(!(f.gettVar_types().get(function_argument_list.size()-2)).equals(function_argument_list.get(function_argument_list.size()-1))&&!((f.gettVar_types().get(function_argument_list.size()-2)).equals("UNKNOWN")))
						{
							printError(node, ERRORS.TYPE_MISMATCH);
						}
						//if the variable was declared as unknown inside the function, now a type is given to it by the arguments
						if((f.gettVar_types().get(function_argument_list.size()-2)).equals("UNKNOWN"))
						{
							f.gettVar_types().set((function_argument_list.size()-2),"NONE");
							variableTypes.put(f.getVars().get(function_argument_list.size()-2), VARIABLE_TYPES.STRING);
							if((f.getVars().get(function_argument_list.size()-2)).equals(f.getVariableOfReturn()))
							{
								f.setReturnType("NONE");
							}
						}
						for(int j:f.getVarOfSameType())
						{
							if(j==function_argument_list.size()-2)
							{
								//We can't have addition/subtraction with none
								printError(node, ERRORS.TYPE_MISMATCH);
							}
						}
					}
				}
					
			}
		}
		Node parent2 = node.parent().parent(); 
		if(parent2 instanceof AAdditionExExpression || parent2 instanceof ASubtractionExExpression || parent2 instanceof APlplExpression || parent2 instanceof AMinminExpression || parent2 instanceof ADivisionExpression || parent2 instanceof AModuloExpression || parent2 instanceof AMultiplicationExpression || parent2 instanceof APowerExpression || parent2 instanceof ALenExpExpression)
		{
			printError(parent2.parent(), ERRORS.NONE);
		}
	}

	public static void printError(Node node, MyVisitor.ERRORS error) {
		total_errors++;

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
            case TYPE_MISMATCH:
                System.out.println("Error at Node " + node + ": Type mismatch");
                break;
            case NONE:
                System.out.println("Error at Node " + node + ": arithmetic operation with type None");
                break;
            case MISUSED_FUNCTION:
                System.out.println("Error at Node " + node + ": Misused function");
                break;
            case REDEFINED_FUNCTION:
                System.out.println("Error at Node " + node + ": Redefined function");
                break;
			case REDEFINED_VARIABLE:
				System.out.println("Error at Node " + node + ": Redefined variable");
                break;

            // Add cases for other error types as needed
			case NO_ERROR:
				break;

            default:
                System.out.println("Unknown error at Node " + node);
        }
		if (node instanceof AGoal) {
			total_errors-=1;
			System.err.println("total errors: "+ total_errors);
			System.exit(-1);
		}
	}
	

	private int getX(AFuncCallFunctionCall node, int x) {
		AArglistArglist args =  (AArglistArglist)node.getArglist().get(0);
		x++;
		LinkedList<PExpression> more = (LinkedList<PExpression> )args.getR();

		for(int i =0 ; i< more.size();i++)
		{
			x++;
		}
		return x;
	}


	@Override
	public void inADefFuncFunction(ADefFuncFunction node)
	{ 

		String fName = node.getId().toString().trim();
		add_type = "null";


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
			
				// calculation of same name function's arguments and assignments
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
		// Create a new function
		if (functions.containsKey(name)) { // If it exists, add the node to the existing list of nodes
			
			functions.get(name).add(node);

			Function f = new Function(countVars(node).get(1), countVars(node).get(0),name);
			func_list.add(f);
			current_function = f;

		} 
		else 
		{
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
		
		if(!node.getArgument().isEmpty()) //there are arguments f(x1,x2,...)
		{
			AArgArgument argument = ((AArgArgument) (node.getArgument().get(0)));
			
			count_total_vars++;
			if(!argument.getAssignValue().isEmpty())
			{
				
				count_default_vars++;
			}	
			LinkedList ciav = argument.getCiav();
			if (!ciav.isEmpty())
			{
				ACommaIdCiav l = (ACommaIdCiav) ciav.get(0);
			}
			for ( int k = 0; k < ciav.size();k++)
			{
				ACommaIdCiav c = (ACommaIdCiav) ciav.get(k);
				
				if (c.getAssignValue().isEmpty())
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
		
		String id = node.getId().toString().trim();
		if (node.getAssignValue().isEmpty())
		{
			if(variableTypes.containsKey(id))
			{
				variableTypes.remove(id);
			}

			variableTypes.put(id, VARIABLE_TYPES.UNKNOWN);
			current_function.addVar_type("UNKNOWN");
			current_function.addVar(id);
		
		}

		
	}
	@Override
	public void inACommaIdCiav(ACommaIdCiav node) {

		String id = node.getId().toString().trim();

		if (node.getAssignValue().isEmpty())
		{
			if(variableTypes.containsKey(id))
			{
				variableTypes.remove(id);
			}
			variableTypes.put(id, VARIABLE_TYPES.UNKNOWN);

			current_function.addVar_type("UNKNOWN");
			current_function.addVar(id);

		}
	}
	

	// @Override
	// public void inAAdditionExExpression(AAdditionExExpression node){
	// }

	@Override
	public void inASubtractionExExpression(ASubtractionExExpression node){
		if(node.parent() instanceof AReturnStatementStatement){
			current_function.setReturnType("NUMBER");
		}
	}

	
	@Override
	public void inADivisionExpression(ADivisionExpression node){
		if(node.parent() instanceof AReturnStatementStatement){
			current_function.setReturnType("NUMBER");
		}
	}

	@Override
	public void inAModuloExpression(AModuloExpression node){
		if(node.parent() instanceof AReturnStatementStatement){
			current_function.setReturnType("NUMBER");
		}
	}
	
	@Override
	public void inAMultiplicationExpression(AMultiplicationExpression node){
		if(node.parent() instanceof AReturnStatementStatement){
			current_function.setReturnType("NUMBER");
		}
	}

	@Override
	public void inAPowerExpression(APowerExpression node){
		if(node.parent() instanceof AReturnStatementStatement){
			current_function.setReturnType("NUMBER");
		}
	}

	public int findIndex(LinkedList<String> list, String target) 
	{
			ListIterator<String> iterator = list.listIterator();
			int index = 0;

			while (iterator.hasNext()) {
				if (iterator.next().equals(target)) {
					return index;
				}
				index++;
			}

			return -1; // Element not found
	}

	@Override
	public void inAFuncCallFunctionCall(AFuncCallFunctionCall node) {
		int x = 0;
		curr_type_add ="null";
		String id = node.getId().toString().trim();
		
		if(functions.containsKey(id))
		{	
			//counting how many arguments are given and compare it with the ones saved.
			if(!node.getArglist().isEmpty())
			{
				x = getX(node, x);

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
					if(!(func_list.get(i).getTotal_vars() - func_list.get(i).getDef_vars() <= x && x <= func_list.get(i).getTotal_vars()))
					{
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
				printError(node, ERRORS.WRONG_FUNCTION_PARAMETERS);
			}
			else
			{
				int num_of_args = CountItemsInString(node.getArglist().toString().trim());
				int index = -1;
				Function f = null;
				for(int i =0; i< func_list.size();i++)
				{
					if(func_list.get(i).getName().equals(id))
					{
						//if x (number of arguments given here) is bigger or equal to the number of parameters 
						//that have got to be given for the function call and less than the total variables
						//we can make that comparison here because we've already checked for wrong function call above
						if((func_list.get(i).getTotal_vars() - func_list.get(i).getDef_vars() <= x && x <= func_list.get(i).getTotal_vars()))
						{
							index = i;
						}
					}
				}
				f = func_list.get(index);
				f.decideIfIsReturnUnknown();
				if(num_of_args== 0)
				{
					if(index>=0)
					{
						f = func_list.get(index);
			            Boolean foundError = false;
						Node grandpa = node.parent().parent();
						if(grandpa instanceof AMultiplicationExpression || grandpa instanceof ASubtractionExExpression || grandpa instanceof APlplExpression||grandpa instanceof AMinminExpression||grandpa instanceof ADivisionExpression||grandpa instanceof AModuloExpression||grandpa instanceof APowerExpression)
						{
							if((f.getReturnType().equals("STRING") || f.getReturnType().equals("NONE")))
							{
								foundError=true;
								printError(node, ERRORS.MISUSED_FUNCTION);
							}
						}
						if(grandpa instanceof AAdditionExExpression)
						{
							//we can only have str + str or number + number
							if(f.getReturnType().equals("NONE"))
							{
								//we can't have a none in an addition
								foundError=true;
								printError(node, ERRORS.MISUSED_FUNCTION);
							}
							
							if(f.getReturnType().equals("STRING"))
							{
								calculateTypeOfAddition(node, VARIABLE_TYPES.STRING);
							}
							else if(f.getReturnType().equals("NUMBER"))
							{
								calculateTypeOfAddition(node, VARIABLE_TYPES.NUMBER);
							}
							
						}
						if(!foundError)
						{
							if(f.getReturnType().equals("STRING"))
							{
								calculateTypeOfAssignFunc(node, VARIABLE_TYPES.STRING);
							}
							else if(f.getReturnType().equals("NUMBER"))
							{
								calculateTypeOfAssignFunc(node, VARIABLE_TYPES.NUMBER);
							}
							else if (f.getReturnType().equals("NONE"))
							{
								calculateTypeOfAssignFunc(node, VARIABLE_TYPES.NONE);
							}
						}
					}
				}
			}

		}
		function_argument_list = new LinkedList<String>();
		function_argument_list.add(id);
	}

	public void funcAfterHandlingArguments(AFuncCallFunctionCall node) {
		int x = 0;
		String id = node.getId().toString().trim();
		//counting how many arguments are given and compare it with the ones saved.
		if(!node.getArglist().isEmpty())
		{
			x = getX(node, x);

		}
		else
		{
			x = 0;
		}

		int index = -1;
		Function f = null;
		for(int i =0; i< func_list.size();i++)
		{
			if(func_list.get(i).getName().equals(id))
			{
				//if x (number of arguments given here) is bigger or equal to the number of parameters 
				//that have got to be given for the function call and less than the total variables
				//we can make that comparison here because we've already checked for wrong function call above
				if((func_list.get(i).getTotal_vars() - func_list.get(i).getDef_vars() <= x && x <= func_list.get(i).getTotal_vars()))
				{
					index = i;
				}
			}
		}
		if(index>=0)
		{
			f = func_list.get(index);
			Node grandpa = node.parent().parent();
			Boolean foundError = false;
			if(grandpa instanceof AMultiplicationExpression || grandpa instanceof ASubtractionExExpression || grandpa instanceof APlplExpression||grandpa instanceof AMinminExpression||grandpa instanceof ADivisionExpression||grandpa instanceof AModuloExpression||grandpa instanceof APowerExpression)
			{
				if((f.getReturnType().equals("STRING") || f.getReturnType().equals("NONE")))
				{
					printError(node, ERRORS.MISUSED_FUNCTION);
					foundError = true;
				}
			}
			if(grandpa instanceof AAdditionExExpression)
			{
				if(f.getReturnType().equals("STRING"))
				{
					calculateTypeOfAddition(node, VARIABLE_TYPES.STRING);
				}
				else if(f.getReturnType().equals("NUMBER"))
				{
					calculateTypeOfAddition(node, VARIABLE_TYPES.NUMBER);
				}
				else if (f.getReturnType().equals("NONE"))
				{
					foundError=true;
					printError(node, ERRORS.MISUSED_FUNCTION);
				}
			}
			if(!foundError)
			{
				if(f.getReturnType().equals("STRING"))
				{
					calculateTypeOfAssignFunc(node, VARIABLE_TYPES.STRING);
				}
				else if(f.getReturnType().equals("NUMBER"))
				{
					calculateTypeOfAssignFunc(node, VARIABLE_TYPES.NUMBER);
				}
				else if (f.getReturnType().equals("NONE"))
				{
					calculateTypeOfAssignFunc(node, VARIABLE_TYPES.NONE);
				}
			}
			
		}
		//}
		
	}

	public int CountItemsInString(String inputString) {
        
        // Remove unwanted characters and split the string into an array
        String[] items = inputString.replaceAll("[\\[\\]'\"\\\\]", "").split("\\s+");
    
        // Count the number of items in the array
        int itemCount = items.length;
		if(inputString.equals("[]"))
		{
			itemCount = 0;
		}
    
        return itemCount ;
    }

	
	//this method will find if we are in an addition. if yes then the type of the addition should be either A or B:
	//A.initialized: if this is the first not-unknown member of the addition then we realise what type the addition should be
	//B.compared: if the type of the addition is known we compare the type of node to see if we have a type missmatch
	public void calculateTypeOfAddition(Node node, VARIABLE_TYPES var_type)
	{
		String type = var_type.toString();
		Boolean missmatch_found = false;
		Node parent = node;
		while(true)
		{
			//We will go through all the addition parents. 
			//This is because we could have additions inside additions: in this case the type could be determined on a higher level
			parent = parent.parent();
			if(parent instanceof AAdditionExExpression)
			{
				if(add_type.equals("null")||add_type.equals("UNKNOWN")) //Case A
				{
					add_type = type;
				}
				else if(!add_type.equals(type)) //Case B
				{
					printError(node, ERRORS.TYPE_MISMATCH);
					missmatch_found=true;
					break;
				}
			}
			else if(parent instanceof AGoal)
			{
				break;
			}
		}
		//if there wasn't a missmatch we will check if this addition happens in a return statement.
		//this could also go one of two ways:
		//If the type is number: 
		//no matter what the parent of the addition is, the function's return type is number
		//for example: min(addition), max(addition), (addition)++
		//If the type is string:
		//if the parent of the addition is just return then the return type is string
		//but the parent of the addition is len or max, the return type is a number (for example: len("compiler")=8)
		if(!missmatch_found)
		{
			parent= node;
			if(type.equals("NUMBER"))
			{
				
				while (true) {
					parent = parent.parent();
					if(parent instanceof AReturnStatementStatement)
					{
						current_function.setReturnType(type);
						break;
					}
					else if(parent instanceof AGoal)
					{
						break;
					}
				}
			}
			else if(type.equals("STRING"))
			{
				
				while (true) {
					parent = parent.parent();
					if(parent instanceof AReturnStatementStatement)
					{
						current_function.setReturnType(type);
						break;
					}
					else if(parent instanceof ALenExpExpression || parent instanceof AMaxExprExpression||parent instanceof AMinExprExpression)
					{
						type = "NUMBER";
					}
					else if(parent instanceof AGoal)
					{
						break;
					}
				}
			}
			//check if we also are in an assignment
			parent= node;
			calculateTypeOfAssign(parent, var_type);
		}
	}

	public void calculateTypeOfAdditionId(Node node, VARIABLE_TYPES var_type, String name)
	{
		String type = var_type.toString();
		Boolean missmatch_found = false;
		Node parent = node;
		int indexOfSameType = findIndex(current_function.getVars(), name);
		Integer indexOfSameTypeInteger = Integer.valueOf(indexOfSameType);
		if(indexOfSameType!=-1)
		{
			current_function.addVarOfSameType(indexOfSameTypeInteger);
		}
		while(true)
		{
			//We will go through all the addition parents. 
			//This is because we could have additions inside additions: in this case the type could be determined on a higher level
			parent = parent.parent();
			if(parent instanceof AAdditionExExpression)
			{
				if(!(variableTypes.get(name).name().trim()).equals("UNKNOWN"))
				{
					if(add_type.equals("null")) //Case A
					{
						add_type = type;
					}
					else if(!add_type.equals(type)) //Case B
					{
						printError(node, ERRORS.TYPE_MISMATCH);
						missmatch_found=true;
						break;
					}
					if(indexOfSameType!=-1)
					{
						current_function.setReturnType(variableTypes.get(name).name().trim());
						for(int j:current_function.getVarOfSameType())
						{
							current_function.gettVar_types().set(j,variableTypes.get(name).name().trim());
						}
					}
				}
				else
				{
					variableTypes.put(name, var_type);
					if(indexOfSameType!=-1)
					{
						for(int j:current_function.getVarOfSameType())
						{
							current_function.gettVar_types().set(j,add_type);
						}
					}
				}
				
			}
			else if(parent instanceof AGoal)
			{
				break;
			}
		}
		//if there wasn't a missmatch we will check if this addition happens in a return statement.
		//this could also go one of two ways:
		//If the type is number: 
		//no matter what the parent of the addition is, the function's return type is number
		//for example: min(addition), max(addition), (addition)++
		//If the type is string:
		//if the parent of the addition is just return then the return type is string
		//but the parent of the addition is len or max, the return type is a number (for example: len("compiler")=8)
		if(!missmatch_found)
		{
			parent= node;
			if(type.equals("NUMBER"))
			{
				
				while (true) {
					parent = parent.parent();
					if(parent instanceof AReturnStatementStatement)
					{
						current_function.setReturnType(type);
						break;
					}
					if(parent instanceof AGoal)
					{
						break;
					}
				}
			}
			else if(type.equals("STRING"))
			{
				
				while (true) {
					parent = parent.parent();
					if(parent instanceof AReturnStatementStatement)
					{
						current_function.setReturnType(type);
						break;
					}
					if(parent instanceof ALenExpExpression || parent instanceof AMaxExprExpression||parent instanceof AMinExprExpression)
					{
						type = "NUMBER";
					}
					if(parent instanceof AGoal)
					{
						break;
					}
				}
			}
		}
	}


	private String getString(Node grandpa, String id) {
		if(grandpa instanceof ACommaIdCiav)
		{
			id = ((ACommaIdCiav)grandpa).getId().toString().trim();



			current_function.addVar_type("STRING");
			current_function.addVar(id);

		}
		else if(grandpa instanceof AArgArgument)
		{
			id = ((AArgArgument)grandpa).getId().toString().trim();
			ADefFuncFunction gp = (ADefFuncFunction)grandpa.parent();

			String func_name = gp.getId().toString().trim();
            for (Function function : func_list) {
                if (function.getName().toString().trim().equals(func_name)) {
                    function.addVar_type("STRING");
                    function.addVar(id);
                }
            }
		}
		return id;
	}

	public void calculateTypeOfAssign(Node node, VARIABLE_TYPES var_type)
	{
		String type = var_type.toString();
		Node parent = node;
		String id = null;

		while(true)
		{
			//We will go through all the parents to find whether a parent is an Assign statement. 
			//We will break the loop if we find anything that is a function. That is because we don't want the type of our the assignment to be changed and a function can do that.
			//Although the loop will change when finding a function (since we could not calculate the type) that does not include len, max or min (because we know it will return number)
			//If the type is STRING through we will check for  len, max or min to change the type to NUMBER.
			parent = parent.parent();
			if((!(node instanceof AFuncCallFunctionCall)) && (parent instanceof AFuncCallFunctionCall || parent instanceof AFuncCallExprExpression)) 
			{
				break;
			}
			if(parent instanceof ALenExpExpression || parent instanceof AMaxExprExpression||parent instanceof AMinExprExpression) 
			{
				if(type.equals("STRING"))
				{
					type = "NUMBER";
				}
			}
			if(parent instanceof AAssignStatementStatement)
			{
				id = ((AAssignStatementStatement)parent).getId().toString().trim();
				if(id!=null)
				{
					if(variableTypes.containsKey(id)) 
					{
						variableTypes.remove(id);
						
					}
					variableTypes.put(id, var_type);
				}
				break;
			}
			else if(parent instanceof AGoal)
			{
				break;
			}
		}
		if(node instanceof AFuncCallFunctionCall)
		{
			for(Function f:func_list)
			{
				if(f.getIsReturnUnknown())
				{
					f.setReturnType("UNKNOWN");
				}
			}
		}
	}

	public void calculateTypeOfAssignFunc(Node node, VARIABLE_TYPES var_type)
	{
		String type = var_type.toString();
		Node parent = node;
		String id = null;

		while(true)
		{
			//We will go through all the parents to find whether a parent is an Assign statement. 
			//We will break the loop if we find anything that is a function. That is because we don't want the type of our the assignment to be changed and a function can do that.
			//Although the loop will change when finding a function (since we could not calculate the type) that does not include len, max or min (because we know it will return number)
			//If the type is STRING through we will check for  len, max or min to change the type to NUMBER.
			parent = parent.parent();
			if((!(node instanceof AFuncCallFunctionCall)) && (parent instanceof AFuncCallFunctionCall || parent instanceof AFuncCallExprExpression)) 
			{
				break;
			}
			if(parent instanceof ALenExpExpression || parent instanceof AMaxExprExpression||parent instanceof AMinExprExpression || parent instanceof AMultiplicationExpression || parent instanceof ASubtractionExExpression || parent instanceof APlplExpression||parent instanceof AMinminExpression||parent instanceof ADivisionExpression||parent instanceof AModuloExpression||parent instanceof APowerExpression) 
			{
				if(type.equals("STRING"))
				{
					type = "NUMBER";
				}
				else if(type.equals("NONE"))
				{
					printError(node, ERRORS.MISUSED_FUNCTION);
					break;
				}
			}
			if(parent instanceof AAssignStatementStatement)
			{
				id = ((AAssignStatementStatement)parent).getId().toString().trim();
				if(id!=null)
				{
					if(variableTypes.containsKey(id)) 
					{
						variableTypes.remove(id);
						
					}
					variableTypes.put(id, var_type);
				}
				break;
			}
			else if(parent instanceof AGoal)
			{
				break;
			}
		}
		if(node instanceof AFuncCallFunctionCall)
		{
			for(Function f:func_list)
			{
				if(f.getIsReturnUnknown())
				{
					f.setReturnType("UNKNOWN");
				}
			}
		}
	}
}