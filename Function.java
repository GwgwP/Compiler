import java.util.LinkedList;

public class Function {


    private String name ;
    private int def_vars =0;
    private int total_vars =0;
    private LinkedList<Integer> var_of_same_type ; //variables that are in the addition or substraction of a return statement and need to be of the same type
    private LinkedList<String> var_types ;
    private LinkedList<String> vars ;
    private String returnType;
    
    public LinkedList<String> gettVar_types() {
        return var_types;
    }
    
    public LinkedList<Integer> getVarOfSameType () {
        return var_of_same_type ; 
    }

    public LinkedList<String> getVars() {
        return vars;
    }

    public void addVar_type(String var_type) {
        var_types.add(var_type);
    }

    public void addVar(String var) {
        vars.add(var);
    }

    public void addVarOfSameType(Integer var) {
        var_of_same_type.add(var);
    }

    public void setDef_vars(int def_vars) {
        this.def_vars = def_vars;
    }

    public void setTotal_vars(int total_vars) {
        this.total_vars = total_vars;
    }

    public void setReturnType(String type){
        this.returnType = type;
    }

    public String getReturnType(){
        return this.returnType;
    }
    
    public int getDef_vars() {
        return def_vars;
    }
   
    public int getTotal_vars() {
        return total_vars;
    }
   
    public String getName() {
        return name;
    }
   
    Function(int dv, int tv, String name)
    {
        this.def_vars=dv;
        this.total_vars=tv;
        this.name = name;
        this.var_types = new LinkedList<String>();
        this.vars = new LinkedList<String>();
        this.var_of_same_type = new LinkedList<Integer>();
        this.returnType = "UNKNOWN";
    }
    
}
