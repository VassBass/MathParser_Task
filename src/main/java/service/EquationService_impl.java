package service;

import model.Equation;
import repository.EquationRepository;
import repository.EquationRepository_impl;

import java.util.ArrayList;

public class EquationService_impl implements EquationService {
    private final EquationRepository repository = new EquationRepository_impl();

    @Override
    public ArrayList<Equation> getAll() {
        return repository.getAll();
    }

    @Override
    public Equation get(int id) {
        return repository.get(id);
    }

    @Override
    public boolean set(Equation equation) {
        return repository.set(equation);
    }

    @Override
    public boolean add(Equation equation) {
        return repository.add(equation);
    }

    @Override
    public boolean remove(int id) {
        return repository.remove(id);
    }
}
