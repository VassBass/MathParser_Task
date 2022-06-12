package service;

import model.Equation;

import java.util.ArrayList;

public interface EquationService {
    ArrayList<Equation>getAll();
    Equation get(int id);

    boolean set(Equation equation);

    boolean add(Equation equation);

    boolean remove(int id);
}
