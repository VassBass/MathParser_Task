package model;

import java.util.Objects;

public class Equation {

    private int id;
    private String equation;
    private double result;

    public Equation(){}

    public void setId(int id){this.id = id;}
    public void setEquation(String equation){this.equation = equation;}
    public void setResult(double result){this.result = result;}

    public int getId(){return id;}
    public String getEquation(){return equation;}
    public double getResult(){return result;}

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
                && o.getResult() == this.getResult();
    }
}
