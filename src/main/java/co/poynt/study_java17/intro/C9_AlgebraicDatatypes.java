package co.poynt.study_java17.intro;

public class C9_AlgebraicDatatypes {

    /*

    Algebraic datatypes
        record: product type,   C := (A x B)    set product
        sealed:     sum type,   Z :=  X | Y     set union

    For constructing composite datatypes from basic datatypes
       basic datatypes --> algebraic datatypes --> abstract datatypes --> object types
    In Java, algebraic datatypes are interfaces/classes with features restricted.

    Simpler than object types
      no encapsulation
      no extensibility
    They are transparent and frozen; what you see if what you get.
    Sometimes we just want simpler things.

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
