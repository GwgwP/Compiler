import java.util.LinkedList;

public class Function {


    private String name ;
    private int def_vars =0;
    private int total_vars =0;
    private LinkedList<String> var_types ;
    private String returnType;
    
    public LinkedList<String> gettVar_types() {
        return var_types;
    }

    public void addVar_type(String var_type) {
        var_types.add(var_type);
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
        this.returnType = "UNKNOWN";
    }
    
}
