package model;

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
}
