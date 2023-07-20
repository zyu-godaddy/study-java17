package co.poynt.study_java17.scratches;

public class SealedClasses {

    // inheritance:   open .. sealed .. final

    // sealed type:
    //   - an enumerated list of direct subtypes
    //   - the list cannot be empty. (why tho)
    //   - a direct subtype must be final or sealed, or explicitly non-sealed
    // typically, sealed type hierarchy is a tree of sealed interfaces and (final) records at leafs.
    // non-sealed is allowed, kind of a leaf in the sealed tree. its subtypes cannot be exhausted.

    sealed interface Shape
        permits Circle, Square // can be omitted if all defined in the same .java file
    {
        // don't have methods; leave that to library functions
        default double area() { throw new Error(); }
        default void draw() { throw new Error(); }
    }

    record Circle(int radius) implements Shape{}
    record Square(int width) implements Shape{}

    // a sealed type hierarchy is one integral unit.
    // if subtypes are not in the same .java file, they should be in the same package.

    // maybe better to define subtypes within the supertype
    //   Shape { Circle{} Square{} }

    // library functions on algebraic datatypes
    public static double area(Shape shape) {
        if (shape==null) {
            throw new NullPointerException();
        }

        if (shape instanceof Circle circle) {
            return Math.PI * circle.radius * circle.radius;
        }else if (shape instanceof Square square) {
            return square.width * square.width;
        } else {
            // too bad, javac doesn't know this is unreachable
            throw new IncompatibleClassChangeError();
        }
    }
    // Visitor pattern
    //   a technique to enforce compile time limited subtypes.
    //   a workaround in languages with only open interfaces.
    //
    // Java-17 still lacks compiler support of exhaustive subtype enumeration,
    // see https://openjdk.org/jeps/406
    //     Completeness of pattern labels in switch expressions and statements
    // It's available to Java-17 with `–enable-preview`. Finished in Java-21.
    // Otherwise, we still need a "default" clause.
    // nevertheless, refrain from using visitor pattern.
    // see a workaround:
    //   https://github.com/zhong-j-yu/rekex/blob/main/rekex-common_util/src/main/java/org/rekex/common_util/SwitchOnType.java




}
