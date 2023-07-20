package co.poynt.study_java17.intro;

public class C8_SealedTypes {

    // OOP is about extensibility
    // Java had either final class, or open interface
    interface Tmp{
        interface Expr{}
        record Val(int v) implements Expr{}
        record Add(Expr l, Expr r) implements Expr{}
        record Mul(Expr l, Expr r) implements Expr{}
    }

    // what if we want to limit subtypes
    sealed
        interface Expr
            permits Val, Add, Mul{}

    record Val(int v) implements Expr{}
    record Add(Expr l, Expr r) implements Expr{}
    record Mul(Expr l, Expr r) implements Expr{}

    // a sealed type permits only enumerated direct subtypes
    // a sealed type and its subtypes are closely related. (often recursive types)
    //   they must be defined in the same package.
    // if define in the same .java file, 'permits' can be omitted
    // or maybe better to define subtypes nested inside the supertype

    // like records, sealed types are meant for defining simple datatypes.
    // in general, do not make them complicated.
    //     use 'record' for leaf types.
    //     no instance methods, like eval()
    // records and sealed types are anti-OOP; more of Functional Programming.

    // define operations on sealed datatypes as library functions
    // eval(), simplify(), linearPrint(), treePrint(), drawSvg(), ...

    public static int eval(Expr expr){
        if (expr==null)
            throw new NullPointerException();
        else if (expr instanceof Val val)
            return val.v();
        else if (expr instanceof Add add)
            return eval(add.l()) + eval(add.r());
        else if (expr instanceof Mul mul)
            return eval(mul.l()) * eval(mul.r());
        else // should not reach
            throw new IncompatibleClassChangeError();
    }
    // visitor pattern - no longer needed

    /*  Java 21:
          release Sep 19 2023
          long-term support (LTS)

        public static int eval(Expr expr){
            return switch(expr) {
                case Val val -> val.v();
                case Add add -> eval(add.l()) + eval(add.r());
                case Mul mul -> eval(mul.l()) * eval(mul.r());
            };
        }
     */

    /* two ways of extensibility

    OOP: ops fixed, types extensible.

             Type A  Type B  -->  Type X
        op1                       X.op1
        op2                       X.op2
        -------------------
        breaks if new ops are introduced

    FP: types fixed, ops extensible

             Type A  Type B   |  breaks if new types are introduced
        op1                   |
        op2                   |
         ↓                    |
        opX  opX(A)  opX(B)   |

     */

}
