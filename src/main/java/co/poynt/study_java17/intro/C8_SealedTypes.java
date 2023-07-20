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
    sealed interface Expr
            permits Val, Add, Mul{}

    record Val(int v) implements Expr{}
    record Add(Expr l, Expr r) implements Expr{}
    record Mul(Expr l, Expr r) implements Expr{}

    // a sealed type permits only enumerated direct subtypes
    // a sealed type and its subtypes are closely related. recursive references.
    //   they must be defined in the same package.
    // if define in the same .java file, 'permits' can be omitted
    // maybe better to define subtypes inside the supertype

    // like records, sealed types are meant for defining simple datatypes.
    // in general, do not make them complicated.
    //     use 'record' for leaf types.
    //     no instance methods, like eval()
    // records and sealed types are anti-OOP; more like Functional Programming.

    // define operations on sealed datatypes as library functions
    // eval(), simplify(), prettyPrint(), drawTree()

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
    // switch expression? need Java 21.

    // visitor pattern - no longer needed

}
