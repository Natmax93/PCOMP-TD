package exo3;

/**
 * Classe de test pour l'évaluation des formules logiques
 */
public class Test {
    public static void main(String[] args) {
        
        // (a OU b ) ET ((NON a) OU (NON b))
        Var a = new Var("a");
        Var b = new Var("b");
        Formule f1 = new And(new Or(a, b), new Or(new Not(a), new Not(b)));

        //  (a ET (NON b) ) OU ((NON a) ET b)
        Formule f2 = new Or(new And(a, new Not(b)), new And(new Not(a), b));

        // On teste pour a = true et b = false
        // Puis a = true et b = true

        Formule f1_bis = new And(new Or(new Cte(true), new Cte(false)), new Or(new Not(new Cte(true)), new Not(new Cte(false))));
        Formule f2_bis = new Or(new And(new Cte(true), new Not(new Cte(true))), new And(new Not(new Cte(true)), new Cte(true)));

        System.out.println("f1_bis = " + f1_bis.accepte(new VisiteurEval()));
        System.out.println("f2_bis = " + f2_bis.accepte(new VisiteurEval()));
        // Déclenche l'exception
        //System.out.println("f1_bis = " + f1.accepte(new VisiteurEval()));

        VisiteurEvalVar v = new VisiteurEvalVar();
        v.addVar(a, true);
        v.addVar(b, false);
        System.out.println("f1 = " + f1.accepte(v));
        v.addVar(b, true);
        System.out.println("f2 = " + f2.accepte(v));
    }
}
