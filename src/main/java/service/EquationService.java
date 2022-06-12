package service;

import model.Equation;

import java.util.ArrayList;

public interface EquationService {
    ArrayList<Equation>getAll();
    Equation get(int id);

    boolean set(Equation oldEquation, Equation newEquation);

    boolean add(Equation equation);

    boolean remove(int id);
}
