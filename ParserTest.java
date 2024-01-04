import java.io.*;
import minipython.lexer.Lexer;
import minipython.parser.Parser;
import minipython.node.*;
import java.util.*;

public class ParserTest
{
  public static void main(String[] args)
  {
    try
    {
      Parser parser = new Parser(new Lexer(new PushbackReader(new FileReader(args[0].toString()), 1024)));
      Hashtable symtable =  new Hashtable();
      Hashtable functions = new Hashtable();
      Hashtable variableTypes = new Hashtable();
      Start ast = parser.parse();
      MyVisitor visitor1 = new MyVisitor(symtable, functions, variableTypes);
      ast.apply(visitor1);
      MySecondVisitor visitor2 = new MySecondVisitor(visitor1.getFunctions(), visitor1.getFunc_list());
      ast.apply(visitor2);
    }
    catch (Exception e)
    {
      System.err.println(e);
    }
  }
}