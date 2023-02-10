package exo3;

import java.util.HashMap;
import java.util.Map;

/**
 * On cherche à évaluer des formules logiques contenant des constantes,
 * des variables, la négation, la conjonction et la disjonction.
 * Les variables sont à évaluer dans un environnement fourni.
 * Pour cela on utilisera un visiteur pour explorer les formules.
 */
interface IVisiteur<T> {
    T visite(Cte c);
    T visite(Not e);
    T visite(And e);
    T visite(Or e);
    T visite(Var v) throws VariableFoundException;
}

abstract class Formule {
    public abstract <T> T accepte(IVisiteur<T> v);
}

// CLASSES CONCRETES DES FORMULES ET EXPRESSIONS BOOLEENNES

/**
 * Classe pour une variable qui est définie par un nom
 */
class Var extends Formule {
    String nom;

    public Var(String n) {
        nom = n;
    }

    public Var() {
        this("x");
    }

    public String getNom() {
        return nom;
    }

    @Override
    public <T> T accepte(IVisiteur<T> v) {

        try{
            return v.visite(this);
        }
        catch(VariableFoundException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}

/**
 * Classe pour une constante définie par une valeur booléenne
 */
class Cte extends Formule {

    boolean valeur;

    public Cte(boolean v) {
        valeur = v;
    }

    public boolean isTrue() {
        return valeur;
    }

    @Override
    public <T> T accepte(IVisiteur<T> v) {
        return v.visite(this);
    }
}

/**
 * Classe pour l'opérateur logique NOT
 */
class Not extends Formule {
    
    Formule f;

    public Not(Formule f) {
        this.f = f;
    }

    public Formule getFormule() {
        return f;
    }

    @Override
    public <T> T accepte(IVisiteur<T> v) {
        return v.visite(this);
    }
}

/**
 * Classe pour l'opérateur logique AND
 */
class And extends Formule {
    
    Formule f1, f2;

    public And(Formule f1, Formule f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    public Formule getF1() {
        return f1;
    }

    public Formule getF2() {
        return f2;
    }

    @Override
    public <T> T accepte(IVisiteur<T> v) {
        return v.visite(this);
    } 
}

/**
 * Classe pour l'opérateur logique OR
 */
class Or extends Formule {
    Formule f1, f2;

    public Or(Formule f1, Formule f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    public Formule getF1() {
        return f1;
    }

    public Formule getF2() {
        return f2;
    }

    @Override
    public <T> T accepte(IVisiteur<T> v) {
        return v.visite(this);
    }
}

// Exception en cas d'évaluation d'une variable

class VariableFoundException extends Exception {
    String variableName;
    public VariableFoundException(String name) {
        super("Cannot handle the variable : " + name);
        variableName = name;
    }
}


// CLASSES CONCRETES DES VISITEURS

class VisiteurEval implements IVisiteur<Boolean> {

    @Override
    public Boolean visite(Cte c) {
        return c.isTrue();
    }

    @Override
    public Boolean visite(Not e) {
        return !(e.getFormule().accepte(this));
    }

    @Override
    public Boolean visite(And e) {
        return e.getF1().accepte(this) && e.getF2().accepte(this);
    }

    @Override
    public Boolean visite(Or e) {
        return e.getF1().accepte(this) || e.getF2().accepte(this);
    }

    @Override
    public Boolean visite(Var v) throws VariableFoundException {
        throw new VariableFoundException(v.getNom());
    }
}

class VisiteurEvalVar extends VisiteurEval {

    Map <String, Boolean> variables = new HashMap<>();

    public void addVar(Var x, boolean b) {
        variables.put(x.getNom(), b);
    }

    @Override
    public Boolean visite(Var v) throws VariableFoundException {

        // La variable n'a jamais été visitée
        if (!variables.containsKey(v.getNom())) {
            throw new VariableFoundException(v.getNom());
        }
        
        return variables.get(v.getNom());
    }
}