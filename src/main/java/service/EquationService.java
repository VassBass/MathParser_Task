package service;

import model.Equation;

import java.util.ArrayList;

public interface EquationService {
    void init(String dbUrl);

    ArrayList<Equation>getAll();
    Equation getEquation(int id);

    boolean setEquation(Equation oldEquation, Equation newEquation);

    boolean addEquation(Equation equation);

    boolean removeEquation(int id);
}
