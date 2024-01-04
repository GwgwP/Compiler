import java.util.*;

import minipython.analysis.DepthFirstAdapter;
import minipython.node.*;

public class MySecondVisitor extends DepthFirstAdapter
{
    /**
     * The SecondVisitor does the second traversal of the code to handle
     * function calls that appear before their declaration.
     * 
     * 
     * @param funcitons     hashtable containing the name and node of the functions read
     */

    private Hashtable<String, LinkedList<Node>> functions;
    private LinkedList<Function> func_list;

    public MySecondVisitor(Hashtable<String, LinkedList<Node>> functions, LinkedList<Function> func_list) 
    {
        //MyVisitor.printError(n, MyVisitor.ERRORS.MISUSED_FUNCTION);
        this.functions = functions;
        this.func_list = func_list;
    }
    @Override
    public void outAFuncCallFunctionCall(AFuncCallFunctionCall node)
    {
        String id = node.getId().toString().trim();
		if(!functions.containsKey(id))
		{
			MyVisitor.printError(node, MyVisitor.ERRORS.UNDEFINED_FUNCTION);
		}
    }
    // @Override
    // public void inAIdId(AIdId node)
    // {
    //     String name = node.getIdent().getText().trim();

    //     if(node.parent().parent().parent() instanceof AArglistArglist){
	// 		MyVisitor.function_argument_list.add(MyVisitor.variableTypes.get(name).toString());
	// 		for (Function f: MyVisitor.func_list){
	// 			if(f.getName().equals(MyVisitor.function_argument_list.get(0)))
	// 			{
	// 				if((MyVisitor.function_argument_list.size()-f.gettVar_types().size())<2)
	// 				{
	// 					if(!(f.gettVar_types().get(MyVisitor.function_argument_list.size()-2)).equals(MyVisitor.function_argument_list.get(MyVisitor.function_argument_list.size()-1))&&!((f.gettVar_types().get(MyVisitor.function_argument_list.size()-2)).equals("UNKNOWN")))
	// 					{
	// 						MyVisitor.printError(node, MyVisitor.ERRORS.TYPE_MISSMATCH);
	// 					}
	// 					for(int j:f.getVarOfSameType())
	// 					{
	// 						if(j==MyVisitor.function_argument_list.size()-2)
	// 						{
	// 							if(MyVisitor.curr_type_add_sub.equals("null"))
	// 							{
	// 								if(!MyVisitor.variableTypes.get(name).toString().equals("UNKNOWN"))
	// 								{
	// 									MyVisitor.curr_type_add_sub = MyVisitor.variableTypes.get(name).toString();
	// 								}
	// 							}
	// 							else
	// 							{
	// 								if(!MyVisitor.curr_type_add_sub.equals(MyVisitor.variableTypes.get(name).toString()))
	// 								{
	// 									MyVisitor.printError(node, MyVisitor.ERRORS.TYPE_MISSMATCH);
	// 								}
	// 							}
	// 						}
	// 					}
	// 				}
	// 			}
	// 		}
	// 	}
    // }
    // @Override
    // public void inADoubleQuotesValueno(ADoubleQuotesValueno node)
    // {
    //     Node grandpa = node.parent().parent();
    //     //Checking error 4
	// 	if(grandpa instanceof AAdditionExExpression || grandpa instanceof ASubtractionExExpression || grandpa instanceof AMultiplicationExpression || grandpa instanceof APowerExpression || grandpa instanceof APlplExpression || grandpa instanceof AMinminExpression || grandpa instanceof ADivisionExpression || grandpa instanceof AModuloExpression  )	
	// 	{
	// 		MyVisitor.printError(node, MyVisitor.ERRORS.TYPE_MISSMATCH);
	// 	}
    //     if(grandpa instanceof AArglistArglist){
	// 		MyVisitor.function_argument_list.add("STRING");
	// 		for (Function f: MyVisitor.func_list){
	// 			if(f.getName().equals(MyVisitor.function_argument_list.get(0)))
	// 			{
	// 				if((MyVisitor.function_argument_list.size()-f.gettVar_types().size())<2)
	// 				{
	// 					if(!(f.gettVar_types().get(MyVisitor.function_argument_list.size()-2)).equals(MyVisitor.function_argument_list.get(MyVisitor.function_argument_list.size()-1))&&!((f.gettVar_types().get(MyVisitor.function_argument_list.size()-2)).equals("UNKNOWN")))
	// 					{					
	// 						MyVisitor.printError(node, MyVisitor.ERRORS.TYPE_MISSMATCH);
	// 					}
	// 					for(int j:f.getVarOfSameType())
	// 					{
	// 						if(j==MyVisitor.function_argument_list.size()-2)
	// 						{
	// 							if(MyVisitor.curr_type_add_sub.equals("null"))
	// 							{
	// 								MyVisitor.curr_type_add_sub = "STRING";
	// 							}
	// 							else
	// 							{
	// 								if(!MyVisitor.curr_type_add_sub.equals("STRING"))
	// 								{
	// 									MyVisitor.printError(node, MyVisitor.ERRORS.TYPE_MISSMATCH);
	// 								}
	// 							}
	// 						}
	// 					}
	// 				}
	// 			}
	// 		}
	// 	}
    // }

