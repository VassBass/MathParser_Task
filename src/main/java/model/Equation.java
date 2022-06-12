package model;

import java.util.Objects;

public class Equation {

    private int id;
    private String equation, result;

    public Equation(){}

    public Equation(int id, String equation, String result){
        this.id = id;
        this.equation = equation;
        this.result = result;
    }

    public void setId(int id){this.id = id;}
    public void setEquation(String equation){this.equation = equation;}
    public void setResult(String result){this.result = result;}

    public int getId(){return id;}
    public String getEquation(){return equation;}
    public String getResult(){return result;}

    @Override
    public int hashCode() {
        return Objects.hash(id,equation,result);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) return false;
        if (obj.equals(this)) return true;

        Equation o = (Equation) obj;
        return o.getEquation().equals(this.equation)
                && o.getResult().equals(this.getResult());
    }
}
