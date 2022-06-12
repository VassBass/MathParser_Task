package repository;

import model.Equation;

import java.util.ArrayList;

public interface EquationRepository {
    ArrayList<Equation>getAll();
    Equation get(int id);

    boolean add(Equation equation);

    boolean set(Equation equation);

    boolean remove(int id);
}
