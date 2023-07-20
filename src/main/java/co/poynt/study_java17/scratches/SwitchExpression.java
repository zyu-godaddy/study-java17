package co.poynt.study_java17.scratches;

import java.util.function.Function;

// https://docs.oracle.com/javase/specs/jls/se17/html/jls-15.html#jls-15.28
public class SwitchExpression {

   void basics() {

        int x = (int)Math.PI;

        String s = switch(x) {
            case 0 -> "0";
            case 1 -> "1";
            case 2, 3, 4 -> "X";
            case 5 -> throw new RuntimeException();
            case 6 -> {
                System.out.println("...");
                yield "6";
            }
            default -> "default";
        };

        // can be 'void' type
       switch(x) {
           case 0 -> System.out.println("zero");
           case 1 -> System.out.println("one");
           // default -> System.out.println("many");
       }
       // technically, this is a switch *statement*
       // default is not required

       // switch statement, in the new form
       switch(x) {
           case 0 -> {
               if (true)
                   break;
               System.out.println("false");
               // no fall through here
           }
           case 1 -> {
               System.out.println("1");
           }
           default -> {
               System.out.println("default");
           }
       }

       // switch expression, but in the old form + yield
       s = switch(x) {
           case 0:
               yield "0";

           case 1:
           case 2, 3:
               System.out.println(x);
               // break;
               yield "some";

           case 4:
               System.out.println("four");
               // no yield
               // fall through

           default:
               yield "default";
       };

       // this is apparently a statement
       switch(x) {
           case 0 -> Math.sin(1);
           case 1 -> Math.cos(1);
       }
       // but could be an expression in a context where an expression is expected. (default is required then)

       // no switch on boolean...
       {
//           int i = switch(true) {
//               case true -> 1;
//               case false -> 2;
//           };
       }


       Function<Integer,String> i2s = i -> switch(i){
            case 0 -> "zero";
            case 1 -> "one";
            default -> "many";
        };

        enum Op { plus, mult }
        Op op = Op.valueOf("plus");

        int y = switch (op) {
            case plus -> x+x;
            case mult -> x*x;
        };
       // no default.
       // NPE if op==null

    }

    void yieldStatement() {
        // https://docs.oracle.com/javase/specs/jls/se17/html/jls-14.html#jls-14.21
        // A yield statement attempts to transfer control to the innermost enclosing switch expression

        int x = (int)Math.PI;

        String s = switch(x) {
            case 0 -> { yield "0"; }
            case 1 -> switch(x*x) {
                case 1 -> { yield "good"; }
                default -> throw new Error();
            };
            case 2 -> {
                for (int i=0; i<3; i++) {
                    if (i==1)
                        yield "1";
                }
                yield "2";
            }
            case 3 -> {
                yield "3";

                // does not compile
                //   return "3";
                // jls: It is a compile-time error if the return target contains ... a switch expression that encloses the return statement.
            }
            default -> { yield "default"; }
        };

    }

    void simulateStatementExpression() {

        // many java statements are not expressions;
        // but we can enclose them in a switch{} and yield a value.

        String v1 = switch(0){ default->{
            String foo = System.getenv("foo");
            if (foo==null)
                yield "foo undefined";

            for (char c : foo.toCharArray())
                if (c=='$')
                    yield "bad foo";

            yield "good foo: "+foo;
        }};

        int x = (int)Math.tan(1);
        String v2 = switch(0){ default->{
            if (x==1) {
                yield "1";
            } else if(x==2) {
                yield "2";
            } else {
                yield "3";
            }
        }};

        int v3 = switch(0){ default->{
            try {
                yield Integer.parseInt("3");
            } catch(NumberFormatException e) {
                yield -1;
            }
        }};

    }

}
