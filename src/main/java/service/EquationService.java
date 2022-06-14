package service;

import model.Equation;
import repository.EquationRepository;
import repository.EquationRepository_impl;

import java.util.ArrayList;

public interface EquationService {

    /**
     * @return list of equations
     * @see EquationService_impl#getAll()
     * @see EquationRepository#getAll()
     * @see EquationRepository_impl#getAll() 
     */
    ArrayList<Equation>getAll();

    /**
     * @param id of equation who need to search
     * @return equation with this id or null if equation with this id not found
     * @see EquationService_impl#get(int)
     * @see EquationRepository#get(int) 
     * @see EquationRepository_impl#get(int)
     */
    Equation get(int id);

    /**
     * Changes equation with id of equation from @param
     * @param equation to change
     * @return true if equation was changed or false if not
     * @see EquationService_impl#set(Equation)
     * @see EquationRepository#set(Equation)
     * @see EquationRepository_impl#set(Equation)
     */
    boolean set(Equation equation);


    /**
     * Adds new equation
     * @param equation to add
     * @return id of added equation or -1 if equation wasn't added
     * @see EquationService_impl#add(Equation)
     * @see EquationRepository#add(Equation)
     * @see EquationRepository_impl#add(Equation)
     */
    int add(Equation equation);

    /**
     * Removes equation with this id
     * @param id of equation who need to removed
     * @return true if equation was removed or false if not
     * @see EquationService_impl#remove(int)
     * @see EquationRepository#remove(int)
     * @see EquationRepository_impl#remove(int)
     */
    boolean remove(int id);
}
