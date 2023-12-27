public class Function {

    private int def_vars =0;
    private int total_vars =0;
    
    public void setDef_vars(int def_vars) {
        this.def_vars = def_vars;
    }

    public void setTotal_vars(int total_vars) {
        this.total_vars = total_vars;
    }

    private String name ;
    
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
        this.total_vars=total_vars;
        this.name = name;
    }
    
}
