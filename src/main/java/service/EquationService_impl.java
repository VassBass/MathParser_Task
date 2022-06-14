package service;

import model.Equation;
import repository.EquationRepository;
import repository.EquationRepository_impl;

import java.util.ArrayList;

public class EquationService_impl implements EquationService {
    private final EquationRepository repository = new EquationRepository_impl();

    /**
     * @return list of equations from repository
     * @see EquationRepository#getAll()
     * @see EquationRepository_impl#getAll() 
     */
    @Override
    public ArrayList<Equation> getAll() {
        return repository.getAll();
    }

    /**
     * @param id of equation who need to search in repository
     * @return equation with this id from repository or null if equation with this id not found
     * @see EquationRepository#get(int)
     * @see EquationRepository_impl#get(int) 
     */
    @Override
    public Equation get(int id) {
        return repository.get(id);
    }

    /**
     * Changes equation with id of equation from @param
     * @param equation to change
     * @return true if equation was changed or false if not
     * @see EquationRepository#set(Equation)
     * @see EquationRepository_impl#set(Equation) 
     */
    @Override
    public boolean set(Equation equation) {
        return repository.set(equation);
    }

    /**
     * Adds new equation
     * @param equation to add
     * @return id of added equation or -1 if equation wasn't added
     * @see EquationRepository#add(Equation)
     * @see EquationRepository_impl#add(Equation) 
     */
    @Override
    public int add(Equation equation) {
        return repository.add(equation);
    }

    /**
     * Removes equation with this id
     * @param id of equation who need to removed
     * @return true if equation was removed or false if not
     * @see EquationRepository#remove(int)
     * @see EquationRepository_impl#remove(int)
     */
    @Override
    public boolean remove(int id) {
        return repository.remove(id);
    }
}
