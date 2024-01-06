import minipython.analysis.*;
import minipython.node.*;
import java.util.*;
public class MyVisitor extends DepthFirstAdapter 
{
	public static int total_errors=0;
	public static Function current_function = null;
	public static String curr_type_add_sub = "null"; //type of the arguments that will be used in an addition/subbr=traction together and so must be same type
    public static LinkedList<String> function_argument_list = new LinkedList<>();

	
 	public static Hashtable<String, Node> variables;
	
	public static Hashtable<String, LinkedList<Node>> functions = new Hashtable<>();

	public static Hashtable<String, VARIABLE_TYPES> variableTypes;

	//these two variables are initialized again in the "in" function of addition and multiplication respectively
	String add_type = "null"; //what is the type of the current addition. we can only have str + str or number + number
	String mult_type = "null"; //what is the type of the current multiplication. we can only have number * number or number * str
	
	

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
		TYPE_MISSMATCH,      // #4th error
		NONE,                // #5th error
		MISUSED_FUNCTION,    // #6th error
		REDEFINED_FUNCTION,  // #7th error
		REDIFINED_VARIABLE,
		NO_ERROR //DEBUG
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
			if (node == forLoop.getLid() && variableTypes.containsKey(id2.getIdent().getText().trim())) 
			{
				VARIABLE_TYPES variableType = variableTypes.get(id2.getIdent().getText().trim());
				variableTypes.put(name, variableType);
				variables.put(id1.toString().trim(), node);
			}
		}
		if(node.parent().parent().parent().parent() instanceof AReturnStatementStatement){ 
			if (!variableTypes.containsKey(name)) 
			{
				// Print error message
				printError(node, ERRORS.UNDECLARED_VARIABLE);
			}
			else 
			{
				if(node.parent().parent().parent() instanceof ASubtractionExExpression || node.parent().parent().parent() instanceof ADivisionExpression|| node.parent().parent().parent() instanceof  APowerExpression)
				{
					//we check if we are in the return statement of a function and if there is a  division or power
					//this method finds the index of the id in the linked list of this function so we can check the type of the variable
					//if it is unknown we will make it to be number because you cant have division or power with any other type of variable
					//this way we will be able to control if the variable is called correctly since it will no longer be unknown
					int index = findIndex(current_function.getVars(), name);
					if(current_function.gettVar_types().get(index).equals("UNKNOWN"))
					{
						current_function.gettVar_types().set(index,"NUMBER");
					} 
					current_function.setReturnType("NUMBER");
				}
				else if(node.parent().parent().parent() instanceof AAdditionExExpression ){
					int indexOfSameType = findIndex(current_function.getVars(), name);
					Integer indexOfSameTypeInteger = Integer.valueOf(indexOfSameType);
					current_function.addVarOfSameType(indexOfSameTypeInteger);
				}
				else{
					current_function.setReturnType(variableTypes.get(name).toString());
				}
			}
		}

	
		if(node.parent().parent().parent() instanceof AArglistArglist){
			function_argument_list.add(variableTypes.get(name).toString());
			for (Function f: func_list){
				if(f.getName().equals(function_argument_list.get(0)))
				{
					if((function_argument_list.size()-f.gettVar_types().size())<2)
					{
						if(!(f.gettVar_types().get(function_argument_list.size()-2)).equals(function_argument_list.get(function_argument_list.size()-1))&&!((f.gettVar_types().get(function_argument_list.size()-2)).equals("UNKNOWN")))
						{
							printError(node, ERRORS.TYPE_MISSMATCH);
						}
						for(int j:f.getVarOfSameType())
						{
							if(j==function_argument_list.size()-2)
							{
								if(curr_type_add_sub.equals("null"))
								{
									if(!variableTypes.get(name).toString().equals("UNKNOWN"))
									{
										curr_type_add_sub = variableTypes.get(name).toString();
									}
								}
								else
								{
									if(!curr_type_add_sub.equals(variableTypes.get(name).toString()))
									{
										printError(node, ERRORS.TYPE_MISSMATCH);
									}
								}
							}
						}
					}
				}
			}
		}
		// Addressing 5th error part2 (y = None -> print y+2)
		if( parent3 instanceof AAdditionExExpression || parent3 instanceof ASubtractionExExpression || parent3 instanceof APlplExpression || parent3 instanceof AMinminExpression || parent3 instanceof ADivisionExpression || parent3 instanceof AModuloExpression || parent3 instanceof AMultiplicationExpression || parent3 instanceof APowerExpression || parent3 instanceof ALenExpExpression)
		{
			if(variableTypes.containsKey(name))
			{
				String type = variableTypes.get(name).name().trim();
				if(type.equals("NONE"))
				{
					printError(node, ERRORS.NONE);
				}
				if(node.parent().parent().parent() instanceof AAdditionExExpression)
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
								printError(node, ERRORS.MISUSED_FUNCTION);
							}
						}
					}
				}
				if(node.parent().parent().parent() instanceof AMultiplicationExpression)
				{
					//we check the variable types to make sure we only have str*number or number*number
					//We also check if we are in a return statement. If we are then we set the function's return type to:
					//string: if either of the multiplication variables is string
					//number: if neither of the multiplication variables is string
					AMultiplicationExpression greatGrandpa = (AMultiplicationExpression) node.parent().parent().parent();
					if(node.parent().parent() == greatGrandpa.getL())
					{
						mult_type = variableTypes.get(name).name().trim();
					}
					else if(node.parent().parent() == greatGrandpa.getR())
					{
						if(!mult_type.equals("UNKNOWN")&&!mult_type.equals("null"))
						{
							if((variableTypes.get(name).name().trim()).equals("STRING")&&mult_type.equals("STRING"))
							{
								printError(node, ERRORS.MISUSED_FUNCTION);
							}
							if(node.parent().parent().parent().parent() instanceof AReturnStatementStatement)
							{
								if((variableTypes.get(name).name().trim()).equals("STRING")||mult_type.equals("STRING"))
								{
									current_function.setReturnType("STRING");
								}
								else
								{
									current_function.setReturnType("NUMBER");
								}
							}
						}
					}
				}
			}

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
			current_function.addVar(id);
		
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
					func_list.get(i).addVar(id);
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
		if(grandpa instanceof ASubtractionExExpression || grandpa instanceof APowerExpression || grandpa instanceof APlplExpression || grandpa instanceof AMinminExpression || grandpa instanceof ADivisionExpression || grandpa instanceof AModuloExpression  )	
		{
			printError(node, ERRORS.TYPE_MISSMATCH);
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
							printError(node, ERRORS.TYPE_MISSMATCH);
						}
						for(int j:f.getVarOfSameType())
						{
							if(j==function_argument_list.size()-2)
							{
								if(curr_type_add_sub.equals("null"))
								{
									curr_type_add_sub = "STRING";
								}
								else
								{
									if(!curr_type_add_sub.equals("STRING"))
									{
										printError(node, ERRORS.TYPE_MISSMATCH);
									}
								}
							}
						}
					}
				}
			}
		}
		if(node.parent().parent() instanceof AAdditionExExpression )
		{
			
			AAdditionExExpression greatGrandpa = (AAdditionExExpression) node.parent().parent();
			if(node.parent() == greatGrandpa.getR())
			{
				if(!add_type.equals("UNKNOWN")&&!add_type.equals("null"))
				{
					if(!add_type.equals("STRING"))
					{
						printError(node, ERRORS.MISUSED_FUNCTION);
					}
				}
			}
			if(node.parent() == greatGrandpa.getL())
			{
				add_type = "STRING";
			}
		}
		if(node.parent().parent() instanceof AMultiplicationExpression )
		{
			AMultiplicationExpression greatGrandpa = (AMultiplicationExpression) node.parent().parent();
			if(node.parent() == greatGrandpa.getR())
			{
				if(!mult_type.equals("UNKNOWN")&&!mult_type.equals("null"))
				{
					//if we have a string then we expect the other part of the + to be a number, we cannot have str*str
					if(!mult_type.equals("NUMBER"))
					{
						printError(node, ERRORS.MISUSED_FUNCTION);
					}
				}
			}
			if(node.parent() == greatGrandpa.getL())
			{
				mult_type = "STRING";
			}
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
			
			current_function.addVar_type("STRING");
			current_function.addVar(id);
			
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
					func_list.get(i).addVar(id);
				}
			}
		}
		if(grandpa instanceof ASubtractionExExpression || grandpa instanceof APowerExpression || grandpa instanceof APlplExpression || grandpa instanceof AMinminExpression || grandpa instanceof ADivisionExpression || grandpa instanceof AModuloExpression  )	
		{
			printError(node, ERRORS.TYPE_MISSMATCH);
		}
		if(id!=null)
		{
			if(variableTypes.containsKey(id))
			{
				variableTypes.remove(id);
				
			}
			variableTypes.put(id, VARIABLE_TYPES.STRING);
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

							printError(node, ERRORS.TYPE_MISSMATCH);
						}
						for(int j:f.getVarOfSameType())
						{
							if(j==function_argument_list.size()-2)
							{
								if(curr_type_add_sub.equals("null"))
								{
									curr_type_add_sub = "STRING";
								}
								else
								{
									if(!curr_type_add_sub.equals("STRING"))
									{
										printError(node, ERRORS.TYPE_MISSMATCH);
									}
								}
							}
						}
					}
					
				}
			}
		}
		if(node.parent().parent() instanceof AAdditionExExpression )
		{
			
			AAdditionExExpression greatGrandpa = (AAdditionExExpression) node.parent().parent();
			if(node.parent() == greatGrandpa.getR())
			{
				if(!add_type.equals("UNKNOWN")&&!add_type.equals("null"))
				{
					if(!add_type.equals("STRING"))
					{
						printError(node, ERRORS.MISUSED_FUNCTION);
					}
				}
			}
			if(node.parent() == greatGrandpa.getL())
			{
				add_type = "STRING";
			}
		}
		if(node.parent().parent() instanceof AMultiplicationExpression )
		{
			
			AMultiplicationExpression greatGrandpa = (AMultiplicationExpression) node.parent().parent();
			if(node.parent() == greatGrandpa.getR())
			{
				if(!mult_type.equals("UNKNOWN")&&!mult_type.equals("null"))
				{
					//if we have a string then we expect the other part of the + to be a number, we cannot have str*str
					if(!mult_type.equals("NUMBER"))
					{
						printError(node, ERRORS.MISUSED_FUNCTION);
					}
				}
			}
			if(node.parent() == greatGrandpa.getL())
			{
				mult_type = "STRING";
			}
		}
	}
	@Override
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
			current_function.addVar(id);
		

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
					func_list.get(i).addVar(id);
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
		if(grandpa instanceof AReturnStatementStatement){
			//if it is a return number save that the function returns type STRING
			current_function.setReturnType("NUMBER");
		}
		if(grandpa instanceof AArglistArglist){
			function_argument_list.add("NUMBER");
			for (Function f: func_list){
				if(f.getName().equals(function_argument_list.get(0)))
				{
					if((function_argument_list.size()-f.gettVar_types().size())<2)
					{
						if(!(f.gettVar_types().get(function_argument_list.size()-2)).equals(function_argument_list.get(function_argument_list.size()-1))&&!((f.gettVar_types().get(function_argument_list.size()-2)).equals("UNKNOWN")))
						{

							printError(node, ERRORS.TYPE_MISSMATCH);
						}
						for(int j:f.getVarOfSameType())
						{
							if(j==function_argument_list.size()-2)
							{
								if(curr_type_add_sub.equals("null"))
								{
									curr_type_add_sub = "NUMBER";
								}
								else
								{
									if(!curr_type_add_sub.equals("NUMBER"))
									{

										printError(node, ERRORS.TYPE_MISSMATCH);
									}
								}
							}
						}

					}
				}
			}
		}
		if(node.parent().parent().parent() instanceof AAdditionExExpression)
		{
			AAdditionExExpression greatGrandpa = (AAdditionExExpression) node.parent().parent().parent();
			if(node.parent().parent() == greatGrandpa.getL())
			{
				add_type = "NUMBER";
			}
			else if(node.parent().parent() == greatGrandpa.getR())
			{
				if(!add_type.equals("UNKNOWN")&&!add_type.equals("null"))
				{
					if(!add_type.equals("NUMBER"))
					{
						printError(node, ERRORS.MISUSED_FUNCTION);
					}
				}
			}
		}
		if(node.parent().parent().parent() instanceof AMultiplicationExpression)
		{
			AMultiplicationExpression greatGrandpa = (AMultiplicationExpression) node.parent().parent().parent();
			if(node.parent().parent() == greatGrandpa.getL())
			{
				mult_type = "NUMBER";
			}
			else if(node.parent().parent() == greatGrandpa.getR())
			{
				if(!mult_type.equals("UNKNOWN")&&!mult_type.equals("null"))
				{
					//we can only have num*num or str*num 
					if(!mult_type.equals("NUMBER")&&!mult_type.equals("STRING"))
					{
						printError(node, ERRORS.MISUSED_FUNCTION);
					}
				}
			}
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
			current_function.addVar(id);
		

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
					func_list.get(i).addVar(id);
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
							printError(node, ERRORS.TYPE_MISSMATCH);
						}
						for(int j:f.getVarOfSameType())
						{
							if(j==function_argument_list.size()-2)
							{
								//We can't have addition/substraction with none
								printError(node, ERRORS.TYPE_MISSMATCH);
							}
						}
					}
				}
					
			}
		}
		Node parent2 = node.parent().parent(); //TODO WHY NOT NODE DOESNT WORK PARENT
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
            case TYPE_MISSMATCH:
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
			case REDIFINED_VARIABLE:
				System.out.println("Error at Node " + node + ": Redifined variable");
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
		
			//System.exit(-1);
		}
		
		

	}
	
	
	@Override
	public void inAFuncCallFunctionCall(AFuncCallFunctionCall node) {
		int x = 0;
		curr_type_add_sub="null";
		String id = node.getId().toString().trim();
		if(!functions.containsKey(id))
		{
			//printError(node, ERRORS.UNDEFINED_FUNCTION);
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
					if(grandpa instanceof ASubtractionExExpression || grandpa instanceof APlplExpression||grandpa instanceof AMinminExpression||grandpa instanceof ADivisionExpression||grandpa instanceof AModuloExpression||grandpa instanceof APowerExpression)
					{
						if((f.getReturnType().equals("STRING") || f.getReturnType().equals("NONE")))
						{
							printError(node, ERRORS.MISUSED_FUNCTION);
						}
					}
					if(grandpa instanceof AAdditionExExpression)
					{
						//we can only have str + str or number + number
						if(f.getReturnType().equals("NONE"))
						{
							//we can't have a none in an addition
							printError(node, ERRORS.MISUSED_FUNCTION);
						}
						//if we are in the left part of the + of an addition we want to set the
						//type of the addition to the return type of our function
						AAdditionExExpression gp = (AAdditionExExpression) grandpa;
						if(node.parent() == gp.getL())
						{
							add_type = f.getReturnType();
						}
						else if(node.parent() == gp.getR())
						{
							if(!add_type.equals("UNKNOWN") && !add_type.equals("null"))
							{
								if(!add_type.equals( f.getReturnType()))
								{
									//we can't have addition of different types
									printError(node, ERRORS.MISUSED_FUNCTION);
								}
							}
						}
					}
					if(grandpa instanceof AMultiplicationExpression)
					{
						//we can only have str * number or number * number
						if(f.getReturnType().equals("NONE"))
						{
							//we can't have a none in an addition
							printError(node, ERRORS.MISUSED_FUNCTION);
						}
						//if we are in the left part of the * of an multiplication we want to inform the
						//type of the multiplication to the return type of our function by updating the mult_type variable
						AMultiplicationExpression gp = (AMultiplicationExpression) grandpa;
						if(node.parent() == gp.getL())
						{
							mult_type = f.getReturnType();
						}
						else if(node.parent() == gp.getR())
						{
							if(!mult_type.equals("UNKNOWN") && !mult_type.equals("null"))
							{
								if(mult_type.equals("STRING")&&f.getReturnType().equals("STRING"))
								{
									//we can't have addition of different types
									printError(node, ERRORS.MISUSED_FUNCTION);
								}
							}
						}
					}
				}

			}

		}
		function_argument_list = new LinkedList<String>();
		function_argument_list.add(id);
	}

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
		
		if(node.getArgument().size()!=0) //there are arguments f(x1,x2,...)
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
			current_function.addVar(id);
		
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
			current_function.addVar(id);

		}
	}
	@Override
	public void outAPrintStatementStatement(APrintStatementStatement node)
	{
		System.out.println("-------------------------------KLHSH THS PRINT-------------------------");
		for (Map.Entry<String, VARIABLE_TYPES> entry : variableTypes.entrySet()) {
            String key = entry.getKey();
            VARIABLE_TYPES value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
        }
		System.out.println("------------------------TELOS KLHSH THS PRINT-------------------------");
		// System.out.println();
		// System.out.println("-------------------------------KLHSH THS VAR_TYPES-------------------------");
		// System.out.println(func_list.size());
		// LinkedList ll = func_list.get(0).gettVar_types();
		// System.out.println("-------------------------------TELOS KLHSH THS VAR_TYPES-------------------------");
	
		// System.out.println("-------------------------------KLHSH THS VAR_TYPES-------------------------");
		// System.out.println(func_list.size());
		// ll = func_list.get(1).gettVar_types();
		// System.out.println("-------------------------------TELOS KLHSH THS VAR_TYPES-------------------------");
		// for (Function element : func_list) {
        //     System.out.println("func: "+element.getName());
		// 	System.out.println("function's return type: "+element.getReturnType() );
		//	System.out.println("functon's variable types:");
		// 	for(String type : element.gettVar_types()){
		// 		System.out.println("type: "+type);
		// 	}
		//	System.out.println("functon's variable names:");
		// 	for(String var : element.getVars()){
		// 		System.out.println("variable: "+var);
		// 	}
        // }
		// System.out.println("list of arguments read:");
		// for(String element: function_argument_list){
		// 	System.out.println(element);
		// }

	}

	@Override
	public void inAAdditionExExpression(AAdditionExExpression node){
		add_type = "null";
		if(node.parent() instanceof AReturnStatementStatement){ 
			current_function.setReturnType("NUMBER");
		}
	}

	@Override
	public void inASubtractionExExpression(ASubtractionExExpression node){
		if(node.parent() instanceof AReturnStatementStatement){
			current_function.setReturnType("NUMBER");
		}
	}

	// @Override
	// public void inAPlplExpression(APlplExpression node){
	// 	if(node.parent() instanceof AReturnStatementStatement){
	// 		current_function.setReturnType("NUMBER");
	// 	}
	// }

	// @Override
	// public void inAMinminExpression(AMinminExpression node){
	// 	if(node.parent() instanceof AReturnStatementStatement){
	// 		current_function.setReturnType("NUMBER");
	// 	}
	// }

	
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
		mult_type = "null";
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
}