    // @Override
	// public void inASingleQuotesValueno(ASingleQuotesValueno node)
    // {
    //     Node grandpa = node.parent().parent();
    //     if(grandpa instanceof AArglistArglist){
	// 		MyVisitor.function_argument_list.add("STRING");
	// 		for (Function f: MyVisitor.func_list){
	// 			if(f.getName().equals(MyVisitor.function_argument_list.get(0)))
	// 			{
	// 				if((MyVisitor.function_argument_list.size()-f.gettVar_types().size())<2)
	// 				{
	// 					if(!(f.gettVar_types().get(MyVisitor.function_argument_list.size()-2)).equals(MyVisitor.function_argument_list.get(MyVisitor.function_argument_list.size()-1))&&!((f.gettVar_types().get(MyVisitor.function_argument_list.size()-2)).equals("UNKNOWN")))
	// 					{					
	// 						MyVisitor.printError(node, MyVisitor.ERRORS.TYPE_MISSMATCH);
	// 					}
	// 					for(int j:f.getVarOfSameType())
	// 					{
	// 						if(j==MyVisitor.function_argument_list.size()-2)
	// 						{
	// 							if(MyVisitor.curr_type_add_sub.equals("null"))
	// 							{
	// 								MyVisitor.curr_type_add_sub = "STRING";
	// 							}
	// 							else
	// 							{
	// 								if(!MyVisitor.curr_type_add_sub.equals("STRING"))
	// 								{
	// 									MyVisitor.printError(node, MyVisitor.ERRORS.TYPE_MISSMATCH);
	// 								}
	// 							}
	// 						}
	// 					}
	// 				}
	// 			}
	// 		}
	// 	}

    // }

    // @Override
	// public void inANumNum(ANumNum node)
    // {
    //     Node grandpa = node.parent().parent();
    //     if(grandpa instanceof AArglistArglist){
	// 		MyVisitor.function_argument_list.add("NUMBER");
	// 		for (Function f: func_list){
	// 			if(f.getName().equals(MyVisitor.function_argument_list.get(0)))
	// 			{
	// 				if((MyVisitor.function_argument_list.size()-f.gettVar_types().size())<2)
	// 				{
	// 					if(!(f.gettVar_types().get(MyVisitor.function_argument_list.size()-2)).equals(MyVisitor.function_argument_list.get(MyVisitor.function_argument_list.size()-1))&&!((f.gettVar_types().get(MyVisitor.function_argument_list.size()-2)).equals("UNKNOWN")))
	// 					{

	// 						MyVisitor.printError(node,MyVisitor.ERRORS.TYPE_MISSMATCH);
	// 					}
	// 					for(int j:f.getVarOfSameType())
	// 					{
	// 						if(j==MyVisitor.function_argument_list.size()-2)
	// 						{
	// 							if(MyVisitor.curr_type_add_sub.equals("null"))
	// 							{
	// 								MyVisitor.curr_type_add_sub = "NUMBER";
	// 							}
	// 							else
	// 							{
	// 								if(!MyVisitor.curr_type_add_sub.equals("NUMBER"))
	// 								{

	// 									MyVisitor.printError(node, MyVisitor.ERRORS.TYPE_MISSMATCH);
	// 								}
	// 							}
	// 						}
	// 					}

	// 				}
	// 			}
	// 		}
	// 	}

    // }
    // @Override
    // public void inANoneValueno(ANoneValueno node)
    // {
    //     Node grandpa = node.parent().parent();
    //     if(grandpa instanceof AArglistArglist){
	// 		MyVisitor.function_argument_list.add("NONE");
	// 		for (Function f: func_list){
	// 			if(f.getName().equals(MyVisitor.function_argument_list.get(0)))
	// 			{
	// 				if((MyVisitor.function_argument_list.size()-f.gettVar_types().size())<2)
	// 				{
	// 					if(!(f.gettVar_types().get(MyVisitor.function_argument_list.size()-2)).equals(MyVisitor.function_argument_list.get(MyVisitor.function_argument_list.size()-1))&&!((f.gettVar_types().get(MyVisitor.function_argument_list.size()-2)).equals("UNKNOWN")))
	// 					{
	// 						MyVisitor.printError(node, MyVisitor.ERRORS.TYPE_MISSMATCH);
	// 					}
	// 					for(int j:f.getVarOfSameType())
	// 					{
	// 						if(j==MyVisitor.function_argument_list.size()-2)
	// 						{
	// 							//We can't have addition/substraction with none
	// 							MyVisitor.printError(node, MyVisitor.ERRORS.TYPE_MISSMATCH);
	// 						}
	// 					}
	// 				}
	// 			}
					
	// 		}
	// 	}
    // }
    @Override 
	public void outAGoal(AGoal node)
	{
		MyVisitor.printError(node, MyVisitor.ERRORS.NO_ERROR);
	} 
}