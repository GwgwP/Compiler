
import java.util.*;
import minipython.analysis.DepthFirstAdapter;
import minipython.node.*;
public class Mysecondvisitor extends DepthFirstAdapter
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

    public Mysecondvisitor(Hashtable<String, LinkedList<Node>> functions, LinkedList<Function> func_list) 
    {
        System.out.println("mpika");
        this.functions = functions;
        this.func_list = func_list;
        System.out.println("size: "+functions.size());
    }
